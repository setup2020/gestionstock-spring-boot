package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
