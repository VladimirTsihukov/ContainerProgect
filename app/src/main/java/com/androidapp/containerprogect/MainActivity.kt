package com.androidapp.containerprogect

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testString = "Очень длинный текст для опытов"

        val spannableString = SpannableString(testString)
        spannableString.setSpan(UnderlineSpan(), 6, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text_span_1.text = spannableString

        val btnSpannable = SpannableString("Text spannable")
        btnSpannable.setSpan(StyleSpan(Typeface.BOLD_ITALIC), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        btn_spannable.text = btnSpannable
        btn_spannable.setOnClickListener {
            val testColorSpan = SpannableString("Зеленый текст курсивом в Toast")
            testColorSpan.setSpan(StyleSpan(Typeface.ITALIC),0, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            testColorSpan.setSpan(ForegroundColorSpan(getColor(R.color.teal_200)), 0, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            makeText(this, testColorSpan, Toast.LENGTH_SHORT).show()
        }

        val testSpannable = "Кто не любит котов - собаки".toSpannable()
        testSpannable[0..6] = TypefaceSpan("serif")
        text_span_2.text = testSpannable

        //BackgroundColorSpan
        spannableString.setSpan(BackgroundColorSpan(ContextCompat.getColor
            (this, R.color.purple_500)), 6, 13 ,0)
        text_span_3.text = spannableString

        //ClickSpan
        val spanClick = SpannableString(testString)
        val clickSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                makeText(this@MainActivity, "Click test span", Toast.LENGTH_SHORT).show()
            }
        }
        spanClick.setSpan(clickSpan, 14, 19, 0)
        text_span_click.movementMethod = LinkMovementMethod.getInstance()
        text_span_click.text = spanClick

        //URLSpan
        val textURL = "Текст для ссылки".toSpannable()
        textURL[10..16] = object : URLSpan("http://developer.alexanderklimov.ru/android/") {
            override fun onClick(widget: View) {
                super.onClick(widget)
                makeText(this@MainActivity, "URL click", Toast.LENGTH_SHORT).show()
            }
        }
        text_span_click_url.text = textURL
        text_span_click_url.movementMethod = LinkMovementMethod.getInstance()

        //SubScriptSpan
        val textSubscript = "H20".toSpannable()
        textSubscript.setSpan(SubscriptSpan(), 1, 2, 0)
        text_subscript.text = textSubscript

        //SuperscriptSpan
        val superScriptSpan = "H20, 100m2".toSpannable()
        superScriptSpan[1..2] = SubscriptSpan()
        superScriptSpan[9..10] = SuperscriptSpan()
        text_super_subscript.text = superScriptSpan

        //Underline
        val textUnderLine = "Подчеркнутый текст".toSpannable()
        textUnderLine[0..textUnderLine.length] = UnderlineSpan()
        text_under_line.text = textUnderLine

        //StrikethroughSpan
        val strikethroughSpan = "Зачеркнутый текст".toSpannable()
        strikethroughSpan[0..strikethroughSpan.length] = StrikethroughSpan()
        text_under_strike.text = strikethroughSpan

        //RelativeSizeSpan
        val sizeSpan = "H20".toSpannable()
        //уменьшим символ
        sizeSpan[1..2] = RelativeSizeSpan(0.75F)
        text_under_relative.text = sizeSpan

        //BulletSpan
        val spannableBullet = SpannableString("My text " +
                "\nbullet one " +
                "\nbullet two")
        //spannableBullet[9..18] = BulletSpan(40, Color.BLUE, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //spannableBullet[10..spannableBullet.length] = BulletSpan(40, Color.RED, 10)
        spannableBullet.setSpan(BulletSpan(40, Color.BLUE, 10),9, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableBullet.setSpan(BulletSpan(40, Color.GREEN, 15), 21, spannableBullet.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text_under_bullet.text = spannableBullet

    }
}