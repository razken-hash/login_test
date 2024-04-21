package com.example.localdb.domain.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.localdb.domain.entities.User

@Dao
interface UserDao {

    @Insert()
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Upsert
    fun upsertUser(user: User)

    @Query("SELECT * FROM USERS WHERE ID = :userId")
    fun getUserById(userId: Int):User
}