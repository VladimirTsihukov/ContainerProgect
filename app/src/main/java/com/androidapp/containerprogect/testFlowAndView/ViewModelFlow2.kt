package com.androidapp.containerprogect.testFlowAndView

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class ViewModelFlow2 : ViewModel() {

    private val _name = MutableStateFlow("")
    private val _age = MutableStateFlow("")

    val dataIsValid: LiveData<Boolean> = combine(_name, _age) { name, age ->
        isValidAge(age) && isValidName(name)
    }.asLiveData()

    fun setName(name: String) {
        _name.value = name
    }

    fun setAge(age: String) {
        _age.value = age
    }

    private fun isValidName(name: String): Boolean = name.length > 2

    private fun isValidAge(age: String): Boolean {
        age.toIntOrNull()?.let {
            if (it > 0) return true
        }
        return false
    }
}