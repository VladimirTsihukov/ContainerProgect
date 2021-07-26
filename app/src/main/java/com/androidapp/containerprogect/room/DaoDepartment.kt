package com.androidapp.containerprogect.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.androidapp.containerprogect.room.data.Department
import com.androidapp.containerprogect.room.data.DepartmentWithEmployees

@Dao
interface DaoDepartment {

    @Query("SELECT * FROM department")
    suspend fun getAll(): List<Department>

    @Insert
    suspend fun insertDepartment(vararg department: Department)

    @Delete
    fun deleteDepartment(department: Department)

    @Query("SELECT id, nameDepartment FROM department")
    suspend fun getDepartment(): List<DepartmentWithEmployees>
}