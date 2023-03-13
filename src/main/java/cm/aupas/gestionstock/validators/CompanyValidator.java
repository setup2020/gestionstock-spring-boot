package cm.aupas.gestionstock.validators;

import cm.aupas.gestionstock.dto.CompanyDto;
import cm.aupas.gestionstock.dto.CustomerDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CompanyValidator {

    public static List<String> validator(CompanyDto companyDto){
        List<String> errors=new ArrayList<>();
        if(companyDto==null || !StringUtils.hasLength(companyDto.getName())){
            errors.add("Veuillez renseigner le nom du client");
        }



        return errors;
    }
}
