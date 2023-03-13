package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.LineOrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LineOrderCustomerRepository extends JpaRepository<LineOrderCustomer,Long> {

    List<LineOrderCustomer> findAllByOrderCustomerId(Long id);
}
