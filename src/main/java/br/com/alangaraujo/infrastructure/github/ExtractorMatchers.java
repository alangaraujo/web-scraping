package br.com.alangaraujo.infrastructure.github;

public interface ExtractorMatchers {

	String FILE_DIRECTORY = ".*aria-label=\\\"(File|Directory)\\\".*";
	String URL = ".*href=\\\".*";
	String EXTENSION = ".*class=\\\"final-path\\\">.*<.*";
	String LINES = ".*[0-9]* lines \\(.*";
	String BYTES = ".*[0-9\\.]* (KB|MB|Byte).*";
	
}
