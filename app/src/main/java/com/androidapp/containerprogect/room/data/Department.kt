package com.androidapp.containerprogect.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department")
data class Department(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val nameDepartment: String,
)

