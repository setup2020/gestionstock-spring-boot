package cm.aupas.gestionstock.controller.api;

import cm.aupas.gestionstock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT+"/categories",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT+"/categories/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("id") Long id);

    @GetMapping(value = APP_ROOT+"/categories/reference/{reference}")
    CategoryDto findByReference(@PathVariable("reference") String reference);

    @GetMapping(value = APP_ROOT+"/categories",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/categories")
    void delete(@PathVariable Long id);
}
