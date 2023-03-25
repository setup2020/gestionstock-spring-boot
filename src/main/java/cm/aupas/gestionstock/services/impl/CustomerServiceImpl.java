package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.dto.CustomerDto;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.exceptions.ErrorCode;
import cm.aupas.gestionstock.exceptions.InvalidEntityException;
import cm.aupas.gestionstock.repository.CustomerRepository;
import cm.aupas.gestionstock.services.CustomerService;
import cm.aupas.gestionstock.validators.CustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        List<String> errors=CustomerValidator.validator(customerDto);
        if(!errors.isEmpty()){
            log.error("Customer is not valid {}",customerDto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCode.ERROR_422,errors);

        }
        return CustomerDto.fromEntity(
                customerRepository.save(CustomerDto.toEntity(customerDto))
        );
    }

    @Override
    public CustomerDto findById(Long id) {
        if(id==null){
            log.error("Customer ID is null");

           return  null;
        }
        return  customerRepository.findById(id).
                map(CustomerDto::fromEntity).orElseThrow(
                        ()->new EntityNotFoundException("Aucun client avec l'ID="+id+ "n'ete trouve dans la BDD ", ErrorCode.ERROR_404));

    }

    @Override
    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(CustomerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id==null){
            log.error("Customer ID is null");
        }

        customerRepository.deleteById(id);

    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        return null;
    }

    @Override
    public Long count() {
        return customerRepository.count();
    }
}
