package cm.aupas.gestionstock;

import cm.aupas.gestionstock.config.RsaKeysConfig;
import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)

public class GestionstockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionstockApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(UserService userService,PasswordEncoder passwordEncoder){
		return args -> {
			userService.save(UserDto.builder().username("pascal237").lastName("pascal").email("pascal@gmail.com").password(passwordEncoder.encode("123")).build());

		};
	}

}
