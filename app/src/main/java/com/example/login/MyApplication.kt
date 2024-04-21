package com.example.login

import android.app.Application
import com.example.localdb.domain.gateway.AppDatabase
import com.example.login.domain.repositories.ReservationRepository

class MyApplication : Application() {
    private val database by lazy { AppDatabase.getInstance(this) }
    private val reservationDao by lazy { database.getReservationDao() }
    val reservationRepository by lazy { ReservationRepository(reservationDao) }
}