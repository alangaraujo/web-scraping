package br.com.alangaraujo.infrastructure.github;

import br.com.alangaraujo.domain.model.Document;
import br.com.alangaraujo.domain.model.DocumentType;
import org.apache.commons.lang3.RegExUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.lang3.StringUtils.right;
import static org.apache.commons.lang3.StringUtils.substringBetween;

public class DocumentExtractor {

	public static List<DocumentType> getDirectoryStructure(String htmlFile) throws IOException {

    	String value;
    	String documentTypeValue = null;
    	String urlValue = null;
    	List<DocumentType> documentTypes = new ArrayList<>();
    	
    	URL url = new URL(htmlFile);
    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    	
    	while((value = br.readLine()) != null) {
    		if (value.matches(ExtractorMatchers.FILE_DIRECTORY)) {
    			documentTypeValue = substringBetween(value, "\"", "\"");
    			
        		do {
        			value = br.readLine();
        			if (value.matches(ExtractorMatchers.URL)) {
            			urlValue = substringBetween(value, "href=\"", "\"");
        			}
        			
        		} while (!value.matches(".*href=\\\".*"));
        		
        	documentTypes.add(new DocumentType(documentTypeValue, urlValue));
    		
    		}			
    	}
        
        return documentTypes;
    }
    
    public static Document getFileContents(String htmlFile) throws IOException {
    	
    	String fileExtension;
    	String value;
    	Integer countValue;
    	Document document = new Document();
    	
    	URL url = new URL(htmlFile);
    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    	
    	while ((value = br.readLine()) != null) {
    		if (value.matches(ExtractorMatchers.EXTENSION)) {
    			fileExtension = getExtension(substringBetween(value, "final-path\">", "</strong>"));
        		if (fileExtension.isBlank() || fileExtension.isEmpty()) {
        			fileExtension = substringBetween(value, "final-path\">", "</strong>");
        		}
        		document.setExtension(fileExtension);
        		break;
    		}
    	}
    	
		while ((value = br.readLine()) != null) {
			if (value.matches(ExtractorMatchers.LINES)) {
				String replacer = RegExUtils.removePattern(value, "lines.*");
				countValue = Integer.parseInt(replacer.replaceAll("[^0-9]", ""));
				document.setLines(countValue);
				break;
			}
		}
		
		if (document.getLines() == null) {
			document.setLines(0);
			url = new URL(htmlFile);
	    	br = new BufferedReader(new InputStreamReader(url.openStream()));
		}
		
		while ((value = br.readLine()) != null) {
			if (value.matches(ExtractorMatchers.BYTES)) {
				document.setBytes(byteConverter(value));
				break;
			}
		}
    	
    	return document;
    }
    
    private static Long byteConverter(String value) {
    	Double size = Double.parseDouble(value.replaceAll("[^0-9\\.]", ""));
		if(right(value, 2).equals("KB")) {
			size *= 1024;
		} else if (right(value, 2).equals("MB")) {
			size *= 1048576;
		}		
		return size.longValue();
    }
			
}

