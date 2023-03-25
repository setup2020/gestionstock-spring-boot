package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.domain.Role;
import cm.aupas.gestionstock.dto.ResponsePaginationDto;
import cm.aupas.gestionstock.dto.RoleDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.RoleRepository;
import cm.aupas.gestionstock.services.RoleService;
import cm.aupas.gestionstock.validators.RoleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        List<String> errors= RoleValidator.validator(roleDto);

        if(!errors.isEmpty()){
            log.warn("Role is not valid {}",roleDto);
            throw new InvalidEntityException("Le role n'est pas valide", ErrorCode.ERROR_422,errors);
        }
        return RoleDto.mapToDTO(roleRepository.save(RoleDto.mapToEntity(roleDto)) );
    }

    @Override
    public RoleDto findById(Long id) {
        if(id==null){
            log.error("Role ID is null");
            return null;

        }
        return roleRepository.findById(id).map(RoleDto::mapToDTO).orElseThrow(()-> new EntityNotFoundException(
                "Aucun rôle avec l'ID "+id+" a ete trouvé dans la BD",ErrorCode.ERROR_404
        ));
    }

    @Override
    public ResponsePaginationDto<RoleDto> findAll(int page, int size, List<String> sort) {
        ResponsePaginationDto<RoleDto> paginationDto=new ResponsePaginationDto<>();
        Pageable pageable = PageRequest.of(page, size, ResponsePaginationDto.sort(sort));
        Page<Role> roles = roleRepository.findAll(pageable);
        paginationDto.setSize(roles.getSize());
        paginationDto.setTotal(roles.getTotalElements());
        paginationDto.setTotalPage(roles.getTotalPages());
        paginationDto.setPage(page);
        // get content for page object
        List<Role> rolesList = roles.getContent();
        paginationDto.setContent(rolesList.stream().map(RoleDto::mapToDTO).collect(Collectors.toList()));

        return paginationDto;
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Role ID is null");
            return;
        }


        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        return null;
    }
}
