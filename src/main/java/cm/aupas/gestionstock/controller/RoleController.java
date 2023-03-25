package cm.aupas.gestionstock.controller;

import cm.aupas.gestionstock.controller.api.RoleApi;
import cm.aupas.gestionstock.dto.ResponsePaginationDto;
import cm.aupas.gestionstock.dto.RoleDto;
import cm.aupas.gestionstock.services.RoleService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController implements RoleApi {

private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @Override
    public RoleDto findById(Long id) {
        return roleService.findById(id);
    }

    @Override
    public ResponsePaginationDto<RoleDto> findAll(int page, int size,List<String>sort) {
        return roleService.findAll(page,size,sort);
    }


    @Override
    public void delete(Long id) {
         roleService.delete(id);
    }
}
