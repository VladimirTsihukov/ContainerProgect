package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.ex_01_lifecycle.MyServer

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val server = MyServer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLog("State onCreate = ${lifecycle.currentState}")
        lifecycle.addObserver(server)
    }

    override fun onStart() {
        super.onStart()
        getLog("State onStart = ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        getLog("State onResume = ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        getLog("State onPause = ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        getLog("State onStop = ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        getLog("State onDestroy = ${lifecycle.currentState}")
    }

}

fun getLog(text: String) {
    Log.i(TAG, text)
}