package com.vasilevskii.testandroiddev.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilevskii.testandroiddev.Const
import com.vasilevskii.testandroiddev.R
import com.vasilevskii.testandroiddev.adapter.AdapterRating
import com.vasilevskii.testandroiddev.data.ItemRating
import com.vasilevskii.testandroiddev.data.ObserverProgressResponseServer
import com.vasilevskii.testandroiddev.databinding.FragmentTwoScreenBinding
import com.vasilevskii.testandroiddev.enum.ActionProgress
import com.vasilevskii.testandroiddev.logic.CountDown
import com.vasilevskii.testandroiddev.logic.RandomValue
import com.vasilevskii.testandroiddev.viewmodel.MainViewModel

class TwoScreenFragment : Fragment() {

    private lateinit var twoScreenBinding: FragmentTwoScreenBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var randomOneCountDown: CountDown
    private lateinit var randomTwoCountDown: CountDown
    private lateinit var timerCountDown: CountDown
    private lateinit var adapterRating: AdapterRating

    private var updateTimer: Int = 0

    companion object{
        var progressRandomOne: Int = 0
        var progressRandomTwo: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getRatings()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        twoScreenBinding = FragmentTwoScreenBinding.inflate(layoutInflater)
        return twoScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonRandomize()
        initTimerCountDown()
        responseServerRatings()
        initRecyclerViewRating()
        initButtonBack()
    }


    private fun initButtonBack() {
        twoScreenBinding.back.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.oneScreenFragment)
        }
    }

    private fun initRecyclerViewRating() {
        adapterRating = AdapterRating()
        twoScreenBinding.recyclerviewRating.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false)
            setHasFixedSize(true)
            adapter = adapterRating
        }
    }

    private fun initButtonRandomize() {
        twoScreenBinding.buttonRandomize.setOnClickListener {
            initProgressRandom()
        }
    }

    private fun initProgressRandom() {
        if (this::randomOneCountDown.isInitialized) actionRandomProgress(randomOneCountDown, ActionProgress.RESET)
        if (this::randomTwoCountDown.isInitialized) actionRandomProgress(randomTwoCountDown, ActionProgress.RESET)

        initRandomOneProgress()
        initRandomTwoProgress()
    }

    private fun actionRandomProgress(randomCountDown: CountDown, actionProgress: ActionProgress) {
            when(actionProgress) {
                ActionProgress.START -> randomCountDown.start()
                ActionProgress.PAUSE -> randomCountDown.pause()
                ActionProgress.RESET -> randomCountDown.reset()
                ActionProgress.RESUME -> randomCountDown.resume()
            }
    }

    private fun responseServerRatings() {
        viewModel.apply {
            twoScreenBinding.apply {
                observableProgress.observe(ObserverProgressResponseServer(progressBarLoadServer, textValueLoadServer, observableProgress))
            }
            responseServer.observe(viewLifecycleOwner){responce ->
                adapterRating.submitList(responce)
            }

        }

    }

    private fun initRandomOneProgress() {
        val timerDuration = RandomValue().randomValue
        randomOneCountDown = object :  CountDown(timerDuration, Const.TIMER_INTERVAL) {
            override fun onTimerTick(millisUntilFinished: Long) {
                progressRandomOne = Const.ONE_HUNDRED_PERCENT - ((millisUntilFinished * Const.ONE_HUNDRED_PERCENT) / timerDuration).toInt()
                twoScreenBinding.apply {
                    progressBarRandomOne.progress = progressRandomOne
                    textValueProgressRandomOne.text = String.format(resources.getString(R.string.value_loading), progressRandomOne)
                }
            }

            override fun onTimerFinish() {
                twoScreenBinding.apply {
                    progressBarRandomOne.progress = Const.ONE_HUNDRED_PERCENT
                    textValueProgressRandomOne.text = String.format(resources.getString(R.string.value_loading), Const.ONE_HUNDRED_PERCENT)
                }
            }
        }
        randomOneCountDown.initCountDown()
        randomOneCountDown.start()
    }

    private fun initRandomTwoProgress() {
        val timerDuration = RandomValue().randomValue
        randomTwoCountDown = object : CountDown(timerDuration, Const.TIMER_INTERVAL) {
            override fun onTimerTick(millisUntilFinished: Long) {
                progressRandomTwo = Const.ONE_HUNDRED_PERCENT - ((millisUntilFinished * Const.ONE_HUNDRED_PERCENT) / timerDuration).toInt()
                twoScreenBinding.apply {
                    progressBarRandomTwo.progress = progressRandomTwo
                    textValueProgressRandomTwo.text = String.format(resources.getString(R.string.value_loading), progressRandomTwo)
                }
            }

            override fun onTimerFinish() {
                twoScreenBinding.apply {
                    progressBarRandomTwo.progress = Const.ONE_HUNDRED_PERCENT
                    textValueProgressRandomTwo.text = String.format(resources.getString(R.string.value_loading), Const.ONE_HUNDRED_PERCENT)
                }
            }

        }
        randomTwoCountDown.initCountDown()
        randomTwoCountDown.start()
    }

    private fun initTimerCountDown() {
        timerCountDown = object : CountDown(Const.ONE_HOURS, Const.TIMER_INTERVAL) {
            override fun onTimerTick(millisUntilFinished: Long) {
                updateTimer = (millisUntilFinished / 1000).toInt()
                twoScreenBinding.apply {
                    if (hours.text != String.format("%02d", 0)) hours.text = String.format("%02d", 0)
                    minutes.text = String.format("%02d", getMinutes(updateTimer))
                    seconds.text = String.format("%02d", getSeconds(updateTimer))
                }
            }

            override fun onTimerFinish() {
                twoScreenBinding.apply {
                    hours.text = String.format("%02d", 0)
                    minutes.text = String.format("%02d", 0)
                    seconds.text = String.format("%02d", 0)
                }
            }
        }
        timerCountDown.initCountDown()
        timerCountDown.start()
    }

    private fun getMinutes(seconds: Int): Int{
        return seconds / 60
    }

    private fun getSeconds(seconds: Int): Int {
        return seconds % 60
    }


    override fun onPause() {
        super.onPause()
        if (this::randomOneCountDown.isInitialized) actionRandomProgress(randomOneCountDown, ActionProgress.PAUSE)
        if (this::randomTwoCountDown.isInitialized) actionRandomProgress(randomTwoCountDown, ActionProgress.PAUSE)
        if (this::timerCountDown.isInitialized) actionRandomProgress(timerCountDown, ActionProgress.PAUSE)
    }

    override fun onResume() {
        super.onResume()
        if (this::randomOneCountDown.isInitialized) actionRandomProgress(randomOneCountDown, ActionProgress.RESUME)
        if (this::randomTwoCountDown.isInitialized) actionRandomProgress(randomTwoCountDown, ActionProgress.RESUME)
        if (this::timerCountDown.isInitialized) actionRandomProgress(timerCountDown, ActionProgress.RESUME)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::randomOneCountDown.isInitialized) actionRandomProgress(randomOneCountDown, ActionProgress.RESET)
        if (this::randomTwoCountDown.isInitialized) actionRandomProgress(randomTwoCountDown, ActionProgress.RESET)
        if (this::timerCountDown.isInitialized) actionRandomProgress(timerCountDown, ActionProgress.RESET)
    }






}