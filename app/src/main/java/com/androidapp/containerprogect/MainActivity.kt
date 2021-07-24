package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.room.data.Address
import com.androidapp.containerprogect.room.data.Car
import com.androidapp.containerprogect.room.data.Employees
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val db by lazy { App.db }
    private val handler = CoroutineExceptionHandler { _, throwable ->
        getLogError(throwable.message.toString())
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main + handler)
    private var numberAddress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save.setOnClickListener {
            numberAddress++
            scope.launch {
                db.employees().insert(getEmployee())
                clearText()
            }
        }

        btn_save_employee_and_car.setOnClickListener {
            val employees = getEmployee()
            val car = getCar()

            scope.launch {
                db.carAndEmployee().insertCarAndEmployee(car, employees)
                clearText()
            }
        }

        btn_select.setOnClickListener {
            scope.launch {
                val employees = db.employees().getAll()
                text_select.text = employees.toString()
            }
        }

        btn_select_employee_and_car.setOnClickListener {
            scope.launch {
                val employees = db.employees().getAll()
                val car = db.carAndEmployee().getCarAll()
                text_select.text = "${employees.toString()} \n${car.toString()}"
            }
        }
    }

    private fun clearText() {
        edit_text_name.text?.clear()
        edit_text_salary.text?.clear()
        edit_text_car.text?.clear()
    }

    private fun getEmployee(): Employees {
        val name = edit_text_name.text.toString()
        val salary = edit_text_salary.text.toString().run { toIntOrNull() ?: 0 }
        return Employees(name = name, salary = salary, address = getAddress())
    }

    private fun getCar(): Car {
        val name = edit_text_car.text.toString()
        return Car(name = "Name car $name")
    }

    private fun getAddress(): Address {
        return Address(
            city = "City $numberAddress",
            street = "Street $numberAddress",
            number = numberAddress
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}

fun getLogError(text: String) {
    Log.e(TAG, "Error - $text")
}

fun getLogInfo(text: String) {
    Log.i(TAG, "Info - $text")
}