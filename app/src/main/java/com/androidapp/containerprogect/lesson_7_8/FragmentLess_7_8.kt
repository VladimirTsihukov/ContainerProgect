package com.androidapp.containerprogect.lesson_7_8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class FragmentLess_7_8 : Fragment() {

    private val scope = CoroutineScope(Job())

    private var job: Job? = null

    companion object {
        fun newInstance() = FragmentLess_7_8()
    }

    private lateinit var viewModel: ViewModel_7_8

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragment_less_7_8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModel_7_8::class.java]

        view.findViewById<Button>(R.id.btn_run)?.setOnClickListener {
            onRun()
        }
        view.findViewById<Button>(R.id.btn_cancel)?.setOnClickListener {
            onCancel()
        }
    }

    private fun onRun() {
        log("onRun, start")

        job = scope.launch {
            log("coroutine, start")
            var x = 0
            while (x < 5 && isActive) {
                delay(1_000)
                log("coroutine, ${x++}, isActive = $isActive")
            }
            log("coroutine, end")
        }

        log("onRun, end")
    }

    private fun onCancel() {
        log("onCancel")
        job?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        scope.cancel()
    }

}