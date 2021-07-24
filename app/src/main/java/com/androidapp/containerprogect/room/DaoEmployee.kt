package com.androidapp.containerprogect.room

import androidx.room.*
import com.androidapp.containerprogect.room.data.Employees

@Dao
interface DaoEmployee {

    @Query("SELECT * FROM employees")
    suspend fun getAll(): List<Employees>

    @Query("SELECT * FROM employees WHERE id = :id")
    suspend fun getById(id: Long) : Employees

    @Insert
    suspend fun insert(employees: Employees)

    @Update
    suspend fun update(employees: Employees)

    @Delete
    suspend fun delete(employees: Employees)
}