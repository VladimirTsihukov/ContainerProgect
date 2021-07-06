package com.androidapp.containerprogect.startAndroid.presenter

import android.util.Log
import com.androidapp.containerprogect.startAndroid.example1.classes.DatabaseHelper
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils
import javax.inject.Inject

class MainPresenter @Inject constructor(
    databaseHelper: DatabaseHelper,
    netWorkUtils: NetWorkUtils
) {
    fun getLog() {
        Log.i("TAG", "MainPresenter")
    }
}