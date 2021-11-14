package com.androidapp.containerprogect.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.RootViewNavController
import com.androidapp.containerprogect.TAG
import kotlinx.android.synthetic.main.fragment_1.*

class Fragment1 : Fragment(R.layout.fragment_1) {

    private var viewNavController : RootViewNavController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewNavController = it as RootViewNavController
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "Fragment A: onViewCreated()")
        btn_fragment_1_next.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("Fragment1", "Текст из Fragment 1")
            findNavController().navigate(R.id.action_fragment1_to_fragment2, bundle)
        }
        btn_fragment_1_back.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "Fragment A: onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Fragment A: onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "Fragment A: onDetach()")
    }
}
