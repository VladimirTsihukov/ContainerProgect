package com.androidapp.containerprogect.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.RootViewNavController
import kotlinx.android.synthetic.main.fragment_4.*

class Fragment4 : Fragment(R.layout.fragment_4) {

    private var viewNavController: RootViewNavController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewNavController = it as RootViewNavController
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getString("arg4")
        data?.let {
            tv_fragment_4.text = it
        }

        btn_fragment_4_next.setOnClickListener {
            viewNavController?.getNavController()?.navigate(R.id.action_fragment4_to_fragment5)
        }
        btn_fragment_4_back.setOnClickListener {
            activity?.finish()
        }
    }

}