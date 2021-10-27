package com.androidapp.containerprogect

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField

data class Employee(
    val id: Int,
    val name: String,
    val address: String,
    val salary: Int,
) {
    val testObservable = ObservableField<String>()
}

data class Department(
    val id: Int,
    val name: String,
)

class Handler {
    fun onBind(view: View) {
        Log.i("TAG", "Handler click")
    }

    fun newFun (employee: Employee) {
        Log.i("TAG", "Name employee: ${employee.name}")
    }
}

