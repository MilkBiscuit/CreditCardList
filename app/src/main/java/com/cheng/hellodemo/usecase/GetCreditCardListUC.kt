package com.cheng.hellodemo.usecase

import android.util.Log
import com.cheng.hellodemo.adapter.JsonHelper
import com.cheng.hellodemo.domain.adapterinterface.IRestApiRemote
import com.cheng.hellodemo.domain.model.CreditCardData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCreditCardListUC @Inject constructor(
    private val restApiRemote: IRestApiRemote,
) {

    suspend fun invoke(): List<CreditCardData> = withContext(Dispatchers.IO) {
        Log.i("trpb67", "GetCreditCardListUC is invoked!")
        val result = restApiRemote.get(url = "https://random-data-api.com/api/v2/credit_cards?size=20")
        val jsonString = result.getOrNull() ?: return@withContext emptyList()

        JsonHelper.fromJsonString<List<CreditCardData>>(jsonString) ?: emptyList()
    }

}
