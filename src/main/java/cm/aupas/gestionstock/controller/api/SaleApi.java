package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.OrderCustomerDto;
import cm.aupas.gestionstock.dto.SaleDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;
import static cm.aupas.gestionstock.utils.Constants.SALE;

@Api(SALE)
public interface SaleApi {

    @PostMapping(SALE)
    public ResponseEntity<SaleDto> save(@RequestBody SaleDto saleDto);
    @GetMapping(SALE+"/{id}")
    public  ResponseEntity<SaleDto> findById(@PathVariable Long id);

    @GetMapping(SALE+"/{reference}")
    public  ResponseEntity<SaleDto> findByReference(@PathVariable String reference);
    @GetMapping(SALE)
    public  ResponseEntity<List<SaleDto>> findAll();

    @DeleteMapping(SALE+"/{id}")
    public void delete(@PathVariable Long id);

}
