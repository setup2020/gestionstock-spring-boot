package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.dto.OrderSupplierDto;

public interface OrderSupplierService  extends  DefaultService<OrderSupplierDto,Long>{

    OrderSupplierDto findByReference(String reference);
}
