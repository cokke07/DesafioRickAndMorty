package cl.cokke.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class Character {

	Info info;
	List<Result> results;
}
