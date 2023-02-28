package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
