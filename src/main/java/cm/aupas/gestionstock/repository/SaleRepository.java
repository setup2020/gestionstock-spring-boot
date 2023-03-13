package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

    Optional<Sale> findByReference(String reference);

}
