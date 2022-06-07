package com.androidapp.containerprogect.main_screen_test.screen

import com.androidapp.containerprogect.MainActivity2
import com.androidapp.containerprogect.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object SecondScreen : KScreen<SecondScreen>() {
    override val layoutId: Int = R.layout.activity_main2
    override val viewClass: Class<*> = MainActivity2::class.java

    val btnSecondActivity = KButton {
        withId(R.id.btn_second_activity)
    }
}