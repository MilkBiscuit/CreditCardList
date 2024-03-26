package com.cheng.hellodemo.usecase

import com.cheng.hellodemo.domain.model.CreditCardData
import javax.inject.Inject

class GetCreditCardListUC @Inject constructor(
) {

    fun invoke(): List<CreditCardData> {
        return fakeCreditCardList
    }

}

private val fakeCreditCardList = listOf(
    CreditCardData(
        creditCardNumber = "1212-1221-1121-1234",
        creditCardExpiryDate = "2025-03-26",
        creditCardType = "discover",
    ),
    CreditCardData(
        creditCardNumber = "1234-2121-1221-1211",
        creditCardExpiryDate = "2028-03-25",
        creditCardType = "discover",
    ),
    CreditCardData(
        creditCardNumber = "1212-1221-1121-1234",
        creditCardExpiryDate = "2027-03-26",
        creditCardType = "visa",
    ),
    CreditCardData(
        creditCardNumber = "1212-1221-1121-1234",
        creditCardExpiryDate = "2025-03-26",
        creditCardType = "switch",
    ),
    CreditCardData(
        creditCardNumber = "1212-1221-1121-1234",
        creditCardExpiryDate = "2028-03-25",
        creditCardType = "discover",
    ),
    CreditCardData(
        creditCardNumber = "1211-1221-1234-2201",
        creditCardExpiryDate = "2025-03-26",
        creditCardType = "forbrugsforeningen",
    ),
    CreditCardData(
        creditCardNumber = "1234-2121-1221-1211",
        creditCardExpiryDate = "2028-03-25",
        creditCardType = "diners_club",
    ),
    CreditCardData(
        creditCardNumber = "1234-2121-1221-1211",
        creditCardExpiryDate = "2026-03-26",
        creditCardType = "mastercard",
    ),
    CreditCardData(
        creditCardNumber = "1211-1221-1234-2201",
        creditCardExpiryDate = "2027-03-26",
        creditCardType = "laser",
    ),
    CreditCardData(
        creditCardNumber = "1211-1221-1234-2201",
        creditCardExpiryDate = "2028-03-25",
        creditCardType = "mastercard",
    ),
    CreditCardData(
        creditCardNumber = "1228-1221-1221-1431",
        creditCardExpiryDate = "2025-03-26",
        creditCardType = "maestro",
    ),
    CreditCardData(
        creditCardNumber = "1211-1221-1234-2201",
        creditCardExpiryDate = "2027-03-26",
        creditCardType = "discover",
    ),
    CreditCardData(
        creditCardNumber = "1211-1221-1234-2201",
        creditCardExpiryDate = "2026-03-26",
        creditCardType = "solo",
    ),
    CreditCardData(
        creditCardNumber = "1234-2121-1221-1211",
        creditCardExpiryDate = "2026-03-26",
        creditCardType = "visa",
    ),
    CreditCardData(
        creditCardNumber = "1228-1221-1221-1431",
        creditCardExpiryDate = "2026-03-26",
        creditCardType = "diners_club",
    ),
    CreditCardData(
        creditCardNumber = "1212-1221-1121-1234",
        creditCardExpiryDate = "2027-03-26",
        creditCardType = "mastercard",
    ),
    CreditCardData(
        creditCardNumber = "1212-1221-1121-1234",
        creditCardExpiryDate = "2027-03-26",
        creditCardType = "visa",
    ),
    CreditCardData(
        creditCardNumber = "1234-2121-1221-1211",
        creditCardExpiryDate = "2025-03-26",
        creditCardType = "dankort",
    ),
    CreditCardData(
        creditCardNumber = "1234-2121-1221-1211",
        creditCardExpiryDate = "2026-03-26",
        creditCardType = "discover",
    ),
    CreditCardData(
        creditCardNumber = "1211-1221-1234-2201",
        creditCardExpiryDate = "2027-03-26",
        creditCardType = "diners_club",
    )
)
