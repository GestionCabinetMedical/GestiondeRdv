package com.dto;

import com.enums.Role;

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
public class ConnectedUserDto {

	private String identifiant;
	private String mdp;
	private Role role;

	private boolean error;
	private String msg;

}
