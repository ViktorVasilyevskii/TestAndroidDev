package com.vasilevskii.testandroiddev.logic

import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.vasilevskii.testandroiddev.`interface`.IButtonAnimation

class LottieAnimation(private val lottieAnimationView: LottieAnimationView, private val backgroundNoLottieAnimation: TextView) : IButtonAnimation {

    private var showLottieAnimation = false

    override fun start() {
        if (showLottieAnimation) {
            hideShowLottie(View.VISIBLE)
            hideShowBackgroundNoLottieAnimation(View.GONE)
            lottieStartAnimation()
        }
    }

    override fun pause() {
        if (showLottieAnimation) {
            lottiePauseAnimation()
        }
    }

    override fun hideShow() {
        when(lottieVisibility()) {
            true -> {
                showLottieAnimation = false
                hideShowLottie(View.INVISIBLE)
                hideShowBackgroundNoLottieAnimation(View.VISIBLE)
                lottiePauseAnimation()
            }
            false-> {
                showLottieAnimation = true
                hideShowLottie(View.VISIBLE)
                hideShowBackgroundNoLottieAnimation(View.INVISIBLE)
            }
        }
    }

    private fun hideShowLottie(visibility: Int) {
        lottieAnimationView.visibility = visibility
    }

    private fun hideShowBackgroundNoLottieAnimation(visibility: Int) {
        backgroundNoLottieAnimation.visibility = visibility
    }

    private fun lottieVisibility(): Boolean {
        return lottieAnimationView.visibility == View.VISIBLE
    }

    private fun lottieStartAnimation() {
        lottieAnimationView.playAnimation()
    }

    private fun lottiePauseAnimation() {
        lottieAnimationView.pauseAnimation()
    }
}