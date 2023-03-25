package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.dto.CategoryDto;
import cm.aupas.gestionstock.dto.ResponsePaginationDto;
import cm.aupas.gestionstock.dto.RoleDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.DEFAULT_PAGE_NUMBER;
import static cm.aupas.gestionstock.utils.Constants.ROLE;


public interface RoleApi {

    @PostMapping(value = ROLE,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un role", notes ="Cette methode permet d'enregistrer ou modifier un role",response = RoleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'objet role cree/ modifier"),
            @ApiResponse(code = 400,message = "L'objet role n'est pas valide")
    })
    RoleDto save(@RequestBody RoleDto roleDto);

    @GetMapping(value = ROLE+"/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un role", notes ="Cette methode permet  rechercher un role",response = RoleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Le role a ete trouvé dans la BDD"),
            @ApiResponse(code = 404,message = "Aucun role n'existe dans la BDD avec l'ID fourni")
    })
    RoleDto findById(@PathVariable("id") Long id);



    @ApiOperation(value = "Renvoi la liste des roles", notes ="Cette methode permet  rechercher et renvoyer la liste des roles qui existe dans la BDD",responseContainer = "List<RoleDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des roles / Une liste vide"),
    })
    @GetMapping(value = ROLE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponsePaginationDto<RoleDto> findAll( @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(value = "size", defaultValue = "1", required = false) int size,
                                            @RequestParam(value = "sort", defaultValue = "name,desc", required = false) List<String> sort);



    @DeleteMapping(value = ROLE+"/{id}")
    @ApiOperation(value = "Supprimer un role",notes = "Cette methode permet de supprimer un role par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Le ROle a ete supprimé"),

    })
    void delete(@PathVariable Long id);
}
