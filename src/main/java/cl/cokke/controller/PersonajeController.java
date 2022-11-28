package cl.cokke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.service.CharacterService;
import cl.cokke.model.Character;

@RestController
public class PersonajeController {

	@Autowired
	private CharacterService personajeService;
	
	@GetMapping("/")
	public ResponseEntity<Character> obtenerCharacters( ){
		Character personajesResponse = personajeService.getAllCharacters();
		return new ResponseEntity<>(personajesResponse,HttpStatus.OK);
	}
}
