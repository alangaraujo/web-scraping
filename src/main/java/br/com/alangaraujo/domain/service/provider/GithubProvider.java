package br.com.alangaraujo.domain.service.provider;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.alangaraujo.domain.exception.ConnectionIssueException;
import br.com.alangaraujo.domain.exception.NotFoundException;
import br.com.alangaraujo.domain.model.Document;
import br.com.alangaraujo.infrastructure.github.DocumentFilter;
import br.com.alangaraujo.infrastructure.resource.ProviderVerifier;

@Cacheable("documents")
@Service("github")
public class GithubProvider implements Provider {
	
	@Autowired
	private ProviderVerifier providerVerifier;
	
	protected static final String GITHUB_URL = "https://github.com";
	private static final String REPO_NAME_REGEX = "^[a-zA-Z0-9-]*[\\/][a-zA-Z0-9-]*$";
	
	@Override
	public List<Document> searchDocuments(String repositoryUri) {
		String fullUrl = String.join("/", GITHUB_URL, repositoryUri);
		
		try {
			if (!repositoryUri.matches(REPO_NAME_REGEX) || !providerVerifier.isPublicRepository(fullUrl)) {
				throw new NotFoundException("Repository not found or is not public");
			}	
			
			return new DocumentFilter.Builder(fullUrl)
					.grouped()
					.build();

		} catch (IOException e) {
			throw new ConnectionIssueException("Issues found while fetching from GitHub", e);
		}
	}
	
}
