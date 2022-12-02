package cl.cokke.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cl.cokke.entity.Personajes;
import cl.cokke.repository.PersonajeRepository;

@Service
public class PersonajesServiceImp implements PersonajesService {

	@Autowired
	private PersonajeRepository personajeRepository;
	
	@Override
	public List<Personajes> cargarData() {
			return personajeRepository.findAll();
	}

	@Override
	public Personajes guardar(Personajes ps) {	
		return personajeRepository.save(ps);
	}

	@Override
	public Personajes actualizar(Personajes ps) {	
		return personajeRepository.save(ps);
	}

	@Override
	public List<Personajes> buscarTodos() {
		return personajeRepository.findAll();
	}

	@Override
	public Optional<Personajes> buscarPorId(Integer id) {
		return personajeRepository.findById(id);
	}

	@Override
	public HttpStatus eliminarPersonaje(Integer id) {
		personajeRepository.deleteById(id);
		return null;
	}

	@Override
	public Personajes findByStatusOrGender(String textoBuscado) {
		Personajes encontrado = personajeRepository.findByStatusOrGender(textoBuscado, textoBuscado);
		
		return encontrado;
	}

}