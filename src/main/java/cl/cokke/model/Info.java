package cl.cokke.model;

import lombok.Data;

@Data
public class Info {

	private Integer count;
	private Integer page;
	private String next;
	private String prev;
}
