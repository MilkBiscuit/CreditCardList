package com.cheng.hellodemo.ui.infinitelist

import com.cheng.hellodemo.domain.model.CreditCardData

interface InfiniteListScreenState {
    data object Loading: InfiniteListScreenState
    data class Error(
        val message: String
    ): InfiniteListScreenState
    data class Presenting(
        val dataList: List<CreditCardData>,
        val isLoading: Boolean,
        val isError: Boolean,
        val errorMessage: String
    ): InfiniteListScreenState
}
