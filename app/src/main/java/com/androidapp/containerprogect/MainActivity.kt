package com.androidapp.containerprogect

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_main.*
import kotlinx.android.synthetic.main.content_main.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<ViewGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

        bottomSheetCallback()

        initViewExpandButton()
        initViewCollapseButton()
        initViewHidingButton()
    }

    private fun bottomSheetCallback() {
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                var state = ""
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        state = "STATE_COLLAPSED"
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        state = "STATE_DRAGGING"
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        state = "STATE_EXPANDED"
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        state = "STATE_HALF_EXPANDED"
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        state = "STATE_HIDDEN"
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        state = "STATE_SETTLING"
                    }
                }
                Log.i(TAG, state)
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.i(TAG, "slide = $slideOffset")
                fab.animate()
                    .scaleX(0 + slideOffset)
                    .scaleY(0 + slideOffset)
                    .start()
            }
        })
    }

    private fun initViewExpandButton() {
        expandingBottomSheetButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun initViewCollapseButton() {
        collapsingBottomSheetButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun initViewHidingButton() {
        hidingBottomSheetButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }
}
