package com.androidapp.containerprogect.startAndroid.example1.classes

import android.util.Log

class ServerApi (private val api: String) {

    fun getApi() {
        Log.i("TAG", api)
    }
}