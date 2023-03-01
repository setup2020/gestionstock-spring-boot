package cm.aupas.gestionstock.validators;

import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidator {

    public static List<String> validator(CustomerDto customerDto){
        List<String> errors=new ArrayList<>();
        if(customerDto==null || !StringUtils.hasLength(customerDto.getLastName())){
            errors.add("Veuillez renseigner le nom du client");
        }



        return errors;
    }
}
