package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.CustomerDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/customers")
public interface CustomerApi {

    @PostMapping(value = APP_ROOT+"/customers",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client", notes ="Cette methode permet d'enregistrer ou modifier un client",response = CustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'objet client cree/ modifier"),
            @ApiResponse(code = 400,message = "L'objet client n'est pas valide")
    })
    CustomerDto save(@RequestBody CustomerDto customerDto);

    @ApiOperation(value = "Renvoi la liste des clients", notes ="Cette methode permet  rechercher et renvoyer la liste des clients qui existe dans la BDD",responseContainer = "List<CustomerDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des clients / Une liste vide"),
    })
    @GetMapping(value = APP_ROOT+"/customers",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDto> findAll();


    @DeleteMapping(value = APP_ROOT+"/customers/{id}")
    @ApiOperation(value = "Suppriimer un client",notes = "Cette methode permet de supprimer un client par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La client a ete supprimé"),

    })
    void delete(@PathVariable Long id);

    @GetMapping(value = APP_ROOT+"/customers/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client", notes ="Cette methode permet  rechercher un client",response = CustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Le client a ete trouvé dans la BDD"),
            @ApiResponse(code = 404,message = "Aucun client n'existe dans la BDD avec l'ID fourni")
    })
    CustomerDto findById(@PathVariable Long id);
}
