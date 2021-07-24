package com.androidapp.containerprogect.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class Car(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)