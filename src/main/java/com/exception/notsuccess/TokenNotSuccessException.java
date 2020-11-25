package com.exception.notsuccess;


/**
 * 
 * Classe exception {@code TokenNotSuccessException} si action sur {@link TokenManagement} n'est pas réalisée au sein d'une autre méthode du service. 
 * Elle étend de {@code Throwable}. 
 * @see Throwable
 * @author Pauline Humbert
 *
 */
public class TokenNotSuccessException extends Throwable {
	
	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service.
	 * 
	 * @param msg
	 */
	public TokenNotSuccessException(String msg) {
		super(msg);
	}

}
