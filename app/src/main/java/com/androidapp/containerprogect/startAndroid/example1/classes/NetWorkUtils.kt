package com.androidapp.containerprogect.startAndroid.example1.classes

import android.util.Log
import javax.inject.Inject

class NetWorkUtils @Inject constructor(
    private val connectionManager: ConnectionManager
    ) {

    fun getLog() {
        Log.i("TAG", "NetWorkUtils")
    }
}