package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.UserRepository;
import cm.aupas.gestionstock.services.UserService;
import cm.aupas.gestionstock.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = UserValidator.validator(userDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", userDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCode.ERROR_422, errors);

        }
        return UserDto.fromEntity(
                userRepository.save(UserDto.toEntity(userDto))
        );
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null) {
            log.error("User ID is null");

            return null;
        }
        return userRepository.findById(id).
                map(UserDto::fromEntity).orElseThrow(
                        () -> new EntityNotFoundException("Aucun utitlisateur avec l'ID=" + id + "n'ete trouve dans la BDD ", ErrorCode.ERROR_404));

    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::fromEntity).collect(Collectors.toList());
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
}
