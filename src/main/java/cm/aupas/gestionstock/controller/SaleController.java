package cm.aupas.gestionstock.controller;


import cm.aupas.gestionstock.controller.api.CustomerApi;
import cm.aupas.gestionstock.controller.api.SaleApi;
import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.dto.SaleDto;
import cm.aupas.gestionstock.services.CustomerService;
import cm.aupas.gestionstock.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleController implements SaleApi {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @Override
    public ResponseEntity<SaleDto> save(SaleDto saleDto) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(saleService.save(saleDto));
    }

    @Override
    public ResponseEntity<List<SaleDto>> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(saleService.findAll());
    }

    @Override
    public void delete(Long id) {
        saleService.delete(id);
    }

    @Override
    public ResponseEntity<SaleDto> findById(Long id) {
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(saleService.findById(id));

    }

    @Override
    public ResponseEntity<SaleDto> findByReference(String reference) {
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(saleService.findByReference(reference));
    }
}
