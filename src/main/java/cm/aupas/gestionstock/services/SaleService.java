package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.dto.SaleDto;

public interface SaleService extends DefaultService<SaleDto, Long> {
    SaleDto findByReference(String reference);

}
