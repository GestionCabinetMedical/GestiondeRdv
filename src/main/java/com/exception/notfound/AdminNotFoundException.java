package com.exception.notfound;

import javassist.NotFoundException;

/**
 * Classe exception {@code AdminNotFoundException} si un objet de {@link Admin} n'est pas
 * trouvé. Elle étend de {@code NotFoundException}.
 * 
 * @author Sophie Lahmar
 * @see NotFoundException
 */
public class AdminNotFoundException extends NotFoundException {

	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service.
	 * 
	 * @param msg
	 */
	public AdminNotFoundException(String msg) {
		super(msg);
	}

}
