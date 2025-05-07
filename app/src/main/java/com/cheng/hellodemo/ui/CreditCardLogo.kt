package com.cheng.hellodemo.ui

import com.cheng.hellodemo.R
import com.cheng.hellodemo.domain.model.CreditCardBrand


fun CreditCardBrand.getResourceId(): Int? {
    return when (this) {
        CreditCardBrand.Visa -> R.drawable.visa
        CreditCardBrand.MasterCard -> R.drawable.mastercard
        CreditCardBrand.AmericanEx -> R.drawable.americanexpress
        else -> null
    }
}
