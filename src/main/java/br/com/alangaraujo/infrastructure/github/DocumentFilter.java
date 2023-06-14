package br.com.alangaraujo.infrastructure.github;

import br.com.alangaraujo.domain.model.Document;
import br.com.alangaraujo.domain.model.DocumentType;
import br.com.alangaraujo.infrastructure.util.DocumentUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentFilter {

	private List<Document> documentFilterList;
	private static final String DIRECTORY = "Directory";
	private static final String GITHUB_URL = "https://github.com";

	private DocumentFilter(Builder builder) {
		documentFilterList = builder.documents;
	}

	public static class Builder extends DocumentUtil {

		private List<Document> documents;

		public Builder (String html) throws IOException {
			documents = filterDocuments(html);
		}

		public Builder grouped() {
			documents = groupDocuments(documents);
			return this;
		}

		public List<Document> build() {
			return new DocumentFilter(this).documentFilterList;
		}

		private List<Document> filterDocuments(String html) throws IOException {

			List<DocumentType> documentTypeList = DocumentExtractor.getDirectoryStructure(html);
			List<Document> filterDocumentList = new ArrayList<>();

			if (documentTypeList != null && !documentTypeList.isEmpty()) {
				for (DocumentType documentType : documentTypeList) {

					if (documentType.getType().equals(DIRECTORY)) {
						filterDocumentList.addAll(filterDocuments(GITHUB_URL + documentType.getValue()));
					} else {
						filterDocumentList.add(DocumentExtractor.getFileContents(GITHUB_URL + documentType.getValue()));
					}
				}
			}

			return filterDocumentList;
		}

	}

}
