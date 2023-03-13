package cm.aupas.gestionstock.controller;

import cm.aupas.gestionstock.controller.api.OrderSupplierApi;
import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.LineOrderSupplierDto;
import cm.aupas.gestionstock.dto.OrderSupplierDto;
import cm.aupas.gestionstock.services.OrderSupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class OrderSupplierController implements OrderSupplierApi {
    private final OrderSupplierService orderSupplierService;

    public OrderSupplierController(OrderSupplierService orderSupplierService) {
        this.orderSupplierService = orderSupplierService;
    }

    @Override
    public ResponseEntity<OrderSupplierDto> save(OrderSupplierDto orderSupplierDto) {
        // return ResponseEntity.ok(orderCustomerService.save(orderCustomerDto));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderSupplierService.save(orderSupplierDto));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> findById(Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderSupplierService.findById(id));

    }

    @Override
    public ResponseEntity<OrderSupplierDto> findByReference(String reference) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderSupplierService.findByReference(reference));

    }

    @Override
    public ResponseEntity<List<OrderSupplierDto>> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderSupplierService.findAll());

    }

    @Override
    public void delete(Long id) {
        orderSupplierService.delete(id);
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateStatusOrder(Long id, StatusOrder status) {
        return ResponseEntity.ok(orderSupplierService.updateStatusOrder(id, status));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateQuantityOrder(Long id, Long lineOrderCustomerId, BigDecimal quantity) {
        return ResponseEntity.ok(orderSupplierService.updateQuantityOrder(id, lineOrderCustomerId, quantity));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateSupplierOrder(Long orderSupplierId, Long supplierId) {
        return ResponseEntity.ok(orderSupplierService.updateSupplier(orderSupplierId, supplierId));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateArticle(Long id, Long lineOrderId, Long articleId) {
        return ResponseEntity.ok(orderSupplierService.updateArticle(id, lineOrderId, articleId));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> deleteArticle(Long orderId, Long lineOrderId) {
        return ResponseEntity.ok(orderSupplierService.deleteArticle(orderId, lineOrderId));
    }

    @Override
    public ResponseEntity<List<LineOrderSupplierDto>> findAllLineOrderSupplierByOrderSupplierId(Long orderId) {
        return ResponseEntity.ok(orderSupplierService.findAllLineOrderSupplierByOrderSupplierId(orderId));
    }
}
