package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.SupplierDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

public interface SupplierApi {

    @PostMapping(value = APP_ROOT + "/suppliers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un fournisseur", notes = "Cette methode permet d'enregistrer ou modifier un fournisseur", response = SupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur cree/ modifier"),
            @ApiResponse(code = 400, message = "L'objet fournisseur n'est pas valide")
    })
    SupplierDto save(@RequestBody SupplierDto supplierDto);

    @ApiOperation(value = "Renvoi la liste des fournisseurs", notes = "Cette methode permet  rechercher et renvoyer la liste des fournisseurs qui existe dans la BDD", responseContainer = "List<SupplierDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / Une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SupplierDto> findAll();


    @DeleteMapping(value = APP_ROOT + "/suppliers/{id}")
    @ApiOperation(value = "Suppriimer un fournisseur", notes = "Cette methode permet de supprimer un fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur a ete supprimé"),

    })
    void delete(@PathVariable Long id);

    @GetMapping(value = APP_ROOT + "/suppliers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un fournisseur", notes = "Cette methode permet  rechercher un fournisseur", response = SupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    SupplierDto findById(@PathVariable Long id);
}
