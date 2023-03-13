package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.domain.OrderCustomer;
import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.OrderCustomerDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderCustomerService extends DefaultService<OrderCustomerDto,Long>{
    OrderCustomerDto findByReference(String reference);
    OrderCustomerDto updateStatusOrder(Long id, StatusOrder statusOrder);
    OrderCustomerDto updateQuantityOrder(Long id, Long lineOrderId, BigDecimal quantity);


    OrderCustomerDto updateClient(Long orderId,Long customerId);

    OrderCustomerDto updateArticle(Long orderId,Long lineOrderId,Long articleId);
    // Delete ligne ligne commande client
    OrderCustomerDto deleteArticle(Long orderId,Long lineOrderId);
    List<LineOrderCustomerDto> findAllLineOrderCustomerByOrderCustomerId(Long orderId);

}
