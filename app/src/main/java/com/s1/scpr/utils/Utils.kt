package com.s1.scpr.utils

import android.text.format.DateUtils

object Utils {
    @JvmStatic
    fun secondsToHourMinsAndSecondsStr(seconds: Double): String {
        return DateUtils.formatElapsedTime(seconds.toLong())
    }
}