package com.imastudio.implicitapp.helper

import java.text.SimpleDateFormat
import java.util.*

class Helper {

    companion object {
        fun currentDate(): String {
            val dateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
            val date = Date()
            return dateFormat.format(date)
        }

        const val CAMERA = 1
        const val GALERY = 2
        const val PHONE = 1


    }

}