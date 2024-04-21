package com.example.login.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reservations")
class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "duration")
    val duration: Int,
    @ColumnInfo(name="reservation_date")
    val reservationDate: Date?
)