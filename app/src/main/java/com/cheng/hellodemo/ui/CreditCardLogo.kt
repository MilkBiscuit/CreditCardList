package com.cheng.hellodemo.ui

import com.cheng.hellodemo.R
import com.cheng.hellodemo.domain.model.CreditCardBrand

object CreditCardLogo {

    fun getCreditCardLogoResId(creditCardBrand: CreditCardBrand): Int? {
        return when (creditCardBrand) {
            CreditCardBrand.Visa -> R.drawable.visa
            CreditCardBrand.MasterCard -> R.drawable.mastercard
            CreditCardBrand.AmericanEx -> R.drawable.americanexpress
            else -> null
        }
    }

}
