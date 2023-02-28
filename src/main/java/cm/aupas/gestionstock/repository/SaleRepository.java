package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
