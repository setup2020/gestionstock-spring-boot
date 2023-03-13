package cm.aupas.gestionstock.controller;

import cm.aupas.gestionstock.controller.api.ArticleApi;
import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.LineOrderSupplierDto;
import cm.aupas.gestionstock.dto.LineSaleDto;
import cm.aupas.gestionstock.services.ArticleService;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ArticleController  implements ArticleApi {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService=articleService;
    }
    @Override
    public ArticleDto findByReferenceArticle(String reference) {
        return articleService.findByReferenceArticle(reference)
                ;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Long id) {
        return articleService.findById(id);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Long id) {
            articleService.delete(id);
    }

    @Override
    public List<LineSaleDto> findHistorySale(Long articleId) {
        return articleService.findHistorySale(articleId);
    }

    @Override
    public List<LineOrderCustomerDto> findHistoryOrderCustomer(Long articleId) {
        return articleService.findHistoryOrderCustomer(articleId);
    }

    @Override
    public List<LineOrderSupplierDto> findHistoryOrderSupplier(Long articleId) {
        return articleService.findHistoryOrderSupplier(articleId);
    }

    @Override
    public List<ArticleDto> findAllArticleByCategory(Long categoryId) {
        return articleService.findAllArticleByCategory(categoryId);
    }
}
