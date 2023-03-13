package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.LineSale;
import cm.aupas.gestionstock.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LineSaleRepository extends JpaRepository<LineSale,Long> {

    List<LineSale>findAllByArticleId(Long articleId);
}
