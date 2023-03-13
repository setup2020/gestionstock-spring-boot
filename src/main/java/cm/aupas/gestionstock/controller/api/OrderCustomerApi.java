package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.OrderCustomerDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.ORDER_CUSTOMER;

@Api(ORDER_CUSTOMER)
public interface OrderCustomerApi {

    @PostMapping(ORDER_CUSTOMER)
    @ApiOperation(value = "Enregistrer une commande client par ID", notes = "Cette methode permet d'enregistrer ou modifier une commande client ", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client cree/ modifier"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<OrderCustomerDto> save(@RequestBody OrderCustomerDto orderCustomerDto);


    @ApiOperation(value = "Rechercher une commande client ", notes = "Cette methode permet  rechercher une commande client", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commande client a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client  n'existe dans la BDD avec l'ID fourni")
    })

    @GetMapping(ORDER_CUSTOMER + "/{id}")
    ResponseEntity<OrderCustomerDto> findById(@PathVariable Long id);

    @GetMapping(ORDER_CUSTOMER + "/ref/{reference}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette methode permet  rechercher une commande client par la reference", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commande clienr a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'existe dans la BDD avec la reference fourni")
    })
    public ResponseEntity<OrderCustomerDto> findByReference(@PathVariable String reference);

    @GetMapping(ORDER_CUSTOMER)
    @ApiOperation(value = "Renvoi la liste des commandes clients", notes = "Cette methode permet  rechercher et renvoyer la liste des commandes clients qui existe dans la BDD", responseContainer = "List<OrderCustomerDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes clients / Une liste vide"),

    })
    ResponseEntity<List<OrderCustomerDto>> findAll();


    @ApiOperation(value = "Supprimer une commande client ", notes = "Cette methode permet de supprimer une commande client par ID", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete supprimé"),

    })
    @DeleteMapping(ORDER_CUSTOMER + "/{id}")
    void delete(@PathVariable Long id);


    @ApiOperation(value = "Changer l'etat de la commande client", notes = "Cette methode permet de changer l'etat de la commande client", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'etat de La commande client a ete changé avec succes"),

    })
    @PatchMapping(ORDER_CUSTOMER + "/{id}/status/{status}")
    ResponseEntity<OrderCustomerDto> updateStatusOrder(@PathVariable Long id, @PathVariable StatusOrder status);


    @ApiOperation(value = "Changer la quantité de la commande client", notes = "Cette methode permet de changer la quantité de la commande client", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La quantité de La commande client a ete changé avec succes"),

    })
    @PatchMapping(ORDER_CUSTOMER + "/{id}/line_order_customers/{lineOrderCustomerId}/quantity/{quantity}")
    ResponseEntity<OrderCustomerDto> updateQuantityOrder(@PathVariable Long id, @PathVariable Long lineOrderCustomerId, @PathVariable BigDecimal quantity);


    @ApiOperation(value = "Modifier le client de la commande", notes = "Cette methode permet de modifier le client de commande client", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client de la commande a ete modifier avec succes"),

    })

    @PatchMapping(ORDER_CUSTOMER + "/{orderCustomerId}/customers/{customerId}")
    ResponseEntity<OrderCustomerDto> updateClientOrder(@PathVariable Long orderCustomerId, @PathVariable Long customerId);


    @ApiOperation(value = "Modifier l'article de la commande", notes = "Cette methode permet de modifier l'article de commande client", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article de la commande a ete modifier avec succes"),

    })
    @PatchMapping(ORDER_CUSTOMER + "/{id}/line_order_customers/{lineOrderId}/articles/{articleId}")
    ResponseEntity<OrderCustomerDto> updateArticle(@PathVariable Long id, @PathVariable Long lineOrderId, @PathVariable Long articleId);

    @ApiOperation(value = "Supprimer l'article de la commande", notes = "Cette methode permet de supprimer l'article de commande client", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article de la commande a ete supprimé avec succes"),

    })
    @DeleteMapping(ORDER_CUSTOMER + "/delete/article/{orderId}/{lineOrderId}")
    ResponseEntity<OrderCustomerDto> deleteArticle(@PathVariable Long orderId, @PathVariable Long lineOrderId);


    @ApiOperation(value = "Recuperer les lignes d'une commande article", notes = "Cette methode permet de recuperer les lignes d'une  commande client", response = OrderCustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Les lignes  de la commande a ete rupererent avec succes"),

    })
    @GetMapping(ORDER_CUSTOMER + "/line_orders/{orderId}")
    ResponseEntity<List<LineOrderCustomerDto>> findAllLineOrderCustomerByOrderCustomerId(@PathVariable Long orderId);


}
