package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.androidapp.containerprogect.channel.TestChannel
import com.androidapp.containerprogect.concurrency.TestConcurrency
import com.androidapp.containerprogect.context.TestCoroutineContext
import com.androidapp.containerprogect.coroutineFlow.TestCoroutineSharedFlowAndStateFlow
import com.androidapp.containerprogect.coroutineScope.TestCoroutineScope
import com.androidapp.containerprogect.coroutineSelect.TestCoroutineSelect
import com.androidapp.containerprogect.dispatchers.TestDispatchers
import com.androidapp.containerprogect.exception.TestCoroutineException
import com.androidapp.containerprogect.flow.TestFlow
import com.androidapp.containerprogect.lesson_21_builds.Lesson21BuildsFlow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.resume

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
    private val testStateFlow = TestCoroutineSharedFlowAndStateFlow()
    private val testCoroutineSelect = TestCoroutineSelect()

    private val lesson21BuildsFlow = Lesson21BuildsFlow()

    //private val viewModel: TestScopeLiveData by viewModels()
    //private val viewModel: ViewModelFlow2 by viewModels()
    //private val viewModel: TestActor by viewModels()
    private val viewModel: TestConcurrency by viewModels()

    //private lateinit var viewModel: MyViewModel
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainScope.launch {
    /*        lesson21BuildsFlow.createFlow().collect {
                Log.i(TAG, it)
            }

            lesson21BuildsFlow.createAsFlow().collect {
                Log.i(TAG, "$it")
            }

            val count = lesson21BuildsFlow.createFlowTerminalFlow()
            Log.i(TAG, "count = $count")*/

            lesson21BuildsFlow.createAsynchronousChannelFlow().collect {
                Log.i(TAG, "Test: $it")
            }
        }

        viewModel.test6()

        btn_test.setOnClickListener {

        }


/*        editTextName.addTextChangedListener {
            viewModel.setName(it.toString())
        }
        editTextAge.addTextChangedListener {
            viewModel.setAge(it.toString())
        }

        viewModel.dataIsValid.observe(this, {
            log("LiveData: $it")
            btnTest.isEnabled = it
        })*/

/*        lifecycleScope.launch {
            btn_test.text = "New text"
            log("old width is ${btn_test.width}")
            btn_test.awaitLayoutChange()
            log("new width is ${btn_test.width}")
        }*/

        //viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        /* viewModel.getListMovie()
         viewModel.liveData.observe(this, {
             log("Movie size = ${it.results.size}")
         })*/

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

suspend fun View.awaitLayoutChange() = suspendCancellableCoroutine<Unit> { cont ->

    val listener = object : View.OnLayoutChangeListener {

        override fun onLayoutChange(
            view: View?,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            view?.removeOnLayoutChangeListener(this)
            cont.resume(Unit)
        }
    }

    addOnLayoutChangeListener(listener)

    cont.invokeOnCancellation { removeOnLayoutChangeListener(listener) }
}