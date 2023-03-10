package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.domain.Customer;
import cm.aupas.gestionstock.domain.LineOrderCustomer;
import cm.aupas.gestionstock.domain.OrderCustomer;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.OrderCustomerDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.ArticleRepository;
import cm.aupas.gestionstock.repository.CustomerRepository;
import cm.aupas.gestionstock.repository.LineOrderCustomerRepository;
import cm.aupas.gestionstock.repository.OrderCustomerRepository;
import cm.aupas.gestionstock.services.OrderCustomerService;
import cm.aupas.gestionstock.validators.OrderCustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
