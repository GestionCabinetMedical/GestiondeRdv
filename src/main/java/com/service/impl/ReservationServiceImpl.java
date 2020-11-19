package com.service.impl;

import org.springframework.stereotype.Service;

import com.entity.Reservation;
import com.service.IReservationService;

/**
 * Classe service {@code ReservationServiceImpl} spécifique de Reservation qui
 * hérite de la classe générique {@code DaoServiceImpl} et implémente
 * l'interface spécifique {@code IReservationService}.
 * 
 * @author Sophie Lahmar
 *
 */
@Service
public class ReservationServiceImpl extends DaoServiceImpl<Reservation> implements IReservationService {

}
