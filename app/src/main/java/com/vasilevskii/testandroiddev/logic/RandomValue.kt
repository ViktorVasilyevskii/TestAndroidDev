package com.vasilevskii.testandroiddev.logic

import com.vasilevskii.testandroiddev.Const
import kotlin.random.Random

class RandomValue {

    val randomValue: Long = Random.nextLong (Const.MIN_VALUE_RANDOM, Const.MAX_VALUE_RANDOM)
}