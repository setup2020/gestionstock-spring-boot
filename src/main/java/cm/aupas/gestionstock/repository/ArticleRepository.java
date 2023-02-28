package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository <Article, Long> {
    Optional<Article> findArticleByReferenceArticle(String reference);
}
