package com.androidapp.containerprogect

import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class TestLaunchAsync {
    val scope = CoroutineScope(Job())
    private lateinit var job: Job

     fun onRun() {
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

     fun onRun2() {
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

     fun onRun3() {
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

     fun coroutineCreate() {
        log("coroutineCreate, start")
        job = scope.launch(start = CoroutineStart.LAZY) {
            log("coroutine, start")
            TimeUnit.MILLISECONDS.sleep(1000)
            log("coroutine, end")
        }
        log("coroutineCreate, end")
    }

     fun coroutineStart() {
        log("coroutineStart, start")
        job.start()
        log("coroutineStart, end")
    }

     fun onRunAsync() {
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

     fun onRun4() {
        scope.launch {
            log("parent coroutine, start")

            val data1 = getData1()
            val data2 = getData2()

            val result = "$data1 - $data2"

            log("parent coroutine, child returned: $result")

            log("parent coroutine, end")
        }
    }

     fun onRun5() {
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

     suspend fun getData1() : String {
        log("№3 Start getData 1")
        delay(1000)
        return "DATA 1"
    }

     suspend fun getData2() : String {
        log("№4 Start getData 2")
        delay(1500)
        return "DATA 2"
    }

     fun onCancel() {
        log("onCancel")
        scope.cancel()
    }
}