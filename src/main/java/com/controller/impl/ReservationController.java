package com.controller.impl;

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
public class ReservationController extends DaoControllerImpl<Reservation> implements IReservationController {

}
