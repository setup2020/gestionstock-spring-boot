package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.MovementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MovementStockRepository extends JpaRepository<MovementStock,Long> {


    @Query("select sum(m.quantity) from MovementStock m where m.article.id=:articleId")
    BigDecimal stockRealArticle(@Param("articleId") Long articleId);

    List<MovementStock> findAllByArticleId(Long articleId);
}
