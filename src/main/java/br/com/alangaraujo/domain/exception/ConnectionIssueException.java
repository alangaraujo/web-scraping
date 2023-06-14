package br.com.alangaraujo.domain.exception;

public class ConnectionIssueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConnectionIssueException(String message, Throwable cause) {
        super(message, cause);
        
    }
}
