package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.androidapp.containerprogect.channel.TestChannel
import com.androidapp.containerprogect.context.TestCoroutineContext
import com.androidapp.containerprogect.coroutineRetrofit.MyViewModel
import com.androidapp.containerprogect.coroutineScope.TestCoroutineScope
import com.androidapp.containerprogect.dispatchers.TestDispatchers
import com.androidapp.containerprogect.exeption.TestCoroutineException
import com.androidapp.containerprogect.flow.TestFlow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

    private val testLaunchAsync = TestLaunchAsync()
    private val testCoroutineContext = TestCoroutineContext()
    private val testDispatchers = TestDispatchers()
    private val testCoroutineExpectation = TestCoroutineException()
    private val testCoroutineScope = TestCoroutineScope()
    private val testChannel = TestChannel()
    private val testFlow = TestFlow()
    private val scope = CoroutineScope(Job())

    //private val viewModel: TestScopeLiveData by viewModels()
    private lateinit var viewModel: MyViewModel
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.getListMovie()
        viewModel.liveData.observe(this, {
            log("Movie size = ${it.results.size}")
        })

        btn_run.setOnClickListener {
            //testLaunchAsync.onRun5()
            log("btn_run")
        }

        btn_cancel.setOnClickListener {
            //testLaunchAsync.onCancel()
            log("btn_cancel")
        }

        btn_run2.setOnClickListener {
            //testLaunchAsync.coroutineStart()
            log("btn_run2")
        }
    }

    private fun createLifecycleScope() {
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                log("Work Activity")
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        testLaunchAsync.scope.cancel()
    }
}