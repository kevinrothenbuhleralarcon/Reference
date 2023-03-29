package ch.kra.authentication;

import ch.kra.authentication.model.Role;
import ch.kra.authentication.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@AllArgsConstructor
@SpringBootApplication
@PropertySource("classpath:jwt.properties")
public class AuthenticationApplication implements CommandLineRunner {
	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.save(new Role(Role.ROLE_USER));
		roleRepository.save(new Role(Role.ROLE_ADMIN));
		roleRepository.save(new Role(Role.ROLE_MODERATOR));
	}
}
