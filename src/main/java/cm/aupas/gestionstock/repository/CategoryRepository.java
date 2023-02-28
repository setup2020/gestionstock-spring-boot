package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
