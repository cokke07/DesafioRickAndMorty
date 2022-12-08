package cl.cokke.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.entity.Personajes;
import cl.cokke.service.PersonajesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
public class PersonajeController {

	@Autowired
	private PersonajesService personajeService;
	
	//Documentacion de Path
			@Operation(
					security = {@SecurityRequirement(name = "Bearer") }
					,summary = "Listar personajes desde la API local"
					, description = "<h3>Endpoint encargado de listar los personajes desde la base de datos</h3>")
			@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", description = "OK", content = {
						@Content(mediaType = "application/json", schema = 
								@Schema(implementation = Personajes.class)) }) })	
	//Fin documentacion de path
	@GetMapping("character")
	public ResponseEntity<List<Personajes>> buscarTodos(){
		
		if(personajeService.buscarTodos().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Personajes>>(personajeService.buscarTodos(), HttpStatus.OK);
		}
	}
	
	//Documentacion de Path
			@Operation(
					security = {@SecurityRequirement(name = "Bearer") }
					,summary = "Buscar personajes por ID"
					, description = "<h3>Endpoint encargado de buscar personaje por ID, colocandolo en el path</h3>")
			@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", description = "OK", content = {
						@Content(mediaType = "application/json", schema = 
								@Schema(implementation = Personajes.class)) }) })	
	//Fin documentacion de path
	@GetMapping("/character/{id}")
	public ResponseEntity<Personajes> buscarPorId(@PathVariable(name= "id") Integer id){
		Optional<Personajes> pEncontrado = personajeService.buscarPorId(id);
		
		if(pEncontrado.isPresent()) {
			System.out.println(pEncontrado);
			return new ResponseEntity<>(pEncontrado.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	//Documentacion de Path
			@Operation(
					security = {@SecurityRequirement(name = "Bearer") }
					,summary = "Actualizar personaje desde la API local con el ID"
					, description = "<h3>Endpoint encargado de actualizar al personaje pasando el path del id</h3>")
			@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", description = "OK", content = {
						@Content(mediaType = "application/json", schema = 
								@Schema(implementation = Personajes.class)) }) })	
	//Fin documentacion de path
	@PutMapping("/character/{id}")
	public ResponseEntity<Personajes> actualizaPersonaje(@RequestBody Personajes p, @PathVariable("id") Integer id ){
		Optional<Personajes> pEncontrado = personajeService.buscarPorId(id);
		
		if(pEncontrado.isPresent()) {
			pEncontrado.get().setName(p.getName());
			pEncontrado.get().setGender(p.getGender());
			pEncontrado.get().setStatus(p.getStatus());
			pEncontrado.get().setImage(p.getImage());
			return new ResponseEntity<>(personajeService.actualizar(pEncontrado.get()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	//Documentacion de Path
			@Operation(
					security = {@SecurityRequirement(name = "Bearer") }
					,summary = "Eliminar personajes desde la API local"
					, description = "<h3>Endpoint encargado de eliminar personaje pasando en el path el id</h3>")
			@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", description = "OK", content = {
						@Content(mediaType = "application/json", schema = 
								@Schema(implementation = Personajes.class)) }) })	
	//Fin documentacion de path
	@DeleteMapping("/character/{id}")
	public ResponseEntity<HttpStatus> eliminarPersonaje(@PathVariable("id") Integer id){
		Optional<Personajes> pEncontrado = personajeService.buscarPorId(id);
		
		if(pEncontrado.isPresent()) {
			return new ResponseEntity<>(personajeService.eliminarPersonaje(id), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}
	
	//Documentacion de Path
			@Operation(
					security = {@SecurityRequirement(name = "Bearer") }
					,summary = "Buscar personajes desde la API local por Genero o Estado"
					, description = "<h3>Endpoint encargado de buscar personajes pasando el parametro de Gender o Status</h3>")
			@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", description = "OK", content = {
						@Content(mediaType = "application/json", schema = 
								@Schema(implementation = Personajes.class)) }) })	
	//Fin documentacion de path		
	@GetMapping("/character/")
	public ResponseEntity<List<Personajes>> buscarPorGenderOrStatus(@RequestParam("texto") String texto) {
		 List<Personajes> personajesEncontrados =personajeService.findByStatusOrGender(texto);
		 System.out.println(personajesEncontrados);
		return new ResponseEntity<>(personajesEncontrados, HttpStatus.OK);		
	}
	
	//Documentacion de Path
			@Operation(
					security = {@SecurityRequirement(name = "Bearer") }
					,summary = "Buscar personajes desde la API local por varios Ids"
					, description = "<h3>Endpoint encargado de buscar varios personajes pasando mas de 1 ID en el path</h3>")
			@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", description = "OK", content = {
						@Content(mediaType = "application/json", schema = 
								@Schema(implementation = Personajes.class)) }) })	
	//Fin documentacion de path		
	@GetMapping("/character/ids/{id}/{id2}")
	public ResponseEntity<List<?>> buscarPorVariosId(
			@PathVariable(name= "id") Integer id,@PathVariable(name= "id2") Integer id2){
		List<Optional<Personajes>> pEncontrados = personajeService.buscarPorVariosId(id, id2);
		System.out.println(pEncontrados.toString());
		return new ResponseEntity<>(pEncontrados,HttpStatus.OK);
	}
	
}
