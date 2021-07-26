package com.androidapp.containerprogect.room.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "employees")
data class Employees(

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String = "",

    val salary: Int = 0,

    @Embedded(prefix = "address")
    val address: Address? = null,

    @ColumnInfo(name = "department_id")
    val departmentId: Int = 0
)
