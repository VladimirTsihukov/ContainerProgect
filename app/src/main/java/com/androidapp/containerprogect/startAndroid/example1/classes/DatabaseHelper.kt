package com.androidapp.containerprogect.startAndroid.example1.classes

import android.util.Log

class DatabaseHelper (
    private val repository: Repository
    ) {

    fun getLog() {
        Log.i("TAG", "DatabaseHelper")
    }
}