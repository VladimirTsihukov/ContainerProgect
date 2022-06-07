package com.androidapp.containerprogect.main_screen_test.screen

import com.androidapp.containerprogect.FragmentForActivity3
import com.androidapp.containerprogect.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object FragmentScreen : KScreen<FragmentScreen>() {
    override val layoutId: Int = R.layout.fragment_3
    override val viewClass: Class<*> = FragmentForActivity3::class.java

    val button = KButton {
        withId(R.id.btn_fragment)
    }
}