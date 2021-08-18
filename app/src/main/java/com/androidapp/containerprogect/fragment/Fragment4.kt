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
    private var data: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewNavController = it as RootViewNavController
        }

        data = activity?.intent?.extras?.getInt("product_id", 0)!!

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        data.let {
            tv_fragment_4.text = it.toString()
        }

        btn_fragment_4_next.setOnClickListener {
            viewNavController?.getNavController()?.navigate(R.id.action_fragment4_to_fragment5)
        }
        btn_fragment_4_back.setOnClickListener {
            activity?.finish()
        }
    }

}