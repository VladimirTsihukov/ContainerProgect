package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.fragment.FragmentOne

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FragmentOne.newInstance(), FragmentOne.TAG)
                .commit()
        }
    }
}