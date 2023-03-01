package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.ArticleRepository;
import cm.aupas.gestionstock.services.ArticleService;
import cm.aupas.gestionstock.validators.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors= ArticleValidator.validator(articleDto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}",articleDto);
            throw  new InvalidEntityException("l'article n'est pas valide", ErrorCode.ERROR_422,errors);
        }




        return ArticleDto.fromEntity(
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
         ArticleDto dto=ArticleDto.fromEntity(article.get());
         return Optional.of(dto).orElseThrow(()->new EntityNotFoundException("Aucun article avec l'id= "+id+"n' ete trouvee dans la application",ErrorCode.ERROR_404));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
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

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
            new EntityNotFoundException("Aucun article avec cette reference= "+reference+"n' ete trouvee dans la BDD",ErrorCode.ERROR_404));


    }
}
