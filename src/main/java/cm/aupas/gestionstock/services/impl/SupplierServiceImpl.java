package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.dto.SupplierDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.SupplierRepository;
import cm.aupas.gestionstock.services.SupplierService;
import cm.aupas.gestionstock.validators.SupplierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierDto save(SupplierDto supplierDto) {
        List<String> errors= SupplierValidator.validator(supplierDto);
        if(!errors.isEmpty()){
            log.error("Supplier is not valid {}",supplierDto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCode.ERROR_422,errors);
        }
        return SupplierDto.fromEntity(supplierRepository.save(SupplierDto.toEntity(supplierDto)));
    }

    @Override
    public SupplierDto findById(Long id) {
        if(id==null){
            log.error("Supplier Is is NULL");
            return null;
        }
        return supplierRepository.findById(id).map(SupplierDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucun fournisseur avec l'ID= "+id+" n'ete trouve dans la BDD",ErrorCode.ERROR_404));
    }

    @Override
    public List<SupplierDto> findAll() {
        return supplierRepository.findAll().stream().map(SupplierDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Supplier ID is null");
            return;
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public SupplierDto update(SupplierDto supplierDto) {
        return null;
    }
}
