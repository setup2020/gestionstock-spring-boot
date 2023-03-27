package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

import java.io.IOException;
import java.util.List;

@Api(APP_ROOT+"/articles")
public interface ArticleApi {
    @GetMapping(value = APP_ROOT+"/articles/ref/{reference}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article", notes ="Cette methode permet  rechercher un article par la reference",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete trouvé dans la BDD"),
            @ApiResponse(code = 404,message = "Aucun article n'existe dans la BDD avec la reference fourni")
    })

    ArticleDto findByReferenceArticle(@PathVariable String reference);


    @PostMapping(value =APP_ROOT+"/articles",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article par ID", notes ="Cette methode permet d'enregistrer ou modifier un article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'objet article cree/ modifier"),
            @ApiResponse(code = 400,message = "L'objet article n'est pas valide")
    })
    public ArticleDto save(@RequestBody ArticleDto articleDto);

    @ApiOperation(value = "Rechercher un article", notes ="Cette methode permet  rechercher un article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete trouvé dans la BDD"),
            @ApiResponse(code = 404,message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    @GetMapping(value = APP_ROOT+"/articles/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findById(@PathVariable Long id);




    @ApiOperation(value = "Renvoi la liste des articles", notes ="Cette methode permet  rechercher et renvoyer la liste des articles qui existe dans la BDD",responseContainer = "List<ArticleDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des articles / Une liste vide"),

    })
    @GetMapping(value = APP_ROOT+"/articles",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePaginationDto> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                         @RequestParam(value = "size", defaultValue = "1", required = false) int size,
                                                         @RequestParam(value = "sort", defaultValue = "name,desc", required = false) List<String> sort);



    @ApiOperation(value = "Suppriimer un article",notes = "Cette methode permet de supprimer un article par ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete supprimé"),

    })
    @DeleteMapping(value = APP_ROOT+"/articles/{id}")
    public void delete(@PathVariable Long id);


    @GetMapping(value = APP_ROOT+"/articles/history/sale/{articleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<LineSaleDto> findHistorySale(@PathVariable Long articleId);

    @GetMapping(value = APP_ROOT+"/articles/history/order_customer/{articleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<LineOrderCustomerDto> findHistoryOrderCustomer(@PathVariable  Long articleId);
    @GetMapping(value = APP_ROOT+"/articles/history/order_suppliers/{articleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<LineOrderSupplierDto> findHistoryOrderSupplier(@PathVariable  Long articleId);

    @GetMapping(value = APP_ROOT+"/articles/history/filter/category/{categoryId}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAllArticleByCategory(@PathVariable  Long categoryId);

    @GetMapping(value = APP_ROOT+"/articles/report")
    ResponseEntity<byte[]> reportArticle() throws JRException, IOException;

}
