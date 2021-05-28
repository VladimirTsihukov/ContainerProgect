package com.androidapp.containerprogect

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SampleReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_TOAST_SHORT = "ACTION_TOAST_SHORT"
        const val ACTION_TOAST_LONG = "ACTION_TOAST_LONG"
        const val ARG_MESSAGE = "ARG_MESSAGE"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val resultIntent = when (intent.action) {
            ACTION_TOAST_LONG -> Toast.LENGTH_LONG
            ACTION_TOAST_SHORT -> Toast.LENGTH_SHORT
            else -> throw IllegalArgumentException("Unexpected action")
        }
        val message = intent.getStringExtra(ARG_MESSAGE)
        message?.let {
            Toast.makeText(context, it, resultIntent).show()
        }
    }
}