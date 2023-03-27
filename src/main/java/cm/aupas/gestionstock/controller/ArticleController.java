package cm.aupas.gestionstock.controller;

import cm.aupas.gestionstock.controller.api.ArticleApi;
import cm.aupas.gestionstock.dto.*;
import cm.aupas.gestionstock.services.ArticleService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
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
    public ResponseEntity<ResponsePaginationDto> findAll(int page, int size, List<String> sort) {
        return  ResponseEntity.ok(articleService.findAll(page,size,sort));
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

    @Override
    public ResponseEntity<byte[]> reportArticle() throws JRException, IOException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "employees-details.pdf");
            return new ResponseEntity<byte[]>
                    (
                            JasperExportManager.exportReportToPdf(articleService.generateReport("dd")
                            ), headers, HttpStatus.OK);

        } catch(Exception e) {
            log.warn("-------------------------------------------------------d----------------------------------");
            log.error("error {}",e);
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
}
