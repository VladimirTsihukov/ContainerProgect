package com.androidapp.containerprogect

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.fragment.FragmentAlertDialog
import com.androidapp.containerprogect.fragment.FragmentCustomDialog
import com.androidapp.containerprogect.fragment.SampleBottomSheet
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewAlertDialog()
        initViewFragmentAlertDialog()
        initViewCustomDialog()
        initViewBottomSheetDialog()
        initViewShowTimePicker()

    }

    private fun initViewAlertDialog() {
        btn_show_alert_dialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Title")
                .setPositiveButton("Ok") { _, _ ->
                    showToast(this, "Positive button")
                }
                .setNegativeButton("Cancel") { _, _ ->
                    showToast(this, "Negative button")
                }
                .setNeutralButton("Retry") { _, _ ->
                    showToast(this, "Retry")
                }
                .setOnCancelListener {
                    showToast(this, "Alert dismissed")
                }
                .show()
        }
    }

    private fun initViewFragmentAlertDialog() {
        btn_fragment_show_alert_dialog.setOnClickListener {
            val dialog = FragmentAlertDialog()
            dialog.show(supportFragmentManager, FragmentAlertDialog.TAG)
        }
    }

    private fun initViewCustomDialog() {
        btn_show_custom_dialog_fragment.setOnClickListener {
            val dialog = FragmentCustomDialog()
            dialog.show(supportFragmentManager, FragmentCustomDialog.TAG)
        }
    }

    private fun initViewBottomSheetDialog() {
        btn_show_bottom_sheet_dialog.setOnClickListener {
            val dialogBottomSheet = SampleBottomSheet()
            dialogBottomSheet.show(supportFragmentManager, SampleBottomSheet.TAG)
        }
    }

    private fun initViewShowTimePicker() {
        btn_show_time_picker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this@MainActivity,
                { p0, p1, p2 ->
                    Log.i(TAG, "Your chosen: p0=$p0, hours=$p1, minutes=$p2")
                    Snackbar.make(
                        this.findViewById(R.id.container),
                        "Your chosen: hours=$p1, minutes=$p2",
                        Snackbar.LENGTH_LONG
                    ).show()
                },
                hour,
                minute,
                true
            )

            timePickerDialog.show()
        }
    }
}