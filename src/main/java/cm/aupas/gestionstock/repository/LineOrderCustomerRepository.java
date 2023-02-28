package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.LineOrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineOrderCustomerRepository extends JpaRepository<LineOrderCustomer,Long> {
}
