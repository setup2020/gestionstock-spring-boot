package cm.aupas.gestionstock.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RoleDto {
    private Long id;
    private String roleName;


    private UserDto user;
}
