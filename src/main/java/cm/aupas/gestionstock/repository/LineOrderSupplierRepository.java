package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.LineOrderSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineOrderSupplierRepository extends JpaRepository<LineOrderSupplier,Long> {
}
