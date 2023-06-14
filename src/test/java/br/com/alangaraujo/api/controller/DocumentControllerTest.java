package br.com.alangaraujo.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.alangaraujo.domain.model.Document;
import br.com.alangaraujo.domain.service.DocumentService;

@SpringBootTest
@AutoConfigureMockMvc
class DocumentControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private DocumentService documentService;
	
	private static final String GITHUB_VALUE = "github";
	private static final String REPOSITORY_URI_VALUE = "alangaraujo/web-scraping";

	@Test
	void shouldReturnAnDocumentThatMatchesAGivenTypeInRepository() throws Exception {
		
		MultiValueMap<String, String> queryParamValues = new LinkedMultiValueMap<>();
		queryParamValues.add("provider", GITHUB_VALUE);
		queryParamValues.add("repositoryUri", REPOSITORY_URI_VALUE);
		
		mockMvc.perform(get("/documents")
			.queryParams(queryParamValues))
			.andExpect(status().isOk());
		
		List<Document> documentReturn = documentService.findAll(GITHUB_VALUE, REPOSITORY_URI_VALUE);
		
		Assertions.assertTrue(documentReturn.stream()
								.anyMatch(item -> item.equals(new Document("mvnw", 1, 310, 10065L))));
		
	}

}
