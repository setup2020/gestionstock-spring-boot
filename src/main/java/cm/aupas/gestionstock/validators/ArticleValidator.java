package cm.aupas.gestionstock.validators;

import cm.aupas.gestionstock.dto.ArticleDto;
import cm.aupas.gestionstock.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validator(ArticleDto articleDto){
        List<String> errors=new ArrayList<>();
        if(articleDto==null || !StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("Veuillez renseigner la designation de l'article");
        }



        return errors;
    }
}
