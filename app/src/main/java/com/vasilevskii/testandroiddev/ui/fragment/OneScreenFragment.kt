package com.vasilevskii.testandroiddev.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vasilevskii.testandroiddev.Const
import com.vasilevskii.testandroiddev.R
import com.vasilevskii.testandroiddev.databinding.FragmentOneScreenBinding
import com.vasilevskii.testandroiddev.logic.CountDown
import com.vasilevskii.testandroiddev.logic.LottieAnimation
import com.vasilevskii.testandroiddev.ui.view.CustomAlertView
import com.vasilevskii.testandroiddev.viewmodel.MainViewModel

class OneScreenFragment : Fragment(), View.OnClickListener{

    private lateinit var oneScreenBinding: FragmentOneScreenBinding
    private lateinit var timerCountDown: CountDown
    private lateinit var lottieAnimation: LottieAnimation
    private var parent: ViewGroup? = null


    companion object {
        const val TIMER_DURATION = 14000L
        var progress: Int = 0
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        parent = container
        oneScreenBinding = FragmentOneScreenBinding.inflate(layoutInflater)
        return oneScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottieAnimation = LottieAnimation(oneScreenBinding.lottieDownloadOneScreen, oneScreenBinding.noLottieAnimation)
        initAnimation()
        initProgress()
        initButtonCustomAlert()
        initButtonGoSecondScreen()
    }

    private fun initProgress(){
        timerCountDown = object : CountDown(TIMER_DURATION, Const.TIMER_INTERVAL) {
            override fun onTimerTick(millisUntilFinished: Long) {
                progress = Const.ONE_HUNDRED_PERCENT - ((millisUntilFinished * Const.ONE_HUNDRED_PERCENT) / TIMER_DURATION).toInt()
                oneScreenBinding.apply {
                    progressBarOneScreen.progress = progress
                    textValueLoading.text = String.format(resources.getString(R.string.value_loading), progress)
                }

            }

            override fun onTimerFinish() {
                oneScreenBinding.apply {
                    progressBarOneScreen.progress = Const.ONE_HUNDRED_PERCENT
                    textValueLoading.text = String.format(resources.getString(R.string.value_loading), Const.ONE_HUNDRED_PERCENT)
                }
            }
        }
        timerCountDown.initCountDown()
        timerCountDown.start()
    }

    private fun initAnimation(){
        oneScreenBinding.apply {
            buttonStartAnimation.setOnClickListener(this@OneScreenFragment)
            buttonStopAnimation.setOnClickListener(this@OneScreenFragment)
            buttonHideShowAnimation.setOnClickListener(this@OneScreenFragment)
        }
    }

    private fun initButtonCustomAlert() {
        oneScreenBinding.buttonCustomAlert.setOnClickListener(this@OneScreenFragment)
    }

    private fun initButtonGoSecondScreen() {
        oneScreenBinding.buttonGoSecondScreen.setOnClickListener(this@OneScreenFragment)
    }

    override fun onPause() {
        super.onPause()
        if (this::timerCountDown.isInitialized) timerCountDown.pause()
        if (this::lottieAnimation.isInitialized) lottieAnimation.pause()
    }

    override fun onResume() {
        super.onResume()
        if (this::timerCountDown.isInitialized) timerCountDown.resume()
        if (this::lottieAnimation.isInitialized) lottieAnimation.start()

    }

    override fun onDestroy() {
        if (this::timerCountDown.isInitialized) timerCountDown.reset()
        super.onDestroy()
    }

    override fun onClick(view: View?) {
        oneScreenBinding.apply {
            when(view){
                buttonStartAnimation -> lottieAnimation.start()
                buttonStopAnimation -> lottieAnimation.pause()
                buttonHideShowAnimation -> lottieAnimation.hideShow()
                buttonGoSecondScreen -> showTwoScreenFragment(view)
                buttonCustomAlert -> showCustomAlertDialog(view)
            }
        }
    }

    private fun showTwoScreenFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.twoScreenFragment)
    }

    private fun showCustomAlertDialog(view: View) {
        val customAlertDialog = MaterialAlertDialogBuilder(view.context, R.style.MaterialAlertDialog_rounded)
        customAlertDialog.setView(CustomAlertView(view.context))
        customAlertDialog.setPositiveButton(R.string.text_button_custom_alert_dialog, DialogInterface.OnClickListener {dialog, _ ->
            dialog.dismiss()
        })
        customAlertDialog.show()
    }
}