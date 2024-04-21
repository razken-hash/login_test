package com.example.login.dao_test

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.localdb.domain.gateway.AppDatabase
import com.example.login.domain.entities.Reservation
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.Date

class ReservationDaoTest {

    lateinit var memoryDatabase: AppDatabase
    lateinit var reservation: Reservation

    val reservationId = 2

    @Before
    fun initDB() {
        val appContext =
            InstrumentationRegistry.getInstrumentation().targetContext
        memoryDatabase =
            Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        reservation = Reservation(id = reservationId, name = "Res", duration = 2, reservationDate = Date(2020, 12, 23))
    }

    @Test
    fun testInsertAndRetrieveReservation() {
        memoryDatabase.getReservationDao().createReservation(reservation = reservation)
        val res = memoryDatabase.getReservationDao().getReservationById(reservationId)
        assertEquals(reservationId, res.id)
    }

    @After
    fun closeDb() {
        memoryDatabase.close()
    }
}
