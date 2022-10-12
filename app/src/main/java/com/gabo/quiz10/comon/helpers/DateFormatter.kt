package com.gabo.quiz10.comon.helpers

import java.text.SimpleDateFormat
import java.util.*

fun getData(milliSeconds: Long, dateFormat: String?): String?{
    val formatter = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}