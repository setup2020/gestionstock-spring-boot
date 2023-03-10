package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.domain.LineOrderSupplier;
import cm.aupas.gestionstock.domain.OrderSupplier;
import cm.aupas.gestionstock.domain.Supplier;
import cm.aupas.gestionstock.dto.LineOrderSupplierDto;
import cm.aupas.gestionstock.dto.OrderSupplierDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.ArticleRepository;
import cm.aupas.gestionstock.repository.LineOrderSupplierRepository;
import cm.aupas.gestionstock.repository.OrderSupplierRepository;
import cm.aupas.gestionstock.repository.SupplierRepository;
import cm.aupas.gestionstock.services.OrderSupplierService;
import cm.aupas.gestionstock.validators.OrderSupplierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
