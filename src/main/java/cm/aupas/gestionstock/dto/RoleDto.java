package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Article;
import cm.aupas.gestionstock.domain.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private String status;


  //  private UserDto user;

    public static RoleDto mapToDTO(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .status(role.getStatus())
                .build();

    }

    public static Role mapToEntity(RoleDto roleDto) {

        if (roleDto == null) {
            return null;
        }


        Role role1 = new Role();
        role1.setName(roleDto.getName());
        role1.setStatus(roleDto.getStatus());
        return role1;

    }
}