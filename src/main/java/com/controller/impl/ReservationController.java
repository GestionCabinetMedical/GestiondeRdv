package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IReservationController;
import com.entity.Reservation;

/**
 * Classe controller {@code ReservationController} spécifique de Reservation qui
 * hérite de la classe générique {@code DaoControllerImpl} et implémente
 * l'interface spécifique {@code IReservationController}.
 * 
 * @author Sophie Lahmar
 *
 */
@RestController
@RequestMapping(value = "/reservation-rest")
public class ReservationController extends DaoControllerImpl<Reservation> implements IReservationController {

}
