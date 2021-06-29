package com.androidapp.containerprogect.fragmentRetr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androidapp.containerprogect.App
import com.androidapp.containerprogect.R

class FragmentRetrofit : Fragment(R.layout.fragment_retrofit) {

    private val viewModel: ViewModelFragmentRetrofit by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchQuest(App.questApi)
    }

    companion object {
        const val TAG = "FragmentRetrofit"

        fun newInstance() : FragmentRetrofit {
            return FragmentRetrofit()
        }
    }
}