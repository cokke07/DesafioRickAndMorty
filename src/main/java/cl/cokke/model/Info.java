package cl.cokke.model;



import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@Data
@JsonIgnoreType
public class Info {

	private Integer count;
	private Integer page;
	private String next;
	private String prev;
}
