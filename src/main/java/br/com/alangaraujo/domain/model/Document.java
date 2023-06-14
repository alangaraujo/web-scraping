package br.com.alangaraujo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Document {
	
	private String extension;
	private Integer count;
	private Integer lines;
	private Long Bytes;
	
}
