package cm.aupas.gestionstock.validators;

import cm.aupas.gestionstock.dto.CategoryDto;
import cm.aupas.gestionstock.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {
    public static List<String> validator(RoleDto roleDto){
        List<String> errors=new ArrayList<>();
        if(roleDto==null || !StringUtils.hasLength(roleDto.getName())){
            errors.add("Veuillez renseigner le nom du rôle");
        }

        return errors;
    }
}
