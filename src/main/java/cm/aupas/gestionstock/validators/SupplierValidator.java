package cm.aupas.gestionstock.validators;

import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.dto.SupplierDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SupplierValidator {

    public static List<String> validator(SupplierDto supplierDto){
        List<String> errors=new ArrayList<>();
        if(supplierDto==null || !StringUtils.hasLength(supplierDto.getLastName())){
            errors.add("Veuillez renseigner le nom du fournisseur");
        }



        return errors;
    }
}
