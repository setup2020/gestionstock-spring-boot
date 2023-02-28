package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
