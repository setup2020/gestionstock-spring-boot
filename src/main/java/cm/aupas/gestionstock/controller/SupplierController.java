package cm.aupas.gestionstock.controller;


import cm.aupas.gestionstock.controller.api.SupplierApi;
import cm.aupas.gestionstock.dto.SupplierDto;
import cm.aupas.gestionstock.services.SupplierService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController implements SupplierApi {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public SupplierDto save(SupplierDto supplierDto) {
        return supplierService.save(supplierDto);
    }

    @Override
    public List<SupplierDto> findAll() {
        return supplierService.findAll();
    }

    @Override
    public void delete(Long id) {
        supplierService.delete(id);
    }

    @Override
    public SupplierDto findById(Long id) {
        return supplierService.findById(id);
    }

    @Override
    public Long count() {
        return supplierService.count();
    }
}
