package cl.cokke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class config {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	 @Bean
	 public HttpHeaders httpHeaders() {
		 return new HttpHeaders();
	 }
	 
	 @Bean
	 public OpenAPI customOpenAPI() {
			return new OpenAPI()
					.components(new Components()
							.addSecuritySchemes("Bearer",
									new SecurityScheme()
									.type(SecurityScheme.Type.HTTP)
									.scheme("Bearer")
									.bearerFormat("JWT")))
					.info(new Info()
							.title("Documentación API RickAndMorty")
							.description("Este es un desafio para crear una API de personajes de RickAndMorty"));

		}
}
