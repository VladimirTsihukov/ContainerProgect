package com.androidapp.containerprogect.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.androidapp.containerprogect.showToast

class FragmentAlertDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = requireActivity()
        val builder = AlertDialog.Builder(activity)
            .setTitle("Title")
            .setPositiveButton("Ok") { _, _ ->
                showToast(activity, "Fragment Positive button")
            }
            .setNegativeButton("Cancel") { _, _ ->
                showToast(activity, "Fragment Negative button")
            }
            .setNeutralButton("Retry") { _, _ ->
                showToast(activity, "Fragment Retry")
            }

        return builder.create()
    }

    override fun onCancel(dialog: DialogInterface) {
        showToast(requireActivity(), "Fragment alert dismissed")
    }

    companion object {
        const val TAG = "FragmentAlertDialog"
    }
}