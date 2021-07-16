package com.androidapp.containerprogect.coroutineRetrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.containerprogect.coroutineRetrofit.data.MoviesList
import com.androidapp.containerprogect.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {

    private var _liveData = MutableLiveData<MoviesList>()
    var liveData = _liveData as LiveData<MoviesList>

    fun getListMovie()  {
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