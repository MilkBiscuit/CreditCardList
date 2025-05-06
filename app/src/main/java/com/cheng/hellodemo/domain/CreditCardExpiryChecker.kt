package com.cheng.hellodemo.domain

import java.time.LocalDate
import java.time.format.DateTimeParseException

object CreditCardExpiryChecker {

    fun expiresInThreeYears(inputDate: String): Boolean {
        val parsedInputDate: LocalDate = try {
            LocalDate.parse(inputDate)
        } catch (e: DateTimeParseException) {
            println("invalid input: $inputDate, exception: ${e.message}")

            return true
        }
        val cutOffDate = LocalDate.now().plusYears(3)

        return cutOffDate.isAfter(parsedInputDate)
    }

}
