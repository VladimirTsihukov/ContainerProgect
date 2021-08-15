package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity2 : AppCompatActivity(), RootViewNavController {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val data = intent?.getStringExtra("activity2")
        data?.let {
            Log.i(TAG, it)
        }

        val dataArg4 = intent?.getStringExtra("arg4")
        dataArg4?.let {
            Log.i(TAG, it)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_second) as? NavHostFragment
        navHostFragment?.let {
            navController = it.navController
        }

    }

    override fun getNavController(): NavController = navController
}