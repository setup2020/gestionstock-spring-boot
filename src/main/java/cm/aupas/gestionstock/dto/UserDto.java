package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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


    private List<RoleDto> roles;


    public static UserDto fromEntity(User user){
        if(user==null){
            return  null;
        }
        return  UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(AddressDto.fromEntity(user.getAddress()))
                .photo(user.getPhoto())

                .roles( user.getRoles()!=null?
                        user.getRoles().stream().map(RoleDto::mapToDTO).collect(Collectors.toList()):null)
                .build();
    }

    public static User toEntity(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(AddressDto.toEntity(userDto.getAddress()));
        user.setDateBirth(userDto.getDateBirth());
        user.setEmail(userDto.getEmail());

        user.setRoles(userDto.getRoles()!=null?userDto.getRoles().stream().map(RoleDto::mapToEntity).collect(Collectors.toList()):null);
        user.setPhoto(userDto.getPhoto());
        return user;
    }


}
