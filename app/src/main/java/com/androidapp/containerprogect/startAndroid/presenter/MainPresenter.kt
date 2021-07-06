package com.androidapp.containerprogect.startAndroid.presenter

import android.util.Log
import com.androidapp.containerprogect.MainActivity
import com.androidapp.containerprogect.startAndroid.example1.classes.DatabaseHelper
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils

class MainPresenter (
    private val databaseHelper: DatabaseHelper,
    private val netWorkUtils: NetWorkUtils,
    private val activity: MainActivity,
) {
    fun getLog() {
        Log.i("TAG", "MainPresenter: activity = $activity")
    }
}