/**
 * 
 */
package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Classe ResponseDto permettant la communication avec le front.
 * 
 * @author Maxime Rembert
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<E> {

	private boolean error;

	private E body;

	private String message;

	private int status;

}
