package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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
	@Column(name = "ID_PATIENT")
	private Long idReservation;

	@Column(name = "STATUS")
	private boolean status;

	@Column(name = "DATE_RESERVATION")
	private Date dateRervation;

	@Column(name = "CONSULTATION_EN_URGENCE")
	private boolean isUrgent;

	@ManyToOne
	private Patient patient;

}
