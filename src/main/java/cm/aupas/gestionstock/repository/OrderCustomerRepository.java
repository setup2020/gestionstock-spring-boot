package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCustomerRepository extends JpaRepository<OrderCustomer,Long> {
}
