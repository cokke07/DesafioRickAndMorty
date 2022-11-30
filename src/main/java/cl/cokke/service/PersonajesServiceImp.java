package cl.cokke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.cokke.entity.Personajes;
import cl.cokke.repository.PersonajeRepository;

@Service
public class PersonajesServiceImp implements PersonajesService {

	@Autowired
	private PersonajeRepository personajeRepository;
	
	@Override
	public List<Personajes> findAll() {
		// TODO Auto-generated method stub
		return personajeRepository.findAll();
	}

	@Override
	public Personajes guardar(Personajes ps) {
		
		return personajeRepository.save(ps);
	}

}
