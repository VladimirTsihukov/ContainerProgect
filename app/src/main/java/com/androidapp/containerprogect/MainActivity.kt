package com.androidapp.containerprogect

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.fragment.FragmentAlertDialog
import com.androidapp.containerprogect.fragment.FragmentCustomDialog
import com.androidapp.containerprogect.fragment.SampleBottomSheet
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dialog
        initViewAlertDialog()
        initViewFragmentAlertDialog()
        initViewCustomDialog()

        initViewBottomSheetDialog()

        //TimePicker
        initViewShowTimePicker()
        initViewShowTimePickerMaterial()

        //DatePicker
        initViewDatePicker()

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
                    val textTime = "Hours=$p1, minutes=$p2"
                    showSnackbar(textTime)
                },
                hour,
                minute,
                true
            )

            timePickerDialog.show()
        }
    }

    private fun initViewShowTimePickerMaterial() {
        btn_material_show_time_picker.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(7)
                .setMinute(0)
                .setTitleText("Select time")
                .build()
            picker.show(supportFragmentManager, "MaterialTimePicker")

            picker.addOnCancelListener {
                Log.i(TAG, "Dialog was cancelled")
            }

            picker.addOnNegativeButtonClickListener {
                Log.i(TAG, "Negative button click")
            }

            picker.addOnPositiveButtonClickListener {
                val newHour = picker.hour
                val minutes = picker.minute
                val textTime = "Hours = $newHour, minutes = $minutes"
                showSnackbar(textTime)
            }

            picker.addOnDismissListener {
                Log.i(TAG, "Dismissed dialog")
            }
        }
    }

    private fun initViewDatePicker() {
        btn_show_date_picker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this, { _, year, month, day ->

                    val textDate = "Year = $year, month = $month, day = $day"
                    showSnackbar(textDate)
                }, year, month, day
            )
            datePickerDialog.show()
        }
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(
            findViewById(R.id.container),
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }
}