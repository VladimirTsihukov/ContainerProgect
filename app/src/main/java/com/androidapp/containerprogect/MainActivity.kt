package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.channel.TestChannel
import com.androidapp.containerprogect.context.TestCoroutineContext
import com.androidapp.containerprogect.coroutineScope.TestCoroutineScope
import com.androidapp.containerprogect.dispatchers.TestDispatchers
import com.androidapp.containerprogect.exeption.TestCoroutineException
import com.androidapp.containerprogect.flow.TestFlow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //testCoroutineContext.main()
        //testDispatchers.dispatcherDefault2()
        //testDispatchers.dispatcherUnconfined()
        //testCoroutineExpectation.onRunExample5()
        //testCoroutineScope.onRun()
        //testChannel.onRun6()

/*        scope.launch {
            testFlow.testFlow1()
        }*/

/*        val flow = testFlow.createFlow()
        testFlow.collectFlow(flow)*/

        testFlow.myTerminalFlow()

/*        val job = scope.launch {
            log("parent start")
            launch {
                log("child start")
                delay(1000)
                log("child end")
            }
            log("parent end")
        }

        scope.launch {
            delay(500)
            log("1 parent job is active: ${job.isActive}")
            delay(1000)
            log("2 parent job is active: ${job.isActive}")
        }*/

        btn_run.setOnClickListener {
            //testLaunchAsync.onRun5()
        }

        btn_cancel.setOnClickListener {
            //testLaunchAsync.onCancel()
        }

        btn_run2.setOnClickListener {
            //testLaunchAsync.coroutineStart()
        }

        /*      val userData = UserData(1, "First userData", 31)
              val scope = CoroutineScope(Dispatchers.Main + userData)
              log("scope context: ${scope.coroutineContext}")
              log("userData in context: ${scope.coroutineContext[UserData].toString()}")

              val context = Job() + Dispatchers.Default
              log("contextCoroutine = $context")*/

/*        val job = scope.launch {
            Log.d(TAG, "scope = $this")
        }
        Log.d(TAG, "job = $job")*/
    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        testLaunchAsync.scope.cancel()
    }
}