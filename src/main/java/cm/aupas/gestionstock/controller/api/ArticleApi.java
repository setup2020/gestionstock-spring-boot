package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;
import java.util.List;

public interface ArticleApi {
    @GetMapping(value = APP_ROOT+"/articles/ref/{reference}",produces = MediaType.APPLICATION_JSON_VALUE)

    ArticleDto findByReferenceArticle(@PathVariable String reference);


    @PostMapping(value =APP_ROOT+"/articles/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public    ArticleDto save(ArticleDto articleDto);

    @GetMapping(value = APP_ROOT+"/articles/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findById(@PathVariable Long id);

    @GetMapping(value = APP_ROOT+"/articles",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> findAll();


    @DeleteMapping(value = APP_ROOT+"/articles/{id}")
    public void delete(@PathVariable Long id);


}
