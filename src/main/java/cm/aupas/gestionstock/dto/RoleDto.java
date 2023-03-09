package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.domain.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RoleDto {
    private Long id;
    private String roleName;


    private UserDto user;

    public static RoleDto fromEntity(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();

    }

    public static Role toEntity(RoleDto roleDto) {

        if (roleDto == null) {
            return null;
        }

        Role role1 = new Role();
        role1.setRoleName(roleDto.getRoleName());
        return role1;

    }
}