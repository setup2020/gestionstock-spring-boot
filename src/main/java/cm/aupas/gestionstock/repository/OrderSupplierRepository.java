package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.OrderSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderSupplierRepository extends JpaRepository<OrderSupplier, Long> {

    Optional<OrderSupplier> findByReference(String reference);
}
