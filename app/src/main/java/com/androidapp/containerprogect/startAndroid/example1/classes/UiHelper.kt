package com.androidapp.containerprogect.startAndroid.example1.classes

import android.app.Activity
import android.util.Log

class UiHelper(private val activity: Activity) {

    fun getLog() {
        Log.i("TAG", "UiHelper - $this \nactivity - $activity")
    }
}