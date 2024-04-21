package com.example.localdb.domain.gateway

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.localdb.domain.entities.User
import com.example.localdb.domain.repositories.ReservationDao
import com.example.localdb.domain.repositories.UserDao
import com.example.login.domain.entities.Reservation
import com.example.login.utils.Converters

@Database(entities = [User::class, Reservation::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(

) {
    abstract fun getUserDao(): UserDao
    abstract fun getReservationDao(): ReservationDao


    companion object {
        var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        context, AppDatabase::class.java,
                        "app_db",
                    ).build()
            }
            return instance!!
        }
    }
}