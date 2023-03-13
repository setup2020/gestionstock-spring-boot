package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.domain.Customer;
import cm.aupas.gestionstock.domain.LineOrderCustomer;
import cm.aupas.gestionstock.domain.OrderCustomer;
import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.OrderCustomerDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.repository.ArticleRepository;
import cm.aupas.gestionstock.repository.CustomerRepository;
import cm.aupas.gestionstock.repository.LineOrderCustomerRepository;
import cm.aupas.gestionstock.repository.OrderCustomerRepository;
import cm.aupas.gestionstock.services.OrderCustomerService;
import cm.aupas.gestionstock.validators.ArticleValidator;
import cm.aupas.gestionstock.validators.OrderCustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.dnd.InvalidDnDOperationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderCustomerServiceImpl implements OrderCustomerService {

    private final OrderCustomerRepository orderCustomerRepository;
    private  final CustomerRepository customerRepository;
    private final ArticleRepository articleRepository;
    private  final LineOrderCustomerRepository lineOrderCustomerRepository;


    public OrderCustomerServiceImpl(OrderCustomerRepository orderCustomerRepository, CustomerRepository customerRepository, ArticleRepository articleRepository, LineOrderCustomerRepository lineOrderCustomerRepository) {
        this.orderCustomerRepository = orderCustomerRepository;
        this.customerRepository = customerRepository;
        this.articleRepository = articleRepository;
        this.lineOrderCustomerRepository = lineOrderCustomerRepository;
    }

    @Override
    public OrderCustomerDto save(OrderCustomerDto orderCustomerDto) {
        List<String> errors= OrderCustomerValidator.validator(orderCustomerDto);
        if(!errors.isEmpty()){
            log.error("Commande client n'est pas valide");
            throw new  InvalidEntityException("Commande client n'est pas valide", ErrorCode.ERROR_422,errors);
        }
        Optional<Customer> customer=customerRepository.findById(orderCustomerDto.getCustomer().getId());

        if(customer.isEmpty()){
            log.warn("Client with ID {} was not found in the DB",orderCustomerDto.getCustomer().getId());
            throw  new EntityNotFoundException("Aucun client avec le l'ID"+orderCustomerDto.getCustomer().getId()+" exist dans la BD");

        }
        if(orderCustomerDto.getId()!=null && orderCustomerDto.iSOrderDelivery()){
            throw  new InvalidOperationException("Impossible de modifier la commande lorqu'elle est livrée",ErrorCode.ERROR_500);

        }

        List<String> articleErrors=new ArrayList<>();

        if(orderCustomerDto.getLineOrderCustomers()!=null){
            orderCustomerDto.getLineOrderCustomers().forEach(line->{
                if(line.getArticle()!=null){
                    Optional<Article> article=articleRepository.findById(line.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID "+line.getArticle().getId()+" n'exist pas");
                    }
                }else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");

                }

            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BD  ",ErrorCode.ERROR_404,articleErrors);

        }
       OrderCustomer orderCustomerSaved= orderCustomerRepository.save(OrderCustomerDto.toEntity(orderCustomerDto));

        if(orderCustomerDto.getLineOrderCustomers()!=null){
            orderCustomerDto.getLineOrderCustomers().forEach(line->{
                LineOrderCustomer lineOrderCustomer= LineOrderCustomerDto.toEntity(line);
                lineOrderCustomer.setOrderCustomer(orderCustomerSaved);
                lineOrderCustomerRepository.save(lineOrderCustomer);
            });
        }


        return OrderCustomerDto.fromEntity(orderCustomerSaved);
    }

    @Override
    public OrderCustomerDto findById(Long id) {
        if(id==null){
            log.error("Commande client ID is NULL");
            return null;
        }
        return orderCustomerRepository.findById(id).map(OrderCustomerDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune commande client n'a ete trouvé avev l'ID "+id,ErrorCode.ERROR_422));
    }

    @Override
    public List<OrderCustomerDto> findAll() {
        return orderCustomerRepository.findAll().stream()
                .map(OrderCustomerDto::fromEntity)
                .collect(Collectors.toList())

                ;
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Commande client ID is NULL");
            return;
        }
        orderCustomerRepository.deleteById(id);

    }


    @Override
    public OrderCustomerDto update(OrderCustomerDto orderCustomerDto) {
        return null;
    }

    @Override
    public OrderCustomerDto findByReference(String reference) {
        if(reference==null){
            log.error("Commande client Reference is NULL");
            return null;
        }
        return orderCustomerRepository.findByReference(reference).map(OrderCustomerDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune commande client n'a ete trouvé avec reference "+reference,ErrorCode.ERROR_422));

    }

    @Override
    public OrderCustomerDto updateStatusOrder(Long id, StatusOrder statusOrder) {
        if(id==null){
            log.error("Commande client ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier l'etat de la  commande avec un id NULL",ErrorCode.ERROR_500);

        }

        if(StringUtils.hasLength(String.valueOf(statusOrder))){
            log.error("L'etat de la Commande client  is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la  commande avec un etat NULL",ErrorCode.ERROR_500);

        }


        OrderCustomerDto orderCustomerDto=checkStatusOrder(id);
        orderCustomerDto.setStatus(statusOrder);

        return OrderCustomerDto.fromEntity(
                orderCustomerRepository.save(OrderCustomerDto.toEntity(orderCustomerDto))
        );


    }



    @Override
    public OrderCustomerDto updateQuantityOrder(Long id, Long lineOrderId, BigDecimal quantity) {
        if(id==null){
            log.error("Commande client ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier la quantité de la  commande avec un id NULL",ErrorCode.ERROR_500);

        }

        checkLineOrderId(lineOrderId);

        if(quantity==null || quantity.compareTo(BigDecimal.ZERO)==0){

            throw  new EntityNotFoundException("Impossible de modifier la quantity de la  commande avec une quantité NULL ou zero",ErrorCode.ERROR_500);

        }




        OrderCustomerDto orderCustomerDto=checkStatusOrder(id);
        Optional<LineOrderCustomer> lineOrderCustomerOptional=findLineOrderCustomer(lineOrderId);
        LineOrderCustomer lineOrderCustomer=lineOrderCustomerOptional.get();
        lineOrderCustomer.setQuantity(quantity);
        lineOrderCustomerRepository.save(lineOrderCustomer);

        return orderCustomerDto;
    }




    @Override
    public OrderCustomerDto updateClient(Long orderId, Long customerId) {

      checkIdCustomer(customerId);

        checkIdOrder(orderId);

        OrderCustomerDto orderCustomerDto=checkStatusOrder(orderId);
        Optional<Customer> customerOptional=customerRepository.findById(customerId);

        if(customerOptional.isEmpty()){
            throw  new EntityNotFoundException("Aucune client n'a ete trouvé avec l'ID "+customerId,ErrorCode.ERROR_404);

        }

        orderCustomerDto.setCustomer(CustomerDto.fromEntity(customerOptional.get()));
        return OrderCustomerDto.fromEntity(
                orderCustomerRepository.save(OrderCustomerDto.toEntity(orderCustomerDto))
        );
    }
    @Override
    public OrderCustomerDto updateArticle(Long orderId, Long lineOrderId,  Long articleId) {
        checkIdOrder(orderId);
        checkLineOrderId(lineOrderId);

        checkIdArticle(articleId,"nouvel");

        OrderCustomerDto orderCustomerDto=checkStatusOrder(orderId);

        Optional<LineOrderCustomer> lineOrderCustomer=findLineOrderCustomer(lineOrderId);

        Optional<Article> articleOptional=articleRepository.findById(articleId);
        if(articleOptional.isEmpty()){
            throw  new EntityNotFoundException("Aucun article n'a ete trouvé avec ID  "+articleId,ErrorCode.ERROR_500);
        }
        List<String> errors =ArticleValidator.validator(ArticleDto.fromEntity(articleOptional.get()));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Article invalide",ErrorCode.ERROR_422,errors);
        }

        LineOrderCustomer lineOrderCustomerToSave=lineOrderCustomer.get();
        lineOrderCustomerToSave.setArticle(articleOptional.get());
        lineOrderCustomerRepository.save(lineOrderCustomerToSave);


        return  orderCustomerDto;
    }

    @Override
    public OrderCustomerDto deleteArticle(Long orderId, Long lineOrderId) {
        checkIdOrder(orderId);
        checkLineOrderId(lineOrderId);
        OrderCustomerDto orderCustomerDto=checkStatusOrder(lineOrderId);
        // Jsut to check lineOrderCustomer a
        findLineOrderCustomer(lineOrderId);
        lineOrderCustomerRepository.deleteById(lineOrderId);

        return orderCustomerDto;
    }


    @Override
    public List<LineOrderCustomerDto> findAllLineOrderCustomerByOrderCustomerId(Long orderId) {
        return lineOrderCustomerRepository.findAllByOrderCustomerId(orderId).stream().map(LineOrderCustomerDto::fromEntity).collect(Collectors.toList());
    }

    private void checkIdCustomer(Long customerId) {
        if(customerId==null){
            log.error("Client ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier l'etat  de la  commande avec un ID client NULL",ErrorCode.ERROR_500);

        }
    }





    private void  checkIdOrder(Long orderId){
        if(orderId==null){
            log.error("Commande ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier l'etat  de la  commande avec un ID Commande NULL",ErrorCode.ERROR_500);


        }
    }

    private OrderCustomerDto checkStatusOrder(Long orderId) {

        OrderCustomerDto orderCustomerDto=   findById(orderId);
        if(orderCustomerDto.getId()!=null && orderCustomerDto.iSOrderDelivery()){
            throw  new InvalidOperationException("Impossible de modifier la commande lorqu'elle est livrée",ErrorCode.ERROR_500);

        }
        return orderCustomerDto;
    }
    private void checkLineOrderId(Long lineOrderId) {
        if(lineOrderId==null){
            log.error("La ligne Commande client ID is NULL");
            throw  new EntityNotFoundException("Impossible de modifier la quantity de la  commande avec une ligne commande NULL",ErrorCode.ERROR_500);

        }
    }

    private void checkIdArticle(Long idArticle, String msg) {
        if (idArticle == null) {
            log.error("L'ID de " + msg + " is NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg + " ID article null",
                   ErrorCode.ERROR_404);
        }
    }


    private Optional<LineOrderCustomer> findLineOrderCustomer(Long lineOrderId) {
        Optional<LineOrderCustomer> lineOrderCustomerOptional=    lineOrderCustomerRepository.findById(lineOrderId);
        if(lineOrderCustomerOptional.isEmpty()){
            throw  new EntityNotFoundException("Aucune ligne commande client n'a ete trouvé avec l'ID "+lineOrderId,ErrorCode.ERROR_500);

        }
        return  lineOrderCustomerOptional;
    }


}
