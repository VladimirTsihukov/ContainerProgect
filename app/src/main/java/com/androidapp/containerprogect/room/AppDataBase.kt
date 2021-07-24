package com.androidapp.containerprogect.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidapp.containerprogect.room.data.Car
import com.androidapp.containerprogect.room.data.Employees


@Database(entities = [Employees::class, Car::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun employees(): DaoEmployee
    abstract fun carAndEmployee(): DaoEmployeeCar

    companion object {
        fun instance(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "dataBase"
            ).build()
        }
    }
}