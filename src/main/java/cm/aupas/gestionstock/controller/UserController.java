package cm.aupas.gestionstock.controller;


import cm.aupas.gestionstock.controller.api.UserApi;
import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> save(UserDto userDto) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body( userService.save(userDto));
    }

    @Override
    public ResponseEntity<List<UserDto>> findAll() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findAll());
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public ResponseEntity<UserDto> findById(Long id) {
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findById(id));
    }
}
