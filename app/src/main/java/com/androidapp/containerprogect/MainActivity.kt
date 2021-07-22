package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.androidapp.containerprogect.databinding.ActivityMainBinding
import com.androidapp.containerprogect.ex_01_basics.Department
import com.androidapp.containerprogect.ex_01_basics.Employee
import com.androidapp.containerprogect.ex_02_click.MyHandler

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val employeeTest = Employee(12, "Tom", salary = 100)
        val department = Department(325, "Department name")
        val handler = MyHandler()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.employee = employeeTest
        binding.department = department
        binding.handler = handler
    }
}

fun getLog(text: String) {
    Log.i(TAG, text)
}