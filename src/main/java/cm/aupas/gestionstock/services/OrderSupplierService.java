package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.domain.enums.StatusOrder;
import cm.aupas.gestionstock.dto.LineOrderCustomerDto;
import cm.aupas.gestionstock.dto.LineOrderSupplierDto;
import cm.aupas.gestionstock.dto.OrderCustomerDto;
import cm.aupas.gestionstock.dto.OrderSupplierDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderSupplierService  extends  DefaultService<OrderSupplierDto,Long>{

    OrderSupplierDto findByReference(String reference);
    OrderSupplierDto updateStatusOrder(Long id, StatusOrder statusOrder);

    OrderSupplierDto updateQuantityOrder(Long id, Long lineOrderId, BigDecimal quantity);

    OrderSupplierDto updateSupplier(Long orderId,Long supplierId);

    OrderSupplierDto updateArticle(Long orderId,Long lineOrderId,Long articleId);
    OrderSupplierDto deleteArticle(Long orderId,Long lineOrderId);
    List<LineOrderSupplierDto> findAllLineOrderSupplierByOrderSupplierId(Long orderId);
}
