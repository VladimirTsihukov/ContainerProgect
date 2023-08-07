package com.androidapp.containerprogect.lesson_10

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class FragmentLess_10 : Fragment(R.layout.fragment_less_9) {

    private val userData = UserData(123, "Alex", 25)
    private val scope = CoroutineScope(Dispatchers.Default + userData)
    private val scopeEmpty = CoroutineScope(EmptyCoroutineContext + userData)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "New context №1: ${contextToString(scopeEmpty.coroutineContext)}")

        scopeEmpty.launch {
            Log.d(TAG, "New context №2: ${contextToString(coroutineContext)}")
            Log.d(TAG, "User id №1: ${coroutineContext[UserData]?.id}")

            launch(Dispatchers.IO) {
                Log.d(TAG, "New context №3: ${contextToString(coroutineContext)}")
                Log.d(TAG, "User id №2: ${coroutineContext[UserData]?.id}")

                launch {
                    Log.d(TAG, "New context №4: ${contextToString(coroutineContext)}")
                    Log.d(TAG, "User id №3: ${coroutineContext[UserData]?.id}")
                }
            }
        }

/*        scope.launch {
            Log.d(TAG, "User id: ${coroutineContext[UserData]?.id}")
            Log.d(TAG, "User name: ${coroutineContext[UserData]?.name}")
            Log.d(TAG, "User age: ${coroutineContext[UserData]?.age}")
        }*/
    }

    private fun contextToString(context: CoroutineContext): String =
        "Job = ${context[Job]}, Dispatcher = ${context[ContinuationInterceptor]}"
}

data class UserData(
    val id: Int,
    val name: String,
    val age: Int
): AbstractCoroutineContextElement(UserData) {
    companion object Key: CoroutineContext.Key<UserData>
}