package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.*;
import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.*;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.repository.ArticleRepository;
import cm.aupas.gestionstock.repository.LineOrderSupplierRepository;
import cm.aupas.gestionstock.repository.OrderSupplierRepository;
import cm.aupas.gestionstock.repository.SupplierRepository;
import cm.aupas.gestionstock.services.OrderSupplierService;
import cm.aupas.gestionstock.validators.ArticleValidator;
import cm.aupas.gestionstock.validators.OrderSupplierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderSupplierServiceImpl implements OrderSupplierService {

    private final OrderSupplierRepository orderSupplierRepository;
    private final SupplierRepository supplierRepository;
    private final ArticleRepository articleRepository;
    private final LineOrderSupplierRepository lineOrderSupplierRepository;

    public OrderSupplierServiceImpl(OrderSupplierRepository orderSupplierRepository, SupplierRepository supplierRepository, ArticleRepository articleRepository, LineOrderSupplierRepository lineOrderSupplierRepository) {
        this.orderSupplierRepository = orderSupplierRepository;
        this.supplierRepository = supplierRepository;
        this.articleRepository = articleRepository;
        this.lineOrderSupplierRepository = lineOrderSupplierRepository;
    }

    @Override
    public OrderSupplierDto save(OrderSupplierDto orderSupplierDto) {
        List<String> errors = OrderSupplierValidator.validator(orderSupplierDto);
        if (!errors.isEmpty()) {
            log.error("Commande fournisseur n'est pas valide");
            throw new InvalidEntityException("Commande fournisseur n'est pas valide", ErrorCode.ERROR_422, errors);
        }
        Optional<Supplier> supplier = supplierRepository.findById(orderSupplierDto.getSupplier().getId());
        if (supplier.isEmpty()) {
            log.warn("Supplier with ID {} was not found in the DB", orderSupplierDto.getSupplier().getId());
            throw new EntityNotFoundException("Aucun client avec le l'ID " + orderSupplierDto.getSupplier().getId() + " existe dans la BD");

        }

        List<String> articleErrors = new ArrayList<>();

        if (orderSupplierDto.getLineOrderSuppliers() != null) {
            orderSupplierDto.getLineOrderSuppliers().forEach(line -> {
                if (line.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(line.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + line.getArticle().getId() + " n'exist pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");

                }

            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BD  ", ErrorCode.ERROR_404, articleErrors);

        }

        OrderSupplier orderSupplierSaved = orderSupplierRepository.save(OrderSupplierDto.toEntity(orderSupplierDto));

        if (orderSupplierDto.getLineOrderSuppliers() != null) {
            orderSupplierDto.getLineOrderSuppliers().forEach(line -> {
                LineOrderSupplier lineOrderSupplier = LineOrderSupplierDto.toEntity(line);
                lineOrderSupplier.setOrderSupplier(orderSupplierSaved);
                lineOrderSupplierRepository.save(lineOrderSupplier);
            });
        }


        return OrderSupplierDto.fromEntity(orderSupplierSaved);


    }


    @Override
    public OrderSupplierDto findById(Long id) {
        if (id == null) {

        }
        return orderSupplierRepository.findById(id).map(OrderSupplierDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("Aucune commande n'a ete trouvée avec l'ID " + id, ErrorCode.ERROR_401));

    }

    @Override
    public List<OrderSupplierDto> findAll() {
        return orderSupplierRepository.findAll().stream().map(OrderSupplierDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Commande fournisseur ID is NULL");
            return;
        }
        orderSupplierRepository.deleteById(id);
    }

    @Override
    public OrderSupplierDto update(OrderSupplierDto orderSupplierDto) {
        return null;
    }


    @Override
    public OrderSupplierDto findByReference(String reference) {
        if (reference == null) {
            log.error("Commande fournisseur Reference is NULL");
            return null;
        }
        return orderSupplierRepository.findByReference(reference).map(OrderSupplierDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("Aucune commande n'a ete trouvée avec la reference " + reference, ErrorCode.ERROR_401));
    }


    @Override
    public OrderSupplierDto updateStatusOrder(Long id, StatusOrder statusOrder) {
        if(id==null){
            log.error("Commande client ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier l'etat de la  commande avec un id NULL",ErrorCode.ERROR_500);

        }

        if(StringUtils.hasLength(String.valueOf(statusOrder))){
            log.error("L'etat de la Commande client  is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la  commande avec un etat NULL",ErrorCode.ERROR_500);

        }


        OrderSupplierDto orderSupplierDto=checkStatusOrder(id);
        orderSupplierDto.setStatus(statusOrder);

        return OrderSupplierDto.fromEntity(
                orderSupplierRepository.save(OrderSupplierDto.toEntity(orderSupplierDto))
        );
    }

    @Override
    public OrderSupplierDto updateQuantityOrder(Long id, Long lineOrderId, BigDecimal quantity) {
        if(id==null){
            log.error("Commande fournisseur ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier la quantité de la  commande avec un id NULL",ErrorCode.ERROR_500);

        }

        checkLineOrderId(lineOrderId);

        if(quantity==null || quantity.compareTo(BigDecimal.ZERO)==0){

            throw  new EntityNotFoundException("Impossible de modifier la quantity de la  commande avec une quantité NULL ou zero",ErrorCode.ERROR_500);

        }




        OrderSupplierDto orderCustomerDto=checkStatusOrder(id);
        Optional<LineOrderSupplier> lineOrderSupplierOptional=findLineOrderSupplier(lineOrderId);
        LineOrderSupplier lineOrderSupplier=lineOrderSupplierOptional.get();
        lineOrderSupplier.setQuantity(quantity);
        lineOrderSupplierRepository.save(lineOrderSupplier);

        return orderCustomerDto;
    }

    @Override
    public OrderSupplierDto updateSupplier(Long orderId, Long supplierId) {

        checkIdSupplier(supplierId);

        checkIdOrder(orderId);

        OrderSupplierDto orderSupplierDto=checkStatusOrder(orderId);
        Optional<Supplier> supplierOptional=supplierRepository.findById(supplierId);

        if(supplierOptional.isEmpty()){
            throw  new EntityNotFoundException("Aucun fournisseur n'a ete trouvé avec l'ID "+supplierId,ErrorCode.ERROR_404);

        }

        orderSupplierDto.setSupplier(SupplierDto.fromEntity(supplierOptional.get()));
        return OrderSupplierDto.fromEntity(
                orderSupplierRepository.save(OrderSupplierDto.toEntity(orderSupplierDto))
        );
    }
    @Override
    public OrderSupplierDto updateArticle(Long orderId, Long lineOrderId,  Long articleId) {
        checkIdOrder(orderId);
        checkLineOrderId(lineOrderId);

        checkIdArticle(articleId,"nouvel");

        OrderSupplierDto orderSupplierDto=checkStatusOrder(orderId);

        Optional<LineOrderSupplier> lineOrderSupplier=findLineOrderSupplier(lineOrderId);

        Optional<Article> articleOptional=articleRepository.findById(articleId);
        if(articleOptional.isEmpty()){
            throw  new EntityNotFoundException("Aucun article n'a ete trouvé avec ID  "+articleId,ErrorCode.ERROR_500);
        }
        List<String> errors = ArticleValidator.validator(ArticleDto.fromEntity(articleOptional.get()));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Article invalide",ErrorCode.ERROR_422,errors);
        }

        LineOrderSupplier lineOrderSupplierToSave=lineOrderSupplier.get();
        lineOrderSupplierToSave.setArticle(articleOptional.get());
        lineOrderSupplierRepository.save(lineOrderSupplierToSave);


        return  orderSupplierDto;
    }



    @Override
    public OrderSupplierDto deleteArticle(Long orderId, Long lineOrderId) {
        checkIdOrder(orderId);
        checkLineOrderId(lineOrderId);
        OrderSupplierDto orderSupplierDto=checkStatusOrder(lineOrderId);
        // Jsut to check lineOrderCustomer a
        findLineOrderSupplier(lineOrderId);
        lineOrderSupplierRepository.deleteById(lineOrderId);

        return orderSupplierDto;
    }

    @Override
    public List<LineOrderSupplierDto> findAllLineOrderSupplierByOrderSupplierId(Long orderId) {
        return lineOrderSupplierRepository.findAllByOrderSupplierId(orderId).stream().map(LineOrderSupplierDto::fromEntity).collect(Collectors.toList());
    }

    private OrderSupplierDto checkStatusOrder(Long orderId) {

        OrderSupplierDto orderSupplierDto=   findById(orderId);
        if(orderSupplierDto.getId()!=null && orderSupplierDto.iSOrderDelivery()){
            throw  new InvalidOperationException("Impossible de modifier la commande lorqu'elle est livrée",ErrorCode.ERROR_500);

        }
        return orderSupplierDto;
    }

    private void checkLineOrderId(Long lineOrderId) {
        if(lineOrderId==null){
            log.error("La ligne Commande client ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier la quantity de la  commande avec une ligne commande NULL",ErrorCode.ERROR_500);

        }
    }



    private void checkIdSupplier(Long supplierId) {
        if(supplierId==null){
            log.error("Supplier ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier l'etat  de la  commande avec un ID Fournisseur NULL",ErrorCode.ERROR_500);

        }
    }
    private void  checkIdOrder(Long orderId){
        if(orderId==null){
            log.error("Commande ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier l'etat  de la  commande avec un ID Commande NULL",ErrorCode.ERROR_500);


        }
    }

    private void checkIdArticle(Long idArticle, String msg) {
        if (idArticle == null) {
            log.error("L'ID de " + msg + " is NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg + " ID article null",
                    ErrorCode.ERROR_404);
        }
    }
    private Optional<LineOrderSupplier> findLineOrderSupplier(Long lineOrderId) {
        Optional<LineOrderSupplier> lineOrderSupplierOptional=    lineOrderSupplierRepository.findById(lineOrderId);
        if(lineOrderSupplierOptional.isEmpty()){
            throw  new EntityNotFoundException("Aucune ligne commande fournisseur n'a ete trouvé avec l'ID "+lineOrderId,ErrorCode.ERROR_500);

        }
        return  lineOrderSupplierOptional;
    }
}
