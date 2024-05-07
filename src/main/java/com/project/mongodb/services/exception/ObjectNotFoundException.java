package com.project.mongodb.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String id) {
		super("Resource Not Found. Id: " + id);
	}

}
