package equilibrium.br.processo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages={"equilibrium.br.processo.entity"}) 
@EnableJpaRepositories(basePackages={"equilibrium.br.processo.repository"}) 
@EnableWebMvc
@ComponentScan(basePackages = "equilibrium.br.processo.*") // ou o pacote correto
public class ProcessoJudicialApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessoJudicialApiApplication.class, args);
	}

}
