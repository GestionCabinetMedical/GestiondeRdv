package com.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe {@code GainDto} permet de mapper le gain du micro-service
 * {@code GestionAdmin}.
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
