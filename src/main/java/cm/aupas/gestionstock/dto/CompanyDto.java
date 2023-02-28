package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;

@Data
@Builder
public class CompanyDto {
    private Long id;
    List<User> users;
    private String name;
    @Embedded
    private AddressDto address;
    private String codeFiscal;
    private String email;
    private String phone;
    private String webSite;
    private String description;
}
