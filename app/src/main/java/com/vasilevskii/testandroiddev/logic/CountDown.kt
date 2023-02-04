package com.vasilevskii.testandroiddev.logic

import android.os.CountDownTimer
import android.util.Log
import com.vasilevskii.testandroiddev.data.TimeLeftMills
import com.vasilevskii.testandroiddev.`interface`.ICountDown

abstract class CountDown(private val valueMillis: Long, var countDownInterval: Long) : ICountDown {

    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftMills = TimeLeftMills(0L)
    private var isTimerPaused: Boolean = true


    abstract fun onTimerTick(millisUntilFinished: Long)
    abstract fun onTimerFinish()

    init {
        timeLeftMills.time = valueMillis
    }


    override fun initCountDown() {
            countDownTimer = object : CountDownTimer(timeLeftMills.time, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeftMills.time = millisUntilFinished
                    onTimerTick(timeLeftMills.time)
                }

                override fun onFinish() {
                    onTimerFinish()
                    reset()
                }
            }
    }

    override fun start() {
        if (isTimerPaused) {
            countDownTimer.start()
            isTimerPaused = false
        }
    }

    override fun resume() {
        if (timeLeftMills.time in 1 until valueMillis) {
            initCountDown()
            start()
        }
    }


    override fun reset() {
        countDownTimer.cancel()
        timeLeftMills.time = valueMillis
        isTimerPaused = true

    }

    override fun pause() {
        if (!isTimerPaused) {
            countDownTimer.cancel()
        }
        isTimerPaused = true
    }
}