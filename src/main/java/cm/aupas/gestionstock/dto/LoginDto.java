package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private String username;
    private String password;
    private List<RoleDto> roles;

    public static LoginDto mapToDTO(User user){
        if(user==null){
            return  null;
        }
        return  LoginDto.builder()

                .username(user.getUsername())
                .password(user.getPassword())


                .roles( user.getRoles()!=null?
                        user.getRoles().stream().map(RoleDto::mapToDTO).collect(Collectors.toList()):null)
                .build();
    }


}
