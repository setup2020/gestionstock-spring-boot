package cm.aupas.gestionstock.services.impl;

import cm.aupas.gestionstock.dto.OrderCustomerDto;
import cm.aupas.gestionstock.repository.OrderCustomerRepository;
import cm.aupas.gestionstock.services.OrderCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class OrderCustomerServiceImpl implements OrderCustomerService {

    private final OrderCustomerRepository orderCustomerRepository;

    public OrderCustomerServiceImpl(OrderCustomerRepository orderCustomerRepository) {
        this.orderCustomerRepository = orderCustomerRepository;
    }

    @Override
    public OrderCustomerDto save(OrderCustomerDto orderCustomerDto) {
        return null;
    }

    @Override
    public OrderCustomerDto findById(Long id) {
        return null;
    }

    @Override
    public List<OrderCustomerDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OrderCustomerDto update(OrderCustomerDto orderCustomerDto) {
        return null;
    }

    @Override
    public OrderCustomerDto findByReference(String reference) {
        return null;
    }
}
