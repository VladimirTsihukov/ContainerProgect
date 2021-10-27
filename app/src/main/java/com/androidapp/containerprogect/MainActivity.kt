package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.androidapp.containerprogect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.handler = Handler()
        var current = 0
        val employee = Employee(234, "Bob", "Martin", 90_000)
        val department = Department(1324, "IT")
        binding.btnSetEmployer.setOnClickListener {
            binding.employee = employee
            binding.department = department
        }

        binding.btnObservableField.setOnClickListener {
            employee.testObservable.set("Observable: ${current++}")
        }
    }
}