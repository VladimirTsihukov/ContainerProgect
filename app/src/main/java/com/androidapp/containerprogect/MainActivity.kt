package com.androidapp.containerprogect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

const val TAG_TEST = "tag_test"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_main).setOnClickListener {
            Log.i(TAG_TEST, "Click for main Btn")
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
}