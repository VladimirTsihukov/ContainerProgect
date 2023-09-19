package com.androidapp.containerprogect.lesson_28

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.androidapp.containerprogect.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentLess_28 : Fragment(R.layout.fragment_less_9) {

    private val viewModel by viewModels<ViewModel_28>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.second.collect {
                view.findViewById<TextView>(R.id.text).text = it
                delay(2_000)
            }
        }

        lifecycleScope.launch {
            viewModel.second.collect {
                view.findViewById<TextView>(R.id.text_2).text = it
            }
        }
    }
}