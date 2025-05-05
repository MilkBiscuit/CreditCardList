package com.cheng.hellodemo.domain

import java.time.LocalDate

object CreditCardExpiryChecker {

    fun expiresInThreeYears(inputDate: String): Boolean {
        val parsedInputDate = LocalDate.parse(inputDate)
        val cutOffDate = LocalDate.now().plusYears(3)

        return cutOffDate.isAfter(parsedInputDate)
    }

}
