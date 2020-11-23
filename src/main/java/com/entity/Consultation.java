package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe {@code Consultation} qui définit les consultations. Elle contient une
 * liste non-exhaustive d'attributs qui caractérise une consultation faite par
 * un {@link Medecin}, et possède une relation {@code @OneToOne} avec la classe
 * {@code Reservation}.
 *
 * @author Jonathan Rachwal
 * @see Reservation
 *
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Consultation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONSULTATION", unique = true, nullable = false)
	private Long idConsultation;

	@Column(name = "ID_PATIENT", unique = false, nullable = false)
	private Long idPatient;

	@OneToOne
	@JoinColumn(name = "FK_RESERVATION", referencedColumnName = "ID_RESERVATION", unique = true, nullable = true)
	private Reservation reservation;

	public Consultation(Long idConsultation, Long idPatient) {
		super();
		this.idConsultation = idConsultation;
		this.idPatient = idPatient;
	}

}
