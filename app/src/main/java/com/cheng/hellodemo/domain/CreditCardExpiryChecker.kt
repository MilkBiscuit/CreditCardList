package com.cheng.hellodemo.domain

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object CreditCardExpiryChecker {

    @RequiresApi(Build.VERSION_CODES.O)
    fun expireInThreeYears(inputDate: String):Boolean {
        val currentDateString = LocalDate.now().toString()
        val currentYear = currentDateString.substring(0, 4).toInt()
        val threeYearLater = currentYear + 3
        val inputDateYear = inputDate.substring(0, 4).toInt()
        if (threeYearLater > inputDateYear) {

            return true
        } else if (threeYearLater == inputDateYear) {
            val currentMonth = currentDateString.substring(5, 7).toInt()
            val inputDateMonth = inputDate.substring(5, 7).toInt()
            if (currentMonth > inputDateMonth) {

                return true
            } else if (currentMonth == inputDateMonth) {
                val currentDayOfMonth = currentDateString.substring(8, 10).toInt()
                val inputDateDayOfMonth = inputDate.substring(8, 10).toInt()
                if (currentDayOfMonth > inputDateDayOfMonth) {

                    return true
                }
            }
        }

        return false
    }

}
