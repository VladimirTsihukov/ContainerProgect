package com.androidapp.containerprogect.startAndroid.order

import android.content.Context
import android.util.Log
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils

class OrderPresenter(context: Context, netWorkUtils: NetWorkUtils) {

    fun getLog() {
        Log.i("TAG", "OrderPresenter")
    }
}