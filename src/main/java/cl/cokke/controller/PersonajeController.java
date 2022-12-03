package cl.cokke.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.entity.Personajes;
import cl.cokke.service.PersonajesService;

@RestController
@RequestMapping("/api")
public class PersonajeController {

	@Autowired
	private PersonajesService personajeService;
	
	@GetMapping("character")
	public ResponseEntity<List<Personajes>> buscarTodos(){
		
		if(personajeService.buscarTodos().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Personajes>>(personajeService.buscarTodos(), HttpStatus.OK);
		}
	}
	
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
	
	@DeleteMapping("/character/{id}")
	public ResponseEntity<HttpStatus> eliminarPersonaje(@PathVariable("id") Integer id){
		Optional<Personajes> pEncontrado = personajeService.buscarPorId(id);
		
		if(pEncontrado.isPresent()) {
			return new ResponseEntity<>(personajeService.eliminarPersonaje(id), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@GetMapping("/character/")
	public ResponseEntity<List<Personajes>> buscarPorGenderOrStatus(@RequestParam("texto") String texto) {
		 List<Personajes> personajesEncontrados =personajeService.findByStatusOrGender(texto);
		 System.out.println(personajesEncontrados);
		return new ResponseEntity<>(personajesEncontrados, HttpStatus.OK);		
	}
	
	@GetMapping("/character/ids/{id}/{id2}")
	public ResponseEntity<List<?>> buscarPorVariosId(
			@PathVariable(name= "id") Integer id,@PathVariable(name= "id2") Integer id2){
		List<Optional<Personajes>> pEncontrados = personajeService.buscarPorVariosId(id, id2);
		System.out.println(pEncontrados.toString());
		return new ResponseEntity<>(pEncontrados,HttpStatus.OK);
	}
	
	
}
