package com.example.login.presentation.viewmodels

import android.os.UserManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.localdb.domain.repositories.ReservationDao
import com.example.login.domain.entities.Reservation
import com.example.login.domain.repositories.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationsViewModel(private val reservationsRepository: ReservationRepository) :
    ViewModel() {
    var allReservations = mutableStateOf(listOf<Reservation>())

    fun getAllReservations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allReservations.value = reservationsRepository.getReservations()
            }
        }
    }

    fun createReservation(reservation: Reservation) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                reservationsRepository.createReservation(reservation = reservation)
            }
        }
    }

    class Factory(private val reservationsRepository: ReservationRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationsViewModel(reservationsRepository) as T
        }
    }
}