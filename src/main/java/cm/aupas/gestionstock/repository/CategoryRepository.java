package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Optional<Category> findByCode(String code);

    Page<Category> findAllByNameContains(String name, Pageable pageable);

}
