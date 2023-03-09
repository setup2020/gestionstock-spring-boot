package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.CustomerDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

public interface CustomerApi {

    @PostMapping(value = APP_ROOT+"/customers",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto save(@RequestBody CustomerDto customerDto);

    @GetMapping(value = APP_ROOT+"/customers",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/customers/{id}")
    void delete(@PathVariable Long id);

    @GetMapping(value = APP_ROOT+"/customers/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto findById(@PathVariable Long id);
}
