package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.Reservation;

/**
 * Interface controller {@code IReservationController} spécifique de Reservation
 * héritant de l'interface générique {@code IDaoController}.
 * 
 * @author Sophie Lahmar
 *
 */
public interface IReservationController extends IDaoController<Reservation> {

}
