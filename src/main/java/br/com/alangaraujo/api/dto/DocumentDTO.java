package br.com.alangaraujo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTO {

	private String extension;
	private Integer count;
	private Integer lines;
	private Long bytes;
	
}
