package equilibrium.br.processo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
	            .info(new Info()
	                    .title("API de Gestão de Processos Judiciais")
	                    .version("1.0")
	                    .description("Documentação da API da Equilibrium Web para gestão de processos judiciais.")
	                    .contact(new Contact()
	                            .name("Equipe de Suporte")
	                            .email("suporte@equilibriumweb.com"))
	            )
	            .tags(Arrays.asList(
	                    new Tag().name("Processos").description("Endpoints para gerenciar processos judiciais"),
	                    new Tag().name("Gerrenciar Tipos de Processos").description("Endpoints para gerenciar Processos")
	            ));
	}
}