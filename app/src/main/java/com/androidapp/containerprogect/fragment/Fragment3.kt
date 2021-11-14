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

        val dataArg2 = arguments?.getString("arg2")
        dataArg2?.let {
            tv_fragment_3.text = dataArg2
        }

        val test = Fragment4Directions.actionFragment4ToFragment5()

        btn_fragment_3_next.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("product_id", 325)
            viewNavController?.getNavController()?.navigate(R.id.action_fragment3_to_mainActivity2, bundle)
        }
        btn_fragment_3_back.setOnClickListener {
            viewNavController?.getNavController()?.navigate(R.id.action_fragment3_to_fragment2)
        }

        btn_fragment_to_activity_3.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("Fragment3", "Activity 3 from Fragment 3")
            viewNavController?.getNavController()?.navigate(R.id.action_fragment3_to_mainActivity3, bundle)
        }
    }

}