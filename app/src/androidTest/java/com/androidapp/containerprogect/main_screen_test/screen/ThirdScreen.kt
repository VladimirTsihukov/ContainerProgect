package com.androidapp.containerprogect.main_screen_test.screen

import com.androidapp.containerprogect.MainActivity3
import com.androidapp.containerprogect.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.toolbar.KToolbar

object ThirdScreen : KScreen<ThirdScreen>() {
    override val layoutId: Int = R.layout.activity_main3
    override val viewClass: Class<*> = MainActivity3::class.java

    val toolbar = KToolbar {
        withId(R.id.toolbar)
    }
}