package com.vasilevskii.testandroiddev.logic

import android.util.Log
import com.vasilevskii.testandroiddev.`interface`.IObserverProgressServer


class ObservableProgress {

    private val observers = mutableListOf<IObserverProgressServer>()

    var value: Int = 0
        set(value) {
            field = value
            notifyObservers()
        }

    fun observe(observer: IObserverProgressServer) {
        observers.add(observer)
    }

    fun remove() {
        observers.forEach { observer ->
            observers.remove(observer)
        }
    }

    private fun notifyObservers() {
        observers.forEach { observer ->
            observer.onChange(value)
        }

    }
}