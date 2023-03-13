package cm.aupas.gestionstock.validators;

import cm.aupas.gestionstock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {
    public static List<String> validator(CategoryDto categoryDto){
        List<String> errors=new ArrayList<>();
        if(categoryDto==null || !StringUtils.hasLength(categoryDto.getCode())){
            errors.add("Veuillez renseigner le code de la category");
        }

        return errors;
    }
}
