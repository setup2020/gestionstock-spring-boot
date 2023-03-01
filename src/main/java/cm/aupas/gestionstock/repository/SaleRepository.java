package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
}
