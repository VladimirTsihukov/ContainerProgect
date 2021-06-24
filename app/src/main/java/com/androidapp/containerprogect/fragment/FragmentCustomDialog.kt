package com.androidapp.containerprogect.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.showToast
import kotlinx.android.synthetic.main.fragment_dialog.*

class FragmentCustomDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_ok.setOnClickListener {
            showToast(context, "You tapped ok")
            dismiss()
        }

        btn_cancel.setOnClickListener {
            showToast(context, "You tapped cancel")
            dismiss()
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        showToast(context, "You dismissed dialog")
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.4).toInt()
        dialog?.window?.setLayout(width, height)
    }

    companion object {
        const val TAG = "FragmentCustomDialog"
    }
}