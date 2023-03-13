package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.dto.CategoryDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.services.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public  class CategoryServiceImplTest {

    @Autowired
    private CategoryService service;
    @Test
    public void shouldSaveCategoryWithSuccess(){
    CategoryDto expectedCategory=    CategoryDto.builder()
                .code("Category test")
                .name("Name test")
            .build();
    CategoryDto categoryDto=service.save(expectedCategory);
        Assertions.assertNotNull(categoryDto);
        Assertions.assertNotNull(categoryDto.getId());
        Assertions.assertEquals(expectedCategory.getCode(),categoryDto.getCode());
        Assertions.assertEquals(expectedCategory.getName(),categoryDto.getName());


    }


    @Test
    public void shouldUpdateCategoryWithSuccess(){
        CategoryDto expectedCategory=    CategoryDto.builder()
                .code("Category test")
                .name("Name test")
                .build();
        CategoryDto categoryDto=service.save(expectedCategory);
    CategoryDto categoryDtoToUpdate=categoryDto;
    categoryDtoToUpdate.setCode("Cat update");
    categoryDto=service.save(categoryDtoToUpdate);



        Assertions.assertNotNull(categoryDtoToUpdate);
        Assertions.assertNotNull(categoryDtoToUpdate.getId());
        Assertions.assertEquals(categoryDtoToUpdate.getCode(),categoryDto.getCode());
        Assertions.assertEquals(categoryDtoToUpdate.getName(),categoryDto.getName());


    }

    @Test
    public void shouldThrowInvalidEntityException(){
      CategoryDto expectedCategory=CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> service.save(expectedCategory));
        assertEquals(ErrorCode.ERROR_422,expectedException.getErrorCode());
        assertEquals(1,expectedException.getErrors().size());
        assertEquals("Veuillez renseigner le code de la category",expectedException.getErrors().get(0));

    }

    @Test
    public void shouldThrowEntityNotFoundException(){
        CategoryDto expectedCategory=CategoryDto.builder().build();

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> service.findById(0L));
        assertEquals(ErrorCode.ERROR_404,expectedException.getErrorCode());
        assertEquals("Aucune category avec l'ID=0 n'ete trouve dans la BDD",expectedException.getMessage());


    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2(){
       service.findById(0L);

    }


}