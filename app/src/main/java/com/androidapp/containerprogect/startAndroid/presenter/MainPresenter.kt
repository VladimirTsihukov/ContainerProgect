package com.androidapp.containerprogect.startAndroid.presenter

import android.util.Log
import com.androidapp.containerprogect.startAndroid.example1.classes.DatabaseHelper
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils

class MainPresenter (
    databaseHelper: DatabaseHelper,
    netWorkUtils: NetWorkUtils
) {
    fun getLog() {
        Log.i("TAG", "MainPresenter")
    }
}