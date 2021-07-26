package com.androidapp.containerprogect.room.data

import androidx.room.Relation

data class DepartmentWithEmployees(

    val id: Int,
    val nameDepartment: String,

    @Relation(parentColumn = "id", entityColumn = "department_id")
    val employees: List<Employees>


)