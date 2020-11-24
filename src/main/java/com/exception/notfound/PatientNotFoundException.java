package com.exception.notfound;

import javassist.NotFoundException;

/**
 * Classe exception {@code PatientNotFoundException} si un objet de {@link Patient} n'est
 * pas trouvé. Elle étend de {@code NotFoundException}.
 * 
 * @author Sophie Lahmar
 * @see NotFoundException
 */
public class PatientNotFoundException extends NotFoundException {

	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service.
	 * 
	 * @param msg
	 */
	public PatientNotFoundException(String msg) {
		super(msg);
	}

}
