package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.User;
import cm.aupas.gestionstock.dto.ChangePasswordDto;
import cm.aupas.gestionstock.dto.LoginDto;
import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.exceptions.InvalidOperationException;
import cm.aupas.gestionstock.repository.UserRepository;
import cm.aupas.gestionstock.services.UserService;
import cm.aupas.gestionstock.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = UserValidator.validator(userDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", userDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCode.ERROR_422, errors);

        }
        return UserDto.mapToDTO(
                userRepository.save(UserDto.mapToEntity(userDto))
        );
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null) {
            log.error("User ID is null");

            return null;
        }
        return userRepository.findById(id).
                map(UserDto::mapToDTO).orElseThrow(
                        () -> new EntityNotFoundException("Aucun utitlisateur avec l'ID=" + id + "n'ete trouve dans la BDD ", ErrorCode.ERROR_404));

    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Customer ID is null");
        }

        userRepository.deleteById(id);

    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public LoginDto findByUserName(String username) {
        return userRepository.findByUsername(username).map(
                LoginDto::mapToDTO
        ).orElseThrow(()->new EntityNotFoundException("Aucun utilisateur avec le username= "+username+" n'ete trouve dans la BDD0",ErrorCode.ERROR_404));
    }

    @Override
    public UserDto findByUserName1(String username) {
        return userRepository.findByUsername(username).map(
                UserDto::mapToDTO
        ).orElseThrow(()->new EntityNotFoundException("Aucun utilisateur avec le username= "+username+" n'ete trouve dans la BDD0",ErrorCode.ERROR_404));
    }

    @Override
    public UserDto changerMotDePasse(ChangePasswordDto dto) {
        validate(dto);
        Optional<User> userOptional=userRepository.findById(dto.getId());

        if(userOptional.isEmpty()){
            log.warn("Aucun utilisateur n'a ete trouv avec L'ID "+dto.getId());
            throw new EntityNotFoundException("Aucun utilisateur n'a ete trouv avec L'ID "+dto.getId(),ErrorCode.ERROR_404);
        }
        User user=userOptional.get();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserDto.mapToDTO(
                userRepository.save(user)
        );
    }

    private void validate(ChangePasswordDto dto) {
        if (dto == null) {
            log.warn("Impossible de modifier le mot de passe avec un objet NULL");
            throw new InvalidOperationException("Aucune information n'a ete fourni pour pouvoir changer le mot de passe",
                    ErrorCode.ERROR_422);
        }
        if (dto.getId() == null) {
            log.warn("Impossible de modifier le mot de passe avec un ID NULL");
            throw new InvalidOperationException("ID utilisateur null:: Impossible de modifier le mote de passe",
                    ErrorCode.ERROR_422);
        }
        if (!StringUtils.hasLength(dto.getPassword()) || !StringUtils.hasLength(dto.getConfirmPassword())) {
            log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
            throw new InvalidOperationException("Mot de passe utilisateur null:: Impossible de modifier le mote de passe",
                    ErrorCode.ERROR_422);
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            log.warn("Impossible de modifier le mot de passe avec deux mots de passe different");
            throw new InvalidOperationException("Mots de passe utilisateur non conformes:: Impossible de modifier le mote de passe",
                    ErrorCode.ERROR_422);
        }
    }
}
