package com.cheng.hellodemo.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditCardData(
    val id: Int = 0,
    val uid: String = "",
    @SerialName("credit_card_number")
    val creditCardNumber: String,
    @SerialName("credit_card_expiry_date")
    val creditCardExpiryDate: String,
    @SerialName("credit_card_type")
    val creditCardType: String,
)
