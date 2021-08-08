package com.androidapp.containerprogect

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.scale
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

        //QuoteSpan
        val strQuoteSpan = "Первое предложение цитаты\nВторое предложнеие цитаты".toSpannable()
        strQuoteSpan.setSpan(QuoteSpan(Color.RED), 0, 0,0)
        text_under_quote_span.text = strQuoteSpan

        //AlignmentSpan
        val testAlignmentSpan = "Первое предложени\nВторое предложение\nТретье предложение".toSpannable()
        testAlignmentSpan[0..18] = AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER)
        testAlignmentSpan[0..18] = ForegroundColorSpan(Color.RED)

        testAlignmentSpan[18..37] = AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL)
        testAlignmentSpan[18..37] = ForegroundColorSpan(Color.GREEN)

        testAlignmentSpan[37..testAlignmentSpan.length] = AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE)
        testAlignmentSpan[37..testAlignmentSpan.length] = ForegroundColorSpan(Color.BLUE)

        text_alignment_span.text = testAlignmentSpan

        //ImageSpan
        val textImageSpan = "Картинка между словами".toSpannable()
        textImageSpan[8..9] = ImageSpan(this, R.drawable.ic_baseline_adb_24)
        text_image_span.text = textImageSpan

        //DynamicDrawableSpan
        val textDynamic = "Какой-то длинный текст про кота Ваську, который слушает даест.".toSpannable()
        textDynamic[(textDynamic.length - 1)..textDynamic.length] = object : DynamicDrawableSpan() {
            override fun getDrawable(): Drawable? {
                val drawable = getDrawable(R.drawable.ic_baseline_adb_24)
                drawable?.setBounds(
                    0,
                    0,
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight
                )
                return drawable
            }
        }
        text_dynamic_icon.text = textDynamic

        //IconMarginSpan
        val textIconMarginSpan = ("Здесь находится длинный текст про замечательных котиков, " +
                "которые помогают изучать программирование").toSpannable()

        val iconBitmap = resources.getDrawable(R.drawable.ic_baseline_bug_report_24).toBitmap()
        iconBitmap.scale(iconBitmap.width/4, iconBitmap.height/4, true)
        textIconMarginSpan[0..10] = IconMarginSpan(iconBitmap, 10)
        text_margin_icon.text = textIconMarginSpan

        //TextAppearanceSpan
        val textAppearance = "Первая строка предложения\nВторая строка\nТретья строка".toSpannable()
        textAppearance[26..40] = TextAppearanceSpan(this, R.style.SpecialTextAppearance)
        text_appearance_span.text = textAppearance

        //MaskFilterSpan
        val textMaksFiltter = ("MaskFilterSpan Blur Normal." +
                "\nMaskFilterSpan Blur Inner." +
                "\nMaskFilterSpan Blur Outer." +
                "\nMaskFilterSpan Blur Solid.").toSpannable()
        textMaksFiltter[0..27] = MaskFilterSpan(BlurMaskFilter(5F, BlurMaskFilter.Blur.NORMAL))
        textMaksFiltter[28..56] = MaskFilterSpan(BlurMaskFilter(15F, BlurMaskFilter.Blur.INNER))
        textMaksFiltter[57..83] = MaskFilterSpan(BlurMaskFilter(5F, BlurMaskFilter.Blur.OUTER))
        textMaksFiltter[83..textMaksFiltter.length] = MaskFilterSpan(BlurMaskFilter(5F, BlurMaskFilter.Blur.SOLID))
        text_max_filter.text = textMaksFiltter

        //LeadingMarginSpan
        val textLeadingMarginSpan = ("Первая очень длинная строка про кота Ваську."
                + "\n\nВторая длинная строка про кота Барсика."
                + "\n\nТретья длинная строка про кота Мурзика."
                + "\n\nЧетвёртная длинная строка про наглого кота Рыжика."
                ).toSpannable()
        textLeadingMarginSpan[0..textLeadingMarginSpan.length] = LeadingMarginSpan.Standard(100, 50)

        text_leading_margin_span.text = textLeadingMarginSpan

        //SpannableStringBuilder
        val testStrBuilder = SpannableStringBuilder("Котёнок")
        testStrBuilder[1..4] = ForegroundColorSpan(Color.RED)
        testStrBuilder.insert(1, "Z")
        testStrBuilder.insert(5, "W")
        text_string_builder.text = testStrBuilder
    }
}

fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        return this.bitmap
    }

    val bitmap = Bitmap.createBitmap(this.intrinsicWidth, this.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    this.setBounds(0, 0, canvas.width, canvas.height)
    this.draw(canvas)

    return bitmap
}