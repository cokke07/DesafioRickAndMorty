package cl.cokke.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.cokke.entity.Personajes;
import cl.cokke.model.Character;
import cl.cokke.model.Result;

@Service
public class CharacterServiceImp implements CharacterService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders httpHeaders;
	
	@Autowired
	private PersonajesService personajeService;
	
	private static final String URL_API="https://rickandmortyapi.com/api/character";
	
	@Override
	public Character uploadCharacter() {
	
		  	httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

	        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

	        ResponseEntity<Character> response = restTemplate.exchange(URL_API, HttpMethod.GET,
	                entity, Character.class);
	        
	        List<Result> lista = new ArrayList<>();
	        
	        
	        lista = response.getBody().getResults();
	        
			
			for (Result result : lista) { Personajes p = new Personajes();
				p.setName(result.getName()); 
				p.setStatus(result.getStatus());
				p.setGender(result.getGender()); 
				p.setImage(result.getImage());
				personajeService.guardar(p);
			 }
			 
	        //System.out.println(response.getBody().getResults().get(19));
	        
	        return response.getBody();
	}



	@Override
	public void save(List<Result> listaResult) {
		// TODO Auto-generated method stub
		
	}
	
	

}
