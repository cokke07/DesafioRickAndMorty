package cl.cokke.service;

import java.util.List;

import cl.cokke.entity.Personajes;

public interface PersonajesService {

	public List<Personajes> findAll();
	public Personajes guardar(Personajes ps);
	
}
