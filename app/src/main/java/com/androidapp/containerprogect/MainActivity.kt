package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.startAndroid.example1.DatabaseHelper
import com.androidapp.containerprogect.startAndroid.example1.NetWorkUtils
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    @Inject
    lateinit var netWorkUtils: NetWorkUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.injectMainActivity(this)

    }

    override fun onStart() {
        super.onStart()
        databaseHelper.getLog()
        netWorkUtils.getLog()
    }
}