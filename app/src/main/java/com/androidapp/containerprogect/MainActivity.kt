package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.cancel

const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

    private val testLaunchAsync = TestLaunchAsync()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run.setOnClickListener {
            testLaunchAsync.onRun5()
        }

        btn_cancel.setOnClickListener {
            testLaunchAsync.onCancel()
        }

        btn_run2.setOnClickListener {
            testLaunchAsync.coroutineStart()
        }

/*        val job = scope.launch {
            Log.d(TAG, "scope = $this")
        }
        Log.d(TAG, "job = $job")*/
    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        testLaunchAsync.scope.cancel()
    }
}