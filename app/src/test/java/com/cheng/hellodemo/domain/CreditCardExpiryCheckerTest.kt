package domain

import com.cheng.hellodemo.domain.CreditCardExpiryChecker
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


class CreditCardExpiryCheckerTest {

    @Test
    fun `Given a date more than 3 years away Then expireInThreeYears returns false `() {
        val validDate = "2030-05-03"
        val expiryWarning = CreditCardExpiryChecker.expireInThreeYears(validDate)

        Assert.assertEquals(false, expiryWarning)
    }

    @Test
    fun `Given a date in the past Then expireInThreeYears returns true `() {
        val expiringDate = "2026-11-15"
        val expiryWarning = CreditCardExpiryChecker.expireInThreeYears(expiringDate)

        Assert.assertEquals(true, expiryWarning)
    }
}