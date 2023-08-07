package com.androidapp.containerprogect

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

const val TAG = "tag_coroutine"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        log("Activity onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()

        log("Activity onDestroy, isFinishing = $isFinishing")
    }

}

val formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

fun log(text: String) {
    Log.d(TAG, "${formatter.format(Date())} $text [${Thread.currentThread().name}]")
}

fun CoroutineScope.repeatIsActive() {
    repeat(5) {
        TimeUnit.MILLISECONDS.sleep(300)
        log("Coroutine_${coroutineContext[CoroutineName]?.name} isActive $isActive")
    }
}

fun CoroutineScope.logCoroutine(text: String = "") {
    Log.d(TAG, "${formatter.format(Date())} NAME Coroutine_${coroutineContext[CoroutineName]?.name} $text [${Thread.currentThread().name}] isActive $isActive")
}
