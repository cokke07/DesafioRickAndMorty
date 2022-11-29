package cl.cokke.service;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cl.cokke.model.Character;
import cl.cokke.model.Result;

@Service
public class CharacterServiceImp implements CharacterService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders httpHeaders;
	
	
	
	private static final String URL_API="https://rickandmortyapi.com/api/character";
	
	@Override
	public Character getAllCharacters() {
	
		  	httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

	        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

	        ResponseEntity<Character> response = restTemplate.exchange(URL_API, HttpMethod.GET,
	                entity, Character.class);
	        
	        List<Result> lista = new ArrayList<>();
	        
	        lista = response.getBody().getResults();
	        System.out.println(lista.toString());
	        return response.getBody();
	}



	@Override
	public void save(List<Result> listaResult) {
		// TODO Auto-generated method stub
		
	}
	
	

}
