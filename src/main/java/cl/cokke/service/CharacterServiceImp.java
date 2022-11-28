package cl.cokke.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.cokke.model.Character;

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

	        return response.getBody();
	}

}
