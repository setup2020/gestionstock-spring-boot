package cm.aupas.gestionstock.repository;

import cm.aupas.gestionstock.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
