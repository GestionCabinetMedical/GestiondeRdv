/**
 * 
 */
package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Maxime
 *
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ResponseDto<E> {
	
	private boolean error;
	
	private E body;
	
	private String message;
	
	private int status;

}
