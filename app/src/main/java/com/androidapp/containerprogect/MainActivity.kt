package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.fragment.FragmentAlertDialog
import com.androidapp.containerprogect.fragment.FragmentCustomDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewAlertDialog()
        initViewFragmentAlertDialog()
        initViewCustomDialog()
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
}