package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androidapp.containerprogect.ex_01_lifecycle.MyServer
import com.androidapp.containerprogect.ex_03_viewModel.ViewModelFragments
import com.androidapp.containerprogect.ex_03_viewModel.fragment.Fragment1
import com.androidapp.containerprogect.ex_03_viewModel.fragment.Fragment2

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val server = MyServer()

    //private lateinit var viewModel: MyViewModel
    private lateinit var viewModel: ViewModelFragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLog("State onCreate = ${lifecycle.currentState}")
        lifecycle.addObserver(server)

        viewModel = ViewModelProvider(this).get(ViewModelFragments::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment_1, Fragment1())
                .replace(R.id.container_fragment_2, Fragment2())
                .commit()
        }

        //viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        /*   viewModel.mediatorLiveData.observe(this, {
               it?.let {
                   getLog("MediatorLiveData = $it")
               }
           })*/
    }
}

fun getLog(text: String) {
    Log.i(TAG, text)
}