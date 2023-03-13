package cm.aupas.gestionstock.services.strategy;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.services.ArticleService;
import cm.aupas.gestionstock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;


@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDto>{

    private final FlickrService flickrService;
    private final ArticleService articleService;

    public SaveArticlePhoto(FlickrService flickrService, ArticleService articleService) {
        this.flickrService = flickrService;
        this.articleService = articleService;
    }

    @Override
    public ArticleDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        ArticleDto articleDto=articleService.findById(id);
        String url= flickrService.savePhoto(photo,title);
        if(!StringUtils.hasLength(url)){
            throw  new InvalidOperationException("Erreur lors de l'enregistrement de la  photo de l'article", ErrorCode.ERROR_500);
        }
        articleDto.setPhoto(url);

        return articleService.save(articleDto);
    }
}
