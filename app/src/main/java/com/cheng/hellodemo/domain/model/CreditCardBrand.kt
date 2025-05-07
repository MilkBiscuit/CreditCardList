package com.cheng.hellodemo.domain.model

enum class CreditCardBrand {
    Visa,
    MasterCard,
    AmericanEx,
    Others;

    companion object {
        fun fromString(input: String): CreditCardBrand {
            return when (input) {
                "visa" -> CreditCardBrand.Visa
                "mastercard" -> CreditCardBrand.MasterCard
                "american_express" -> CreditCardBrand.AmericanEx
                else -> CreditCardBrand.Others
            }
        }
    }
}
