package com.androidapp.containerprogect.coroutineRetrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.containerprogect.coroutineRetrofit.data.MoviesList
import com.androidapp.containerprogect.log
import kotlinx.coroutines.*

class MyViewModel : ViewModel() {

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        log("Error - ${throwable.message}, name - ${coroutineContext[CoroutineName]?.name}")
    }
    private var _liveData = MutableLiveData<MoviesList>()
    var liveData = _liveData as LiveData<MoviesList>

    init {
        testErrorViewModelScope3()
    }

    private fun testErrorViewModelScope1() {
        viewModelScope.launch(CoroutineName("Launch") + handler) {

            log("START COROUTINE")

            val result1 = async(CoroutineName("Async 1")) { resultError() }

            val result2 = async(CoroutineName("Async 2")) { result2() }

            try {
                log("Result1 = ${result1.await()}, Result2 = ${result2.await()}")
            } catch (e: Exception) {
                log("Error - ${e.message}")
            }

            log("STOP COROUTINE")
        }
    }

    private fun testErrorViewModelScope2() {
        viewModelScope.launch(CoroutineName("Launch") + handler) {

            log("START COROUTINE")

            val result = try {
                coroutineScope() {
                    val result1 = async(CoroutineName("Async 1") + Dispatchers.IO) { resultError() }

                    val result2 = async(CoroutineName("Async 2") + Dispatchers.IO) { result2() }

                    "Result1 = ${result1.await()}, Result2 = ${result2.await()}"
                }
            } catch (e: Exception) {
                "Error - ${e.message}"
            }

            log(result)

            log("STOP COROUTINE")
        }
    }

    private fun testErrorViewModelScope3() {
        viewModelScope.launch(CoroutineName("Launch") + handler) {
            log("START COROUTINE")

            supervisorScope() {
                val result1 = async(CoroutineName("Async 1")) { resultError() }

                val result2 = async(CoroutineName("Async 2")) { result2() }

                try {
                    log("Result1 = ${result1.await()}")
                } catch (e: Exception) {
                    log("Error in result_1")
                }

                log("Result2 = ${result2.await()}")
            }

            log("STOP COROUTINE")
        }
    }

    private suspend fun result1(): Int {
        var result = 0
        for (i in 1..5) {
            delay(500)
            log("Coroutine_2 = $i")
            result = i

        }
        return result
    }

    private suspend fun result2(): Int {
        var result = 0
        for (i in 11..15) {
            delay(750)
            log("Coroutine_1 = $i")
            result = i
        }
        return result
    }

    private suspend fun resultError(): Int {
        var result = 0
        for (i in 1..5) {
            delay(500)
            log("Coroutine_2 = $i")
            result = i
            if (i == 3) result = i / 0
        }
        return result
    }


    fun getListMovie() {
        viewModelScope.launch {
            try {
                val response = ApiFactory.apiServiceMovie.getListMovie()
                if (response.isSuccessful) {
                    _liveData.value = response.body()
                    liveData = _liveData
                } else {
                    log("Server is not responding")
                }
            } catch (e: Exception) {
                log("Error - ${e.message}")
            }

        }
    }

    private suspend fun getMovieList() = withContext(Dispatchers.IO) {
        ApiFactory.apiServiceMovie.getListMovie()
    }
}