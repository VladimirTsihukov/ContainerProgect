package com.androidapp.containerprogect.main_screen_test.screen

import com.androidapp.containerprogect.MainActivity
import com.androidapp.containerprogect.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object MainScreen : KScreen<MainScreen>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    val btnMain = KButton {
        withId(R.id.btn_main)
    }
}