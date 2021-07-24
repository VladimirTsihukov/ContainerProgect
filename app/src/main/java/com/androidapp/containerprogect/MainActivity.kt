package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save.setOnClickListener {
            val name = edit_text_name.text.toString()
            val salary = edit_text_salary.text.toString().run { toIntOrNull() ?: 0 }
            scope.launch {
                db.employees().insert(Employees(name = name, salary = salary))
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