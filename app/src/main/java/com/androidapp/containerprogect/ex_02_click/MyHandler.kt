package com.androidapp.containerprogect.ex_02_click

import android.view.View
import com.androidapp.containerprogect.ex_01_basics.Employee
import com.androidapp.containerprogect.getLog

class MyHandler {
    fun onDelete(view: View){
        getLog("Button text")
    }

    fun getEmployee(employee: Employee) {
        getLog("Employee: ${employee.name}")
    }

    fun onEnabled(employee: Employee, enable: Boolean) {
        getLog("Employee: ${employee.name}, enable: $enable")
    }
}