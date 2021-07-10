package com.androidapp.containerprogect

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


private var formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

fun log(text: String) {
    Log.i(TAG, "${formatter.format(Date())} $text [${Thread.currentThread().name}]")
}