package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.domain.OrderCustomer;
import cm.aupas.gestionstock.dto.OrderCustomerDto;

public interface OrderCustomerService extends DefaultService<OrderCustomerDto,Long>{
    OrderCustomerDto findByReference(String reference);
}
