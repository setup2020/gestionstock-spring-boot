package cm.aupas.gestionstock.controller;

import cm.aupas.gestionstock.controller.api.CategoryApi;
import cm.aupas.gestionstock.dto.CategoryDto;
import cm.aupas.gestionstock.dto.SimplePage;
import cm.aupas.gestionstock.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByReference(String reference) {
        return categoryService.findByCode(reference);
    }

    @Override
    public ResponseEntity<SimplePage<CategoryDto>> findAll(String name, Pageable pageable) {



        return ResponseEntity.ok().body(categoryService.findAll(name, pageable));
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }

    @Override
    public Long count() {
        return categoryService.count();
    }
}
