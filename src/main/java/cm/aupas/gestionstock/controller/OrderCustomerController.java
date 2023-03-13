package cm.aupas.gestionstock.controller;

import cm.aupas.gestionstock.controller.api.OrderCustomerApi;
import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.OrderCustomerDto;
import cm.aupas.gestionstock.services.OrderCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class OrderCustomerController implements OrderCustomerApi {
    private  final OrderCustomerService orderCustomerService;

    public OrderCustomerController(OrderCustomerService orderCustomerService) {
        this.orderCustomerService = orderCustomerService;
    }

    @Override
    public ResponseEntity<OrderCustomerDto> save(OrderCustomerDto orderCustomerDto) {
       // return ResponseEntity.ok(orderCustomerService.save(orderCustomerDto));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderCustomerService.save(orderCustomerDto));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> findById(Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderCustomerService.findById(id));

    }

    @Override
    public ResponseEntity<OrderCustomerDto> findByReference(String reference) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderCustomerService.findByReference(reference));

    }

    @Override
    public ResponseEntity<List<OrderCustomerDto>> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderCustomerService.findAll());

    }

    @Override
    public void delete(Long id) {
        orderCustomerService.delete(id);
    }

    @Override
    public ResponseEntity<OrderCustomerDto> updateStatusOrder(Long id, StatusOrder statusOrder) {
        return ResponseEntity.ok(orderCustomerService.updateStatusOrder(id,statusOrder));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> updateQuantityOrder(Long id, Long lineOrderCustomer, BigDecimal quantity) {
        return ResponseEntity.ok(orderCustomerService.updateQuantityOrder(id,lineOrderCustomer,quantity));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> updateClientOrder(Long orderCustomerId, Long customerId) {
        return ResponseEntity.ok( orderCustomerService.updateClient(orderCustomerId,customerId));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> updateArticle(Long id, Long lineOrderId, Long articleId) {
        return  ResponseEntity.ok(orderCustomerService.updateArticle(id,lineOrderId,articleId));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> deleteArticle(Long orderId, Long lineOrderId) {
        return ResponseEntity.ok(orderCustomerService.deleteArticle(orderId,lineOrderId));
    }

    @Override
    public ResponseEntity<List<LineOrderCustomerDto>> findAllLineOrderCustomerByOrderCustomerId(Long orderId) {
        return ResponseEntity.ok(orderCustomerService.findAllLineOrderCustomerByOrderCustomerId(orderId));
    }
}
