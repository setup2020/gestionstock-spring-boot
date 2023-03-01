package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.LineOrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LineOrderCustomerRepository extends JpaRepository<LineOrderCustomer,Long> {
}
