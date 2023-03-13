package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.USER;

@Api(USER)

public interface UserApi {

    @PostMapping(value = USER, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer d'un utilisateur", notes = "Cette methode permet d'enregistrer ou modifier un utilisateur", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur cree/ modifier"),
            @ApiResponse(code = 400, message = "L'objet utilisateur n'est pas valide")
    })
    ResponseEntity<UserDto> save(@RequestBody UserDto userDto);

    @ApiOperation(value = "Renvoi la liste des utlisateurs", notes = "Cette methode permet  rechercher et renvoyer la liste des utilisateurs qui existe dans la BDD", responseContainer = "List<UserDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des utlisateurs / Une liste vide"),
    })
    @GetMapping(value = USER, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDto>> findAll();


    @DeleteMapping(value = USER + "/{id}")
    @ApiOperation(value = "Suppriimer un utitlisateur", notes = "Cette methode permet de supprimer un utilisateur par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La utilisateur a ete supprimé"),

    })
    void delete(@PathVariable Long id);

    @GetMapping(value = USER + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur", notes = "Cette methode permet  rechercher un utilisateur", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le utilisateur a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun utilisateur n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<UserDto> findById(@PathVariable Long id);
}
