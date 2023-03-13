package cm.aupas.gestionstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class GestionstockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionstockApplication.class, args);
	}

}
