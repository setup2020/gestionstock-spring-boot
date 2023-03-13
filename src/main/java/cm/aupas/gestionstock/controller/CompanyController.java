package cm.aupas.gestionstock.controller;


import cm.aupas.gestionstock.controller.api.CompanyApi;
import cm.aupas.gestionstock.controller.api.CustomerApi;
import cm.aupas.gestionstock.dto.CompanyDto;
import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.services.CompanyService;
import cm.aupas.gestionstock.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController implements CompanyApi {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<CompanyDto> save(CompanyDto companyDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(companyService.save(companyDto)) ;
    }

    @Override
    public ResponseEntity<List<CompanyDto>> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(companyService.findAll());

    }

    @Override
    public void delete(Long id) {
        companyService.delete(id);
    }

    @Override
    public ResponseEntity<CompanyDto> findById(Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body( companyService.findById(id)) ;
    }
}
