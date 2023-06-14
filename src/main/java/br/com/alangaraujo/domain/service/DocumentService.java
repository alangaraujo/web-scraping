package br.com.alangaraujo.domain.service;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alangaraujo.domain.exception.NotFoundException;
import br.com.alangaraujo.domain.model.Document;
import br.com.alangaraujo.domain.service.provider.Provider;

@Service
public class DocumentService {
	
	@Autowired
	private BeanFactory beanFactory;

	public List<Document> findAll(String providerValue, String repositoryUri) {
		
		try {
			Provider provider = beanFactory.getBean(providerValue.toLowerCase(), Provider.class);
			
			return provider.searchDocuments(repositoryUri.toLowerCase());
		} catch (BeansException e) {
			throw new NotFoundException("Provider not found: " + providerValue);
		}

	}
	
}
