package com.androidapp.containerprogect

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rString = getString(R.string.bus_details_baggage_condition_info)
        val styledString = Html.fromHtml(rString)
        tv_text.text = styledString
    }
}