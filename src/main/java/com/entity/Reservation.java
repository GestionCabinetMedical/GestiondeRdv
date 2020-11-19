package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe {@code Reservation} qui caractérise une réservation faite par un
 * {@link Patient}. Cette classe contient une liste non-exhaustive d'attributs
 * qui décrivent une réservation.
 * 
 * @author Sophie Lahmar
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PATIENT", unique = true, nullable = false)
	private Long idReservation;

	@Column(name = "STATUS", unique = true, nullable = false)
	private boolean status;

	@Column(name = "DATE_RESERVATION", unique = true, nullable = false)
	private Date dateRervation;

	@Column(name = "CONSULTATION_EN_URGENCE", unique = true, nullable = true)
	private boolean isUrgent;

}
