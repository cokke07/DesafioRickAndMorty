package cl.cokke.model;



import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@JsonIgnoreType
@Data
public class Info {

	private Integer count;
	private Integer page;
	private String next;
	private String prev;
}
