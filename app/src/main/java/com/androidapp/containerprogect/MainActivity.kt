package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

const val TAG = "TAG_TAG"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testPerson = PersonSerializable(
            "Tom",
            "Tompson",
            20
        )

        val clazz = testPerson.javaClass
        val file = clazz.getDeclaredField("name")

        file.isAccessible = true

        Log.i(TAG, "${file.get(testPerson)}")
        Log.i(TAG, "${file.type}")
        Log.i(TAG, "${file.declaringClass}")
    }
}