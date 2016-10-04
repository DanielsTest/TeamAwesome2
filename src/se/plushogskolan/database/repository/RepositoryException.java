package se.plushogskolan.database.repository;

public class RepositoryException extends Exception {

	public RepositoryException(String message){
		super(message);
	}
	
	public RepositoryException(String message, Throwable throwable){
		super(message, throwable);
	}
	
}
