package cm.aupas.gestionstock.validators;

import cm.aupas.gestionstock.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validator(UserDto userDto){
        List<String> errors=new ArrayList<>();
        if(userDto==null || !StringUtils.hasLength(userDto.getLastName())){
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }

        if(userDto==null || !StringUtils.hasLength(userDto.getPassword())){
            errors.add("Veuillez renseigner le mot de passe");
        }

        if(userDto==null || !StringUtils.hasLength(userDto.getUsername())){
            errors.add("Veuillez renseigner username");
        }



        return errors;
    }
}
