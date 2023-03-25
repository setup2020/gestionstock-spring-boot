package cm.aupas.gestionstock.services;


import cm.aupas.gestionstock.dto.CategoryDto;
import cm.aupas.gestionstock.dto.SimplePage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService  {

    public CategoryDto findByCode(String code);

    public Long count();

    SimplePage<CategoryDto> findAll(String name, Pageable pageable);

    CategoryDto    save(CategoryDto t);
    CategoryDto findById(Long id);

    void delete(Long id);

}
