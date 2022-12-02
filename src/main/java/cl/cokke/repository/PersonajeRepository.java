package cl.cokke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.cokke.entity.Personajes;

@Repository
public interface PersonajeRepository extends JpaRepository<Personajes, Integer> {

	public Personajes findByStatusOrGender(String texto1, String Texto2);
}
