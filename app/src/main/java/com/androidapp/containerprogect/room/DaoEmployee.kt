package com.androidapp.containerprogect.room

import androidx.room.*
import com.androidapp.containerprogect.room.data.Employees
import com.androidapp.containerprogect.room.data.Name

@Dao
interface DaoEmployee {

    @Query("SELECT * FROM employees")
    suspend fun getAll(): List<Employees>

    @Query("SELECT * FROM employees WHERE id = :id")
    suspend fun getById(id: Long) : Employees

    @Query("SELECT first_name, last_name FROM employees")
    suspend fun getAllName(): List<Name>

    @Insert
    suspend fun insert(employees: Employees)

    @Insert
    suspend fun insertList(employeesList: List<Employees>)

    @Update
    suspend fun update(employees: Employees)

    @Delete
    suspend fun delete(employees: Employees)

}