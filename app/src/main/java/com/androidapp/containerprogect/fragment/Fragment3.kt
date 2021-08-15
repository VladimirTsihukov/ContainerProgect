package com.androidapp.containerprogect.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.RootViewNavController
import kotlinx.android.synthetic.main.fragment_3.*

class Fragment3 : Fragment(R.layout.fragment_3) {

    private var viewNavController: RootViewNavController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewNavController = it as RootViewNavController
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_fragment_3_next.setOnClickListener {
            viewNavController?.getNavController()?.navigate(R.id.action_fragment3_to_mainActivity2)
        }
        btn_fragment_3_back.setOnClickListener {
            viewNavController?.getNavController()?.navigate(R.id.action_fragment3_to_fragment2)
        }
    }

}