package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.CompanyDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.COMPANY;

public interface CompanyApi {

    @PostMapping(value = COMPANY, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une entreprise", notes = "Cette methode permet d'enregistrer ou modifier une entreprise", response = CompanyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise cree/ modifier"),
            @ApiResponse(code = 400, message = "L'objet entreprise n'est pas valide")
    })
    ResponseEntity<CompanyDto> save(@RequestBody CompanyDto companyDto);

    @ApiOperation(value = "Renvoi la liste des entreprises", notes = "Cette methode permet  rechercher et renvoyer la liste des entreprises qui existe dans la BDD", responseContainer = "List<CompanyDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des entreprise / Une liste vide"),
    })
    @GetMapping(value = COMPANY, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CompanyDto>> findAll();


    @DeleteMapping(value = COMPANY + "/{id}")
    @ApiOperation(value = "Supprimer une entreprise", notes = "Cette methode permet de supprimer une entreprise par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a ete supprimé"),

    })
    void delete(@PathVariable Long id);

    @GetMapping(value = COMPANY + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une entreprise", notes = "Cette methode permet  rechercher une entreprise", response = CompanyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune entreprise n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CompanyDto> findById(@PathVariable Long id);
}
