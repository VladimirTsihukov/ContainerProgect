package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.androidapp.containerprogect.databinding.ActivityMainBinding
import com.androidapp.containerprogect.ex_01_basics.Employee

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val employeeTest = Employee(12, "Tom", "Address 1")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.employee = employeeTest
    }
}