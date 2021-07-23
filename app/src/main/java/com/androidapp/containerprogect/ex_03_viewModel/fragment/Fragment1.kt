package com.androidapp.containerprogect.ex_03_viewModel.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.ex_03_viewModel.ViewModelFragments
import kotlinx.android.synthetic.main.fragment_1.*

class Fragment1 : Fragment(R.layout.fragment_1) {

    private var vieModel: ViewModelFragments? = null
    //private var current = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //retainInstance = true
        activity?.let {
            vieModel = ViewModelProvider(it).get(ViewModelFragments::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_click_fragment_1.setOnClickListener {
            vieModel?.let {
                //it.setLiveData("Click button = ${++current}")
                it.setLiveData("Click button =")
            }
        }
    }
}