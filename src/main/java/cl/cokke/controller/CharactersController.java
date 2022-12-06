package cl.cokke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.model.Character;
import cl.cokke.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/")
public class CharactersController {

	@Autowired
	private CharacterService personajeService;
	
	//Documentacion de Path
		@Operation(
				security = {@SecurityRequirement(name = "Bearer") }
				,summary = "Traer personajes de API oficial"
				, description = "<h3>Endpoint encargado de cargar los personajes desde la API externa a una base de datos</h3>")
		@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK", content = {
					@Content(mediaType = "application/json", schema = 
							@Schema(implementation = Character.class)) }) })	
		//Fin documentacion de path
	@GetMapping("/upload")
	public ResponseEntity<Character> cargarPersonajes( ){
		Character personajesResponse = personajeService.uploadCharacter();
		return new ResponseEntity<>(personajesResponse,HttpStatus.OK);
	}
	
}
