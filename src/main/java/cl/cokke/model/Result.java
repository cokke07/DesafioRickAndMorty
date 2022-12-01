package cl.cokke.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@Data
public class Result {

	private Integer id;
	private String name;
	private String status;
	//private String species;
	//private String type;
	private String gender;
	private String image;
	//private String url;
	//private Date created;
}
