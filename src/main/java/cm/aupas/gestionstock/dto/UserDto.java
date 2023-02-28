package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UserDto {

    private Long id;
    private String firstName;

    private String lastName;

    private Instant dateBirth;


    private String password;
    @Embedded
    private AddressDto address;

    private String email;

    private String photo;


    private CompanyDto company;


    private List<RoleDto> roles;
}
