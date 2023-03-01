package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.dto.CategoryDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.CategoryRepository;
import cm.aupas.gestionstock.services.CategoryService;
import cm.aupas.gestionstock.validators.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CategoryServiceImpl  implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto findByCode(String code) {
        if(StringUtils.hasLength(code)){
            log.error("Category Codee is null");
            return  null;
        }
        return categoryRepository.findByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune category avec le code="+code+ "n'ete trouve dans la BDD ", ErrorCode.ERROR_404))
                ;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors= CategoryValidator.validator(categoryDto);
        if(!errors.isEmpty()){
            log.error("Article is nt valid {}",categoryDto);
            throw new InvalidEntityException("La  Category n'est pas valide",ErrorCode.ERROR_422,errors);

        }
        return CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(categoryDto))
        );
    }

    @Override
    public CategoryDto findById(Long id) {
        if(id==null){
            log.error("Category ID is null");
            return null;

        }

        return  categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune category avec l'ID="+id+ "n'ete trouve dans la BDD ", ErrorCode.ERROR_404));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        if(id==null){
            log.error("Category ID is null");
        }

        categoryRepository.deleteById(id);

    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return null;
    }
}
