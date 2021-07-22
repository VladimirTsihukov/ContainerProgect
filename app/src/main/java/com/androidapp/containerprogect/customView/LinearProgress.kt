package com.androidapp.containerprogect.customView

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.androidapp.containerprogect.R
import kotlinx.android.synthetic.main.ic_progress.view.*

class LinearProgress @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var linearProgress : LinearLayout
    private var startWidth = 0
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.ic_progress, this, true)
        linearProgress = view.animateProgress
    }

    fun animateProgress(endWidth: Int) {
        val animator = ValueAnimator.ofInt(startWidth, endWidth).setDuration(100)
        animator.addUpdateListener {
            linearProgress.layoutParams.width = it.animatedValue as Int
            linearProgress.requestLayout()  //вызываестся при изм layout
        }
        val animatorSet = AnimatorSet()
        animatorSet.play(animator)
        animatorSet.start()
        startWidth = endWidth
    }

}