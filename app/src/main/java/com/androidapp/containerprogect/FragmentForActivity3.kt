package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentForActivity3 : Fragment(R.layout.fragment_3) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_fragment).setOnClickListener {
            Log.i(TAG_TEST, "Click button fragment")
            activity?.finish()
        }
    }
}