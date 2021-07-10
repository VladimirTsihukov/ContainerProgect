package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Job())
    private var formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run.setOnClickListener {
            onRun5()
        }

        btn_cancel.setOnClickListener {
            onCancel()
        }

        btn_run2.setOnClickListener {
            coroutineStart()
        }

/*        val job = scope.launch {
            Log.d(TAG, "scope = $this")
        }
        Log.d(TAG, "job = $job")*/
    }

    private fun onRun() {
        log("onRun, start")

        job = scope.launch {
            log("coroutine, start")
            var x = 0
            while (x < 5 && isActive) {
                //TimeUnit.MILLISECONDS.sleep(1000)
                delay(1000)
                log("coroutine, ${x++}, isActive = $isActive")
            }
            log("coroutine, end")
        }
        log("onRun, end")
    }

    private fun onRun2() {
        scope.launch {
            log("parent coroutine, start")

           val job = launch {
                log("child coroutine, start")
                TimeUnit.MILLISECONDS.sleep(1000)
                log("child coroutine, end")
            }
            log("parent coroutine, wait child completes")

            job.join()

            log("parent coroutine, end")
        }
    }

    private fun onRun3() {
        scope.launch {
            log("parent coroutine, start")

            val job1 = launch {
                log("job1, start")
                TimeUnit.MILLISECONDS.sleep(1000)
            }

            val job2 = launch {
                log("job2, start")
                TimeUnit.MILLISECONDS.sleep(1500)
            }

            log("parent coroutine, wait child completes")

            job1.join()
            job2.join()

            log("parent coroutine, end")
        }
    }

    private fun coroutineCreate() {
        log("coroutineCreate, start")
         job = scope.launch(start = CoroutineStart.LAZY) {
            log("coroutine, start")
            TimeUnit.MILLISECONDS.sleep(1000)
            log("coroutine, end")
        }
        log("coroutineCreate, end")
    }

    private fun coroutineStart() {
        log("coroutineStart, start")
        job.start()
        log("coroutineStart, end")
    }

    private fun onRunAsync() {
        scope.launch {
            log("parent coroutine, start")

            val deferred = scope.async {
                log("child coroutine, start")
                TimeUnit.MILLISECONDS.sleep(1000)
                log("child coroutine, end")
                "async result"
            }

            log("parent coroutine, wait until child returns result")

            val result = deferred.await()

            log("parent coroutine, child returns: $result")
            log("parent coroutine, end")
        }
    }

    private fun onRun4() {
        scope.launch {
            log("parent coroutine, start")

            val data1 = getData1()
            val data2 = getData2()

            val result = "$data1 - $data2"

            log("parent coroutine, child returned: $result")

            log("parent coroutine, end")
        }
    }

    private fun onRun5() {
        scope.launch {
            log("№1 parent coroutine, start")

            val data1 = async {getData1()}
            val data2 = async {getData2()}

            log("№2 WAIT parent coroutine")
            delay(2000)
            log("№5 parent coroutine, wait until children return result")
            val result = "${data1.await()} - ${data2.await()}"
            log("№6 parent coroutine, child returned: $result")

            log("№7 parent coroutine, end")
        }
    }

    private suspend fun getData1() : String {
        log("№3 Start getData 1")
        delay(1000)
        return "DATA 1"
    }

    private suspend fun getData2() : String {
        log("№4 Start getData 2")
        delay(1500)
        return "DATA 2"
    }


    private fun onCancel() {
        log("onCancel")
        scope.cancel()
    }

    private fun log(text: String) {
        Log.i(TAG, "${formatter.format(Date())} $text [${Thread.currentThread().name}]")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }
}