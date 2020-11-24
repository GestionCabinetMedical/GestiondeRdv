package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sophie Lahmar
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnexionDto {

	private String token;
	private ConnectedUserDto user;

	private boolean error;
	private int status;

}
