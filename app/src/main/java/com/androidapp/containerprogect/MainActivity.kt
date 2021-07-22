package com.androidapp.containerprogect

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels

        val thread = Thread {
            for (x in 0 until 101) {
                runOnUiThread {
                    val progress = x.toFloat() /100
                    val progressWidth = (progress * width).toInt()
                    linearProgress.animateProgress(progressWidth)
                    text_progress.text = "$x%"
                }
                Thread.sleep(100)
            }
        }
        thread.start()
    }
}