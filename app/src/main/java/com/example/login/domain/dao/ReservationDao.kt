package com.example.localdb.domain.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.localdb.domain.entities.User
import com.example.login.domain.entities.Reservation
import java.util.Date

@Dao
interface ReservationDao {

    @Insert()
    fun createReservation(reservation: Reservation)

    @Update
    fun updateReservation(reservation: Reservation)

    @Delete
    fun deleteReservation(reservation: Reservation)

    @Upsert
    fun upsertReservation(reservation: Reservation)

    @Query("SELECT * FROM RESERVATIONS WHERE ID = :reservationId")
    fun getReservationById(reservationId: Int):Reservation

    @Query("SELECT * FROM RESERVATIONS WHERE reservation_date = :res_date")
    fun getReservationByDate(res_date: Date):List<Reservation>

    @Query("SELECT * FROM RESERVATIONS")
    fun getReservations():List<Reservation>

}