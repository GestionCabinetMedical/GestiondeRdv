package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Classe qui définit les Consultations
 *
 * @author Jonathan Rachwal
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
	@Column(name="ID_MEDECIN", unique = false, nullable = false)
	private Long idMedecin;
	@Column(name="ID_PATIENT", unique = false, nullable = false)
	private Long idPatient;
	@ManyToOne
	@JoinColumn(name="FK_MEDECIN", referencedColumnName = "ID_MEDECIN",  unique = true, nullable = true)
	private Medecin medecin;
	@OneToOne
	@JoinColumn(name="FK_FICHE_MEDICAL", referencedColumnName = "ID_FICHE_MEDICAL",  unique = true, nullable = true)
	private FichesMedicales fichesMedicales;
	@OneToOne
	@JoinColumn(name="FK_RESERVATION", referencedColumnName = "ID_RESERVATION",  unique = true, nullable = true)
	private Reservation reservation;
	
	public Consultation(Long idConsultation, Long idMedecin, Long idPatient) {
		super();
		this.idConsultation = idConsultation;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
	}



}
