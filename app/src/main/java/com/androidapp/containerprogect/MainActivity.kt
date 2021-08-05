package com.androidapp.containerprogect

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.core.text.set
import androidx.core.text.toSpannable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spannableString = SpannableString("Очень длинный текст для опытов")
        spannableString.setSpan(UnderlineSpan(), 6, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text_span_1.text = spannableString

        val btnSpannable = SpannableString("Text spannable")
        btnSpannable.setSpan(StyleSpan(Typeface.BOLD_ITALIC), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        btn_spannable.text = btnSpannable

        val testSpannable = "Кто не любит котов - собаки".toSpannable()
        testSpannable[0..6] = TypefaceSpan("serif")
        text_span_2.text = testSpannable

        val testColorSpan = SpannableString("Зеленый текст курсивом в Toast")
        testColorSpan.setSpan(StyleSpan(Typeface.ITALIC),0, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        testColorSpan.setSpan(ForegroundColorSpan(getColor(R.color.teal_200)), 0, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        Toast.makeText(this, testColorSpan, Toast.LENGTH_SHORT).show()
    }
}