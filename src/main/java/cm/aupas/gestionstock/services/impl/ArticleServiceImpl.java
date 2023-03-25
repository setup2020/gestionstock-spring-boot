package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.domain.Role;
import cm.aupas.gestionstock.dto.*;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.*;
import cm.aupas.gestionstock.services.ArticleService;
import cm.aupas.gestionstock.validators.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private  final LineSaleRepository lineSaleRepository;
    private final LineOrderCustomerRepository lineOrderCustomerRepository;
    private  final LineOrderSupplierRepository lineOrderSupplierRepository;
    private final SaleRepository saleRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository, LineSaleRepository lineSaleRepository, LineOrderCustomerRepository lineOrderCustomerRepository, LineOrderSupplierRepository lineOrderSupplierRepository, SaleRepository saleRepository) {
        this.articleRepository = articleRepository;
        this.lineSaleRepository = lineSaleRepository;
        this.lineOrderCustomerRepository = lineOrderCustomerRepository;
        this.lineOrderSupplierRepository = lineOrderSupplierRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors= ArticleValidator.validator(articleDto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}",articleDto);
            throw  new InvalidEntityException("l'article n'est pas valide", ErrorCode.ERROR_422,errors);
        }




        return ArticleDto.mapToDTO(
                articleRepository.save(ArticleDto.toEntity(articleDto))
        );
    }

    @Override
    public ArticleDto findById(Long id) {

         if(id==null){
             log.error("Article ID is null");
             return null;
         }

      Optional<Article>  article=articleRepository.findById(id);
         ArticleDto dto=ArticleDto.mapToDTO(article.get());
         return Optional.of(dto).orElseThrow(()->new EntityNotFoundException("Aucun article avec l'id= "+id+"n' ete trouvee dans la application",ErrorCode.ERROR_404));
    }

    @Override
    public ResponsePaginationDto<ArticleDto> findAll(int page, int size, List<String> sort){
        ResponsePaginationDto<ArticleDto> paginationDto=new ResponsePaginationDto<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articles = articleRepository.findAll(pageable);
        paginationDto.setSize(articles.getSize());
        paginationDto.setTotal(articles.getTotalElements());
        paginationDto.setTotalPage(articles.getTotalPages());
        paginationDto.setPage(page);
        // get content for page object
        List<Article> articleList = articles.getContent();
        paginationDto.setContent(articleList.stream().map(ArticleDto::mapToDTO).collect(Collectors.toList()));
        return  paginationDto;

    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);
    }

    @Override
    public ArticleDto update(ArticleDto articleDto) {
        return null;
    }

    @Override
    public ArticleDto findByReferenceArticle(String reference) {
        if(!StringUtils.hasLength(reference)){
            log.error("Article reference is null");
            return null;
        }

        Optional<Article> article=articleRepository.findByReference(reference);

        return Optional.of(ArticleDto.mapToDTO(article.get())).orElseThrow(()->
            new EntityNotFoundException("Aucun article avec cette reference= "+reference+"n' ete trouvee dans la BDD",ErrorCode.ERROR_404));
    }

    @Override
    public List<LineSaleDto> findHistorySale(Long articleId) {

        return lineSaleRepository.findAllByArticleId(articleId).stream().map(LineSaleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LineOrderCustomerDto> findHistoryOrderCustomer(Long articleId) {

    return lineOrderCustomerRepository.findAllByArticleId(articleId).stream().map(LineOrderCustomerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LineOrderSupplierDto> findHistoryOrderSupplier(Long articleId) {

        return lineOrderSupplierRepository.findAllByArticleId(articleId).stream().map(LineOrderSupplierDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticleByCategory(Long categoryId) {

        return articleRepository.findAllByCategoryId(categoryId).stream().map(ArticleDto::mapToDTO).collect(Collectors.toList());
    }
}
