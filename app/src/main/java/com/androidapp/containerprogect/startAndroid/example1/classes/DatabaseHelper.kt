package com.androidapp.containerprogect.startAndroid.example1.classes

import android.util.Log
import javax.inject.Inject

class DatabaseHelper @Inject constructor(
    private val repository: Repository
    ) {

    fun getLog() {
        Log.i("TAG", "DatabaseHelper")
    }
}