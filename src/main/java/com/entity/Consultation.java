package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Classe qui définit les Consultations
 *
 * @author rachw
 *
 */

@Data
@Entity
@NoArgsConstructor 
@AllArgsConstructor
public class Consultation implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID_CONSULTATION", unique = true, nullable = false)
	private Long idConsultation; 
	@Column(name="ID_MEDECIN")
	private Long idMedecin;
	@Column(name="ID_PATIENT")
	private Long idPatient;
	@OneToOne
	@JoinColumn(name="FK_RESERVATION", referencedColumnName = "ID_RESERVATION",  unique = true, nullable = true)
	private Reservation reservation;
}