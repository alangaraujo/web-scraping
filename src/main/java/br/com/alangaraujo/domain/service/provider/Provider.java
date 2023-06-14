package br.com.alangaraujo.domain.service.provider;

import java.util.List;

import br.com.alangaraujo.domain.model.Document;

public interface Provider {

	List<Document> searchDocuments(String repositoryUri);
	
}
