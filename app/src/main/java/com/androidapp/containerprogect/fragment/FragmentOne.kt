package com.androidapp.containerprogect.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androidapp.containerprogect.R
import kotlinx.android.synthetic.main.fragment_one.*

const val TAG_LOG = "tag_log"

class FragmentOne : Fragment(R.layout.fragment_one) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG_LOG, "1. onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG_LOG, "2. onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG_LOG, "3. onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG_LOG, "4. onViewCreated()")

        go_to_fragment.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, FragmentTwo.newInstance(), FragmentTwo.TAG)
                    .commit()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG_LOG, "5. onActivityCreated()")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG_LOG, "6. onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG_LOG, "7. onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG_LOG, "8. onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG_LOG, "9. onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG_LOG, "10. onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG_LOG, "11. onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG_LOG, "12. onDetach()")
    }

    companion object {
        const val TAG = "FragmentOne"
        fun newInstance(): FragmentOne {
            return FragmentOne()
        }
    }
}