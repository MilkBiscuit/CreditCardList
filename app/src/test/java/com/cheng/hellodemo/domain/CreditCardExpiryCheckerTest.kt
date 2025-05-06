package com.cheng.hellodemo.domain

import org.junit.Assert
import org.junit.Test


class CreditCardExpiryCheckerTest {

    @Test
    fun `expiresInThreeYears - Given an invalid string input - returns true`() {
        val validDate = "AAAA-05-03"
        val expiresInThreeYears = CreditCardExpiryChecker.expiresInThreeYears(validDate)

        Assert.assertTrue(expiresInThreeYears)
    }

    @Test
    fun `expiresInThreeYears - Given a date more than 3 years away - returns false`() {
        val validDate = "2030-05-03"
        val expiryWarning = CreditCardExpiryChecker.expiresInThreeYears(validDate)

        Assert.assertEquals(false, expiryWarning)
    }

    @Test
    fun `expiresInThreeYears - Given a date in the past - returns true`() {
        val expiringDate = "2024-11-15"
        val expires = CreditCardExpiryChecker.expiresInThreeYears(expiringDate)

        Assert.assertTrue(expires)
    }

    @Test
    fun `expiresInThreeYears - Given a date in the next year - returns true`() {
        val expiringDate = "2026-11-15"
        val expiresInThreeYears = CreditCardExpiryChecker.expiresInThreeYears(expiringDate)

        Assert.assertTrue(expiresInThreeYears)
    }

    @Test
    fun `expiresInThreeYears - Given a date 3 years later - returns false`() {
        // Note: unstable test, it relies on the date of local machine
        val expiringDate = "2028-05-06"
        val expiresInThreeYears = CreditCardExpiryChecker.expiresInThreeYears(expiringDate)

        Assert.assertFalse(expiresInThreeYears)
    }
}
