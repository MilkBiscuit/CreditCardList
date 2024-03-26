package com.cheng.hellodemo.ui.infinitelist

import com.cheng.hellodemo.domain.model.CreditCardData

interface InfiniteListScreenState {
    data object Loading: InfiniteListScreenState
    data class Presenting(
        val dataList: List<CreditCardData>,
    ): InfiniteListScreenState
}
