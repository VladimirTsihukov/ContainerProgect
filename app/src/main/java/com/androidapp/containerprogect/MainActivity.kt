package com.androidapp.containerprogect

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sampleReceiver by lazy { SampleReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter().apply {
            addAction(SampleReceiver.ACTION_TOAST_SHORT)
            addAction(SampleReceiver.ACTION_TOAST_LONG)
        }
        registerReceiver(sampleReceiver, intentFilter)

        btn_broadcast.setOnClickListener {
            onSendButtonClick()
        }
    }

    private fun onSendButtonClick() {
        val intent = Intent(SampleReceiver.ACTION_TOAST_SHORT)
            .putExtra(SampleReceiver.ARG_MESSAGE, "My 1 broadcast Receiver")
        sendBroadcast(intent)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(sampleReceiver)
    }

}