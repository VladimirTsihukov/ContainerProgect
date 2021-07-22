package com.androidapp.containerprogect.ex_02_liveData

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private var _liveData = MutableLiveData<String>()
    var liveData = _liveData as LiveData<String>
    val liveDataInt = Transformations.map(liveData) {
        it.toIntOrNull()
    }

    val liveDataUser = Transformations.switchMap(liveData) {
        getUser(it)
    }

    private var liveDataMed1 = MutableLiveData<Int>()
    private var liveDataMed2 = MutableLiveData<Int>()
    val mediatorLiveData = MediatorLiveData<Int>()


    init {
        mediatorLiveData()
        workLiveData()
    }

    private fun getUser(user: String): LiveData<User> {
        val age = if (user.toIntOrNull() == null) 0 else user.toInt()
        return MutableLiveData(User("User $user", age = age))
    }

    private fun workLiveData() {
        viewModelScope.launch(Dispatchers.Main) {
            for (x in 0 until 10) {
                delay(1000)
                _liveData.value = x.toString()
                liveData = _liveData
                liveDataMed1.value = x * 10
                liveDataMed2.value = x * 3
            }
        }
    }

    private fun mediatorLiveData() {
        mediatorLiveData.addSource(liveDataMed1) {
            mediatorLiveData.value = it
        }

        mediatorLiveData.addSource(liveDataMed2) {
            if (it == 9) {
                mediatorLiveData.removeSource(liveDataMed2)
                return@addSource
            }
            mediatorLiveData.value = it
        }
    }
}