package com.example.login.domain.repositories

import com.example.localdb.domain.repositories.ReservationDao
import com.example.login.domain.entities.Reservation

class ReservationRepository(private val reservationDao: ReservationDao) {

    fun getReservations() = reservationDao.getReservations()

    fun createReservation(reservation: Reservation) =
        reservationDao.createReservation(reservation = reservation)

}
