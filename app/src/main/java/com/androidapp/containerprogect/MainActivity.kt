package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.room.data.Address
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
            val name = edit_text_name.text.toString()
            val salary = edit_text_salary.text.toString().run { toIntOrNull() ?: 0 }
            val addressDb = Address(
                city = "City $numberAddress",
                street = "Street $numberAddress",
                number = numberAddress
            )
            scope.launch {
                db.employees().insert(Employees(name = name, salary = salary, address = addressDb))
                edit_text_name.text?.clear()
                edit_text_salary.text?.clear()
            }
        }

        btn_select.setOnClickListener {
            scope.launch {
                val employees = db.employees().getAll()
                text_select.text = employees.toString()
            }
        }
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