package cm.aupas.gestionstock.services;


import cm.aupas.gestionstock.dto.CategoryDto;

public interface CategoryService  extends  DefaultService<CategoryDto,Long> {

    public CategoryDto findByCode(String code);

}
