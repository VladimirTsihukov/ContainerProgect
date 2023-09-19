package com.androidapp.containerprogect.lesson_32

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.TAG
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

class FragmentLess_32 : Fragment(R.layout.fragment_less_9) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            val asunc1 = async {
                delay(1_000)
                "Async №1"
            }
            val asunc2 = async {
                delay(2_000)
                "Async №2"
            }

            val result = select<String> {

                val result1 = asunc1.onAwait {
                    "Result - $it"
                }

                asunc2.onAwait {
                    "Result - $it"
                }

            }

            Log.d(TAG, result)

        }
    }
}