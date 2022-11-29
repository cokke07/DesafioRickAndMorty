package cl.cokke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

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
}
