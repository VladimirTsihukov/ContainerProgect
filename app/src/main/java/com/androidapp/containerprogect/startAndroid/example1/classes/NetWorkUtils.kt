package com.androidapp.containerprogect.startAndroid.example1.classes

import android.util.Log

class NetWorkUtils(private val connectionManager: ConnectionManager) {

    fun getLog() {
        Log.i("TAG", "NetWorkUtils")
    }
}