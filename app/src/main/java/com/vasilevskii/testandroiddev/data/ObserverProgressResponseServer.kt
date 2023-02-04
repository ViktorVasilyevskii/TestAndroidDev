package com.vasilevskii.testandroiddev.data

import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.vasilevskii.testandroiddev.R
import com.vasilevskii.testandroiddev.`interface`.IObserverProgressServer
import com.vasilevskii.testandroiddev.logic.ObservableProgress

data class ObserverProgressResponseServer(val progressBar: ProgressBar, val progressBarValue: TextView, val observer: ObservableProgress): IObserverProgressServer{
    override fun onChange(newValue: Int) {
        progressBar.progress = newValue
        progressBarValue.text = String.format(progressBarValue.context.resources.getString(R.string.value_loading), newValue)
        if (newValue == 100) {
            observer.remove()
        }
    }

}
