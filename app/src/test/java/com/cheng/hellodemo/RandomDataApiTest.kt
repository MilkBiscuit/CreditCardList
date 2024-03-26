package com.cheng.hellodemo

import com.cheng.hellodemo.adapter.JsonHelper
import com.cheng.hellodemo.adapter.RestApiRemote
import com.cheng.hellodemo.domain.model.CreditCardData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RandomDataApiTest {

    private val restApiRemote = RestApiRemote()

    // This is actually integration test rather than unit test
    @Test
    fun testGetApiRequest() {
        runBlocking {
            val result = restApiRemote.get(
                url = "https://random-data-api.com/api/v2/credit_cards?size=10",
            )
            Assert.assertTrue(result.isSuccess)
            val jsonString = result.getOrThrow()
            val creditCardDataList = JsonHelper.fromJsonString<List<CreditCardData>>(jsonString)
            println(creditCardDataList)
            Assert.assertNotNull(creditCardDataList)
            Assert.assertEquals(10, creditCardDataList!!.size)
        }
    }

}
