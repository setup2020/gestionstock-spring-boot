package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.dto.CompanyDto;
import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.CompanyRepository;
import cm.aupas.gestionstock.repository.CustomerRepository;
import cm.aupas.gestionstock.services.CompanyService;
import cm.aupas.gestionstock.services.CustomerService;
import cm.aupas.gestionstock.validators.CompanyValidator;
import cm.aupas.gestionstock.validators.CustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDto save(CompanyDto companyDto) {
        List<String> errors= CompanyValidator.validator(companyDto);
        if(!errors.isEmpty()){
            log.error("Company is not valid {}",companyDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCode.ERROR_422,errors);

        }
        return CompanyDto.fromEntity(
                companyRepository.save(CompanyDto.toEntity(companyDto))
        );
    }

    @Override
    public CompanyDto findById(Long id) {
        if(id==null){
            log.error("Customer ID is null");

           return  null;
        }
        return  companyRepository.findById(id).
                map(CompanyDto::fromEntity).orElseThrow(
                        ()->new EntityNotFoundException("Aucune entreprise avec l'ID="+id+ "n'ete trouve dans la BDD ", ErrorCode.ERROR_404));

    }

    @Override
    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream().map(CompanyDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Company ID is null");
        }

        companyRepository.deleteById(id);

    }

    @Override
    public CompanyDto update(CompanyDto customerDto) {
        return null;
    }
}
