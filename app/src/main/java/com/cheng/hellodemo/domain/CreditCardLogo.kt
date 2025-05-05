package com.cheng.hellodemo.domain

import com.cheng.hellodemo.R

object CreditCardLogo {

    fun getCreditCardLogo(creditCardType: String): Int? {
        return when (creditCardType) {
            "visa" -> R.drawable.visa
            "mastercard" -> R.drawable.mastercard
            "american_express" -> R.drawable.americanexpress
            else -> null
        }
    }

//        if (creditCardType == "visa")
//           return R.drawable.visa
//        else if (creditCardType == "mastercard")
//           return R.drawable.mastercard
//        else if (creditCardType == "american_express")
//           return R.drawable.americanexpress
//        return null
//    }
    }









