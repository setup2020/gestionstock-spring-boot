package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.dto.CategoryDto;
import cm.aupas.gestionstock.dto.SimplePage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une category", notes = "Cette methode permet d'enregistrer ou modifier une category", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet category cree/ modifier"),
            @ApiResponse(code = 400, message = "L'objet category n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une category", notes = "Cette methode permet  rechercher une category", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La category a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune category n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable("id") Long id);


    @GetMapping(value = APP_ROOT + "/categories/reference/{reference}")
    @ApiOperation(value = "Rechercher un category", notes = "Cette methode permet  rechercher une category par la reference", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La category a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune category n'existe dans la BDD avec la reference fourni")
    })
    CategoryDto findByReference(@PathVariable("reference") String reference);


    @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette methode permet  rechercher et renvoyer la liste des categories qui existe dans la BDD", responseContainer = "List<CategoryDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SimplePage<CategoryDto>> findAll(@RequestParam(value = "name",required = false)  String name, @SortDefault(sort = "name") @PageableDefault(size = 2) Pageable pageable);


    @DeleteMapping(value = APP_ROOT + "/categories/{id}")
    @ApiOperation(value = "Supprimer une category", notes = "Cette methode permet de supprimer une category par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La category a ete supprimé"),

    })
    void delete(@PathVariable Long id);


    @GetMapping(APP_ROOT + "/categories/count")
    @ApiOperation(value = "Compte les categories",notes = "Cette methode permet de compter le nombre de categorie")
    Long count();
}
