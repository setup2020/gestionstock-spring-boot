package cm.aupas.gestionstock.controller;


import cm.aupas.gestionstock.controller.api.CustomerApi;
import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.services.CustomerService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @Override
    public void delete(Long id) {
            customerService.delete(id);
    }

    @Override
    public CustomerDto findById(Long id) {
        return customerService.findById(id);
    }

    @Override
    public Long count() {
        return customerService.count();
    }
}
