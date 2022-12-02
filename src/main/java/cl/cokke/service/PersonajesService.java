package cl.cokke.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import cl.cokke.entity.Personajes;

public interface PersonajesService {

	public List<Personajes> cargarData();
	public Personajes guardar(Personajes ps);
	public Personajes actualizar(Personajes ps);
	public List<Personajes> buscarTodos();
	public Optional<Personajes> buscarPorId(Integer id);
	public HttpStatus eliminarPersonaje(Integer id); 
	public List<Personajes> findByStatusOrGender(String textoBuscado);
	
}
