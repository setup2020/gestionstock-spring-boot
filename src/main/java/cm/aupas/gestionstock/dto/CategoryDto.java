package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String code;

    private String name;


    @JsonIgnore
    private List<ArticleDto> articles;

    // Category ->CategoryDto
    public static CategoryDto fromEntity(Category category){
        if(category==null){
            return null;
            // TODO
        }

        return  CategoryDto.builder()
                .code(category.getCode())
                .name(category.getName())
                .id(category.getId())
                .build();

    }

    // CategoryDto ->Category
    public static Category toEntity(CategoryDto categoryDto){
        if(categoryDto==null){
            return null;
            // T
            // ODO
        }

        Category category=new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setName(categoryDto.getName());

        return  category;

    }
}
