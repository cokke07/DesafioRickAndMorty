package cl.cokke.model;

import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@Data
@JsonIgnoreType
public class Character {

	Info info;
	List<Result> results;
}
