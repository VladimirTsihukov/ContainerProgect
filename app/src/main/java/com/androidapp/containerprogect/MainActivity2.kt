package com.androidapp.containerprogect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<Button>(R.id.btn_second_activity).setOnClickListener {
            Log.i(TAG_TEST, "Click for second btn Activity")
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}