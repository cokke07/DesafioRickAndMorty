package cl.cokke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.cokke.entity.Personajes;

@Repository
public interface PersonajeRepository extends JpaRepository<Personajes, Integer> {

}
