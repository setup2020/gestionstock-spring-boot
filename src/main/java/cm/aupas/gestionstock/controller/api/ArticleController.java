package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleController  implements ArticleApi{
    private ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService=articleService;
    }
    @Override
    public ArticleDto findByReferenceArticle(String reference) {
        return null;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return null;
    }

    @Override
    public ArticleDto findById(Long id) {
        return null;
    }

    @Override
    public List<ArticleDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
