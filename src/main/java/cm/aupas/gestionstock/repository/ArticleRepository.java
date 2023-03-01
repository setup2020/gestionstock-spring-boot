package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository <Article, Long> {

    Optional<Article> findByReference(String reference);
}
