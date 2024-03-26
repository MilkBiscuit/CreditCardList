package com.cheng.hellodemo.domain.model

data class CreditCardData(
    val id: Int = 0,
    val uuid: String = "",
    val creditCardNumber: String,
    val creditCardExpiryDate: String,
    val creditCardType: String,
)
