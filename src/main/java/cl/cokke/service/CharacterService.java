package cl.cokke.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import cl.cokke.model.Character;
import cl.cokke.model.Result;

public interface CharacterService {

	public Character uploadCharacter();
	public void save(List<Result> listaResult);
}
