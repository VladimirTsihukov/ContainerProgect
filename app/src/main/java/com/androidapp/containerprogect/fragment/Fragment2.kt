package com.androidapp.containerprogect.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.RootViewNavController
import kotlinx.android.synthetic.main.fragment_2.*

class Fragment2 : Fragment(R.layout.fragment_2) {

    private var viewNavController : RootViewNavController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewNavController = it as RootViewNavController
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getString("Fragment1")
        data?.let {
            tv_fragment_2.text = it
        }

        btn_fragment_2_next.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("arg2_1", "Переданное значение")
            viewNavController?.getNavController()?.navigate(R.id.action_fragment2_to_fragment3, bundle)
        }
        btn_fragment_2_back.setOnClickListener {
            viewNavController?.getNavController()?.navigate(R.id.action_fragment2_to_fragment1)
        }
    }

}