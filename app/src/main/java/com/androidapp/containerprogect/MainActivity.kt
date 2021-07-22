package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androidapp.containerprogect.ex_01_lifecycle.MyServer
import com.androidapp.containerprogect.ex_02_liveData.MyViewModel

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val server = MyServer()
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLog("State onCreate = ${lifecycle.currentState}")
        lifecycle.addObserver(server)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        viewModel.mediatorLiveData.observe(this, {
            it?.let {
                getLog("MediatorLiveData = $it")
            }
        })
    }
}

fun getLog(text: String) {
    Log.i(TAG, text)
}