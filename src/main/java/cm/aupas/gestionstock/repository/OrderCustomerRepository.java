package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.OrderCustomer;
import cm.aupas.gestionstock.dto.OrderCustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderCustomerRepository extends JpaRepository<OrderCustomer,Long> {
    Optional<OrderCustomer> findByReference(String reference);
}
