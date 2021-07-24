package com.androidapp.containerprogect.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.androidapp.containerprogect.room.data.Car
import com.androidapp.containerprogect.room.data.Employees

@Dao
interface DaoEmployeeCar {

    @Insert
    suspend fun insertEmployee(employees: Employees)

    @Insert
    suspend fun insertCar(car: Car)

    @Query("SELECT * FROM car")
    suspend fun getCarAll(): List<Car>

    @Transaction
    suspend fun insertCarAndEmployee(car: Car, employees: Employees) {
        insertCar(car)
        insertEmployee(employees)
    }
}