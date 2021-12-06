package com.androidapp.containerprogect.testFlowAndView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ViewModelFlow : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val searchResult = _searchQuery.asStateFlow()
            .debounce(500)
            .filter { it.length > 3 }
            .mapLatest {
                "Flow - $it"
            }

    val liveData = MutableLiveData<String>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        scope.launch {
            searchResult.collect {
                liveData.value = it
            }
        }
    }

    fun search(query: String) {
        _searchQuery.value = query
    }
}