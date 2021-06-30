package com.androidapp.containerprogect.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment(R.layout.fragment_one) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        go_to_fragment.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, FragmentTwo.newInstance(), FragmentTwo.TAG)
                    .commit()
            }
        }
    }

    companion object {
        const val TAG = "FragmentOne"
        fun newInstance(): FragmentOne {
            return FragmentOne()
        }
    }
}