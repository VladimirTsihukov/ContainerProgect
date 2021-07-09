package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Job())
    private var formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run.setOnClickListener {
            onRun()
        }

        btn_cancel.setOnClickListener {
            onCancel()
        }


/*        val job = scope.launch {
            Log.d(TAG, "scope = $this")
        }
        Log.d(TAG, "job = $job")*/
    }

    private fun onRun() {
        log("onRun, start")

        job = scope.launch {
            log("coroutine, start")
            var x = 0
            while (x < 5 && isActive) {
                //TimeUnit.MILLISECONDS.sleep(1000)
                delay(1000)
                log("coroutine, ${x++}, isActive = $isActive")
            }
            log("coroutine, end")
        }
        log("onRun, end")
    }

    private fun onCancel() {
        log("onCancel")
        scope.cancel()
    }

    private fun log(text: String) {
        Log.i(TAG, "${formatter.format(Date())} $text [${Thread.currentThread().name}]")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }
}