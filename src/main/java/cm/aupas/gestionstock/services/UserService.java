package cm.aupas.gestionstock.services;


import cm.aupas.gestionstock.dto.ChangePasswordDto;
import cm.aupas.gestionstock.dto.UserDto;

public interface UserService extends DefaultService<UserDto,Long>{

    UserDto findByEmail(String email);

    UserDto changerMotDePasse(ChangePasswordDto dto);

}
