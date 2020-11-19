/**
 * 
 */
package com.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe GainDto servant mappant le gain du micro service gestion-admin
 * 
 * @author Maxime Rembert
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GainDto {

	private Long id;

	private float gain;

	private Date date;

}
