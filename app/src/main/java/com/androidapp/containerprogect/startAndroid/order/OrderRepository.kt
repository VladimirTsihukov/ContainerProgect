package com.androidapp.containerprogect.startAndroid.order

import android.util.Log
import com.androidapp.containerprogect.startAndroid.example1.di.AppScope
import javax.inject.Inject


@AppScope
class OrderRepository @Inject constructor() {

    fun getLog() {
        Log.i("TAG", "OrderRepository - $this")
    }
}