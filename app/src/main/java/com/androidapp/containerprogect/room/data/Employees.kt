package com.androidapp.containerprogect.room.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "employees")
data class Employees(

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val name: String,
    val salary: Int,
    @Embedded(prefix = "address")
    val address: Address,
)
