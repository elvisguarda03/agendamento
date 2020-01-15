package br.com.guacom.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;

//Informando para o JTA que essa exceção deve gerar um rollback apesar de extender de uma ApplicationException

@ApplicationException(rollback = true)
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<String> messages;
	
	public BusinessException() {
		this("");
	}
	
	public BusinessException(String message) {
		super(message);
		
		messages = new ArrayList<>();
		messages.add(message);
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
}