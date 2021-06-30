package com.androidapp.containerprogect.fragment

import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R

class FragmentTwo : Fragment(R.layout.fragment_two) {



    companion object {
        const val TAG = "FragmentTwo"
        fun newInstance() : FragmentTwo {
            return FragmentTwo()
        }
    }
}