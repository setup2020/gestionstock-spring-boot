package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.LineOrderSupplierDto;
import cm.aupas.gestionstock.dto.LineSaleDto;

import java.util.List;

public interface ArticleService extends DefaultService<ArticleDto, Long> {

    ArticleDto findByReferenceArticle(String reference);
    List<LineSaleDto> findHistorySale(Long articleId);
    List<LineOrderCustomerDto> findHistoryOrderCustomer(Long articleId);

    List<LineOrderSupplierDto> findHistoryOrderSupplier(Long articleId);
    List<ArticleDto> findAllArticleByCategory(Long categoryId);

}
