package br.com.alangaraujo.api.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.alangaraujo.api.dto.DocumentDTO;
import br.com.alangaraujo.domain.model.Document;

public class DocumentDTOMapper {
	
	public static List<DocumentDTO> toDto(List<Document> documents) {
		List<DocumentDTO> documentDtos = new ArrayList<>();
		
		for(Document document : documents) {
			documentDtos.add(DocumentDTO.builder()
					.extension(document.getExtension())
					.count(document.getCount())
					.lines(document.getLines())
					.bytes(document.getBytes())
					.build());
		}
		
		return documentDtos;
	}

}
