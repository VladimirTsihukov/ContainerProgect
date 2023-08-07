package com.androidapp.containerprogect.lesson_11

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.resume

class FragmentLess_11 : Fragment(R.layout.fragment_less_9) {

    private val scope = CoroutineScope(Dispatchers.Unconfined)
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_run).setOnClickListener {
            onRun()
        }

        /*        repeat(10) {
                    scope.launch {
                        Log.d(TAG,"Coroutine $it is started")
                        TimeUnit.SECONDS.sleep(1)
                        Log.d(TAG,"Coroutine $it is finished")
                    }
                }*/
    }

    private fun onRun() {
        if (job?.isActive == true) return
        job = scope.launch {
            log("start coroutine")
            val data = getData()
            log("end coroutine")
            //updateUI(data)
        }
    }

    private suspend fun getData(): String =
        suspendCancellableCoroutine {
            log("suspend function, start")
            thread {
                log("suspend function, background work")
                TimeUnit.SECONDS.sleep(3)
                it.resume("Data")
            }
        }

    private fun updateUI(date: String) {
        requireView().findViewById<Button>(R.id.btn_enter_text).text = date
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}