package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.OrderSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSupplierRepository  extends JpaRepository<OrderSupplier,Long> {
}
