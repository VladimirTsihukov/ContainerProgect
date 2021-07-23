package com.androidapp.containerprogect.ex_03_viewModel.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.ex_03_viewModel.ViewModelFragments
import kotlinx.android.synthetic.main.fragment_2.*

class Fragment2 : Fragment(R.layout.fragment_2) {

    private var vieModel: ViewModelFragments? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            vieModel = ViewModelProvider(it).get(ViewModelFragments::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vieModel?.let { viewModel ->
            viewModel.getLiveData().observe(viewLifecycleOwner, {
                text_view.text = it
            })
        }
    }
}