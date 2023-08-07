package com.androidapp.containerprogect.lesson_9

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class FragmentLess_9 : Fragment(R.layout.fragment_less_9) {

    private val formatter = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    private val scope = CoroutineScope(Job())

    private var job: Job? = null

    companion object {
        fun newInstance() = FragmentLess_9()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_run)?.setOnClickListener {
            onRunAsync()
        }
        view.findViewById<Button>(R.id.btn_run_2)?.setOnClickListener {
            onRunStart()
        }
        view.findViewById<Button>(R.id.btn_cancel)?.setOnClickListener {

        }

    }

    private fun onRun1() {
        scope.launch {
            log("parent coroutine, start")

            launch {
                log("child coroutine, start")
                TimeUnit.MILLISECONDS.sleep(1000)
                log("child coroutine, end")
            }

            log("parent coroutine, end")
        }
    }

    private fun onRun2() {
        scope.launch {
            log("parent coroutine, start")

            val job = launch {
                log("child coroutine, start")
                TimeUnit.MILLISECONDS.sleep(1000)
                log("child coroutine, end")
            }

            log("parent coroutine, wait until child completes")
            job.join()

            log("parent coroutine, end")
        }
    }

    private fun onRun3() {
        scope.launch {
            log("parent coroutine, start")

            val job = launch {
                TimeUnit.MILLISECONDS.sleep(1000)
            }

            val job2 = launch {
                TimeUnit.MILLISECONDS.sleep(1500)
            }

            log("parent coroutine, wait until children complete")
            job.join()
            job2.join()

            log("parent coroutine, end")
        }
    }

    private fun onRunCreate() {
        log("onRun, start")

        job = scope.launch(start = CoroutineStart.LAZY) {
            log("coroutine, start")
            TimeUnit.MILLISECONDS.sleep(1000)
            log("coroutine, end")
        }

        log("onRun, end")
    }

    private fun onRunStart() {
        log("onRun2, start")
        job?.start()
        log("onRun2, end")
    }

    private fun onRunAsync() {
        scope.launch {
            log("parent coroutine, start")

            val deferred = async() {
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

    private fun log(text: String) {
        Log.d(TAG, "${formatter.format(Date())} $text [${Thread.currentThread().name}]")
    }
}