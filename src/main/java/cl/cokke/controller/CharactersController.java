package cl.cokke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.model.Character;
import cl.cokke.service.CharacterService;

@RestController
@RequestMapping("/")
public class CharactersController {

	@Autowired
	private CharacterService personajeService;
	
	@GetMapping("/upload")
	public ResponseEntity<Character> cargarPersonajes( ){
		Character personajesResponse = personajeService.uploadCharacter();
		return new ResponseEntity<>(personajesResponse,HttpStatus.OK);
	}
	
}
