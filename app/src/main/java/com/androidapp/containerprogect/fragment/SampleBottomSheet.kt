package com.androidapp.containerprogect.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.showToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment.view.*

class SampleBottomSheet : BottomSheetDialogFragment() {

/*    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.dialog_fragment, null)
        dialog.setContentView(contentView)

        (contentView as View).setBackgroundColor(
            ContextCompat.getColor(
                contentView.context, android.R.color.holo_red_light
            )
        )
        Log.i(TAG, "setupDialog()")

    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView()")
        return inflater.inflate(R.layout.dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "onViewCreated()")

        view.btn_sheet_ok.setOnClickListener {
            showToast(context, "Click OK")
            dismiss()
        }

        view.btn_sheet_cancel.setOnClickListener {
            showToast(context, "Click CANCEL")
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart()")
        val width = resources.displayMetrics.widthPixels
        val height = (resources.displayMetrics.heightPixels * 0.5).toInt()

        dialog?.window?.setLayout(width, height)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Log.i(TAG, "onCancel()")
        showToast(context, "You dismissed dialog")
    }

    companion object {
        const val TAG = "SampleBottomSheet"
    }
}