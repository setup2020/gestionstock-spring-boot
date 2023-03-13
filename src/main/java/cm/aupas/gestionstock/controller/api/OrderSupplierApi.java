package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.LineOrderSupplierDto;
import cm.aupas.gestionstock.dto.OrderSupplierDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import static cm.aupas.gestionstock.utils.Constants.ORDER_SUPPLIER;

@Api(ORDER_SUPPLIER)
public interface OrderSupplierApi {

    @PostMapping(ORDER_SUPPLIER)
    @ApiOperation(value = "Enregistrer une commande fournisseur par ID", notes = "Cette methode permet d'enregistrer ou modifier une commande fournisseur ", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur cree/ modifier"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    public ResponseEntity<OrderSupplierDto> save(@RequestBody OrderSupplierDto orderSupplierDto);

    @GetMapping(ORDER_SUPPLIER + "/{id}")
    @ApiOperation(value = "Rechercher une commande fournisseur ", notes = "Cette methode permet  rechercher une commande fournisseur", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commande fournisseur a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur  n'existe dans la BDD avec l'ID fourni")
    })
    public ResponseEntity<OrderSupplierDto> findById(@PathVariable Long id);

    @GetMapping(ORDER_SUPPLIER + "/{reference}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette methode permet  rechercher une commande fournisseur par la reference", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commande fournisseur a ete trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'existe dans la BDD avec la reference fourni")
    })
    public ResponseEntity<OrderSupplierDto> findByReference(@PathVariable String reference);

    @GetMapping(ORDER_SUPPLIER)
    @ApiOperation(value = "Renvoi la liste des commandes fournisseurs", notes = "Cette methode permet  rechercher et renvoyer la liste des commandes fournisseurs qui existe dans la BDD", responseContainer = "List<OrderSupplierDto.class>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes fournisseur / Une liste vide"),

    })

    public ResponseEntity<List<OrderSupplierDto>> findAll();

    @ApiOperation(value = "Supprimer une commande fournisseur ", notes = "Cette methode permet de supprimer une commande fournisseur par ID", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete supprimé"),

    })
    @DeleteMapping(ORDER_SUPPLIER + "/{id}")
    public void delete(@PathVariable Long id);


    @ApiOperation(value = "Changer l'etat de la commande fournisseur", notes = "Cette methode permet de changer l'etat de la commande fournisseur", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'etat de La commande fournisseur a ete changé avec succes"),

    })
    @PatchMapping(ORDER_SUPPLIER + "/{id}/status/{status}")
    ResponseEntity<OrderSupplierDto> updateStatusOrder(@PathVariable Long id, @PathVariable StatusOrder status);


    @ApiOperation(value = "Changer la quantité de la commande fournisseur", notes = "Cette methode permet de changer la quantité de la commande fournisseur", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La quantité de La commande fournisseur a ete changé avec succes"),

    })
    @PatchMapping(ORDER_SUPPLIER + "/{id}/line_order_suppliers/{lineOrderSupplierId}/quantity/{quantity}")
    ResponseEntity<OrderSupplierDto> updateQuantityOrder(@PathVariable Long id, @PathVariable Long lineOrderSupplierId, @PathVariable BigDecimal quantity);


    @ApiOperation(value = "Modifier le fournisseur de la commande", notes = "Cette methode permet de modifier le fournisseur de commande fournisseur", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur de la commande a ete modifier avec succes"),

    })

    @PatchMapping(ORDER_SUPPLIER + "/{orderSupplierId}/suppliers/{supplierId}")
    ResponseEntity<OrderSupplierDto> updateSupplierOrder(@PathVariable Long orderSupplierId, @PathVariable Long supplierId);


    @ApiOperation(value = "Modifier l'article de la commande", notes = "Cette methode permet de modifier l'article de commande fournisseur", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article de la commande a ete modifier avec succes"),

    })
    @PatchMapping(ORDER_SUPPLIER + "/{id}/line_order_suppliers/{lineOrderId}/articles/{articleId}")
    ResponseEntity<OrderSupplierDto> updateArticle(@PathVariable Long id, @PathVariable Long lineOrderId, @PathVariable Long articleId);

    @ApiOperation(value = "Supprimer l'article de la commande", notes = "Cette methode permet de supprimer l'article de commande fournisseur", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article de la commande a ete supprimé avec succes"),

    })
    @DeleteMapping(ORDER_SUPPLIER + "/delete/article/{orderId}/{lineOrderId}")
    ResponseEntity<OrderSupplierDto> deleteArticle(@PathVariable Long orderId, @PathVariable Long lineOrderId);


    @ApiOperation(value = "Recuperer les lignes d'une commande article", notes = "Cette methode permet de recuperer les lignes d'une  commande fournisseur", response = OrderSupplierDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Les lignes  de la commande a ete rupererent avec succes"),

    })
    @GetMapping(ORDER_SUPPLIER + "/line_orders/{orderId}")
    ResponseEntity<List<LineOrderSupplierDto>> findAllLineOrderSupplierByOrderSupplierId(@PathVariable Long orderId);
}
