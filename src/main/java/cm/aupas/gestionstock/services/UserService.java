package cm.aupas.gestionstock.services;


import cm.aupas.gestionstock.dto.ChangePasswordDto;
import cm.aupas.gestionstock.dto.LoginDto;
import cm.aupas.gestionstock.dto.UserDto;

public interface UserService extends DefaultService<UserDto,Long>{

    LoginDto findByUserName(String username);
    UserDto findByUserName1(String username);

    UserDto changerMotDePasse(ChangePasswordDto dto);

}
