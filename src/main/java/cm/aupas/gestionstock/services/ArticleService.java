package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.LineOrderSupplierDto;
import cm.aupas.gestionstock.dto.LineSaleDto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ArticleService extends DefaultService2<ArticleDto, Long> {

    ArticleDto findByReferenceArticle(String reference);
    List<LineSaleDto> findHistorySale(Long articleId);
    List<LineOrderCustomerDto> findHistoryOrderCustomer(Long articleId);

    List<LineOrderSupplierDto> findHistoryOrderSupplier(Long articleId);
    List<ArticleDto> findAllArticleByCategory(Long categoryId);

    JasperPrint generateReport(String fileFormat) throws JRException, IOException;

}
