package com.cheng.hellodemo.ui.infinitelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheng.hellodemo.usecase.GetCreditCardListUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfiniteListScreenVM @Inject constructor(
    private val getCreditCardListUC: GetCreditCardListUC,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<InfiniteListScreenState> =
        MutableStateFlow(InfiniteListScreenState.Loading)
    val stateFlow: StateFlow<InfiniteListScreenState> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val creditCardList = getCreditCardListUC.invoke()
            _stateFlow.value = InfiniteListScreenState.Presenting(
                dataList = creditCardList,
                isLoading = false,
            )
        }
    }

    fun loadMore() = viewModelScope.launch {
        val currentState = _stateFlow.value as InfiniteListScreenState.Presenting
        val currentList = currentState.dataList
        _stateFlow.value = currentState.copy(isLoading = true)

        val newlyFetchedList = getCreditCardListUC.invoke()
        val newList = buildList {
            addAll(currentList)
            addAll(newlyFetchedList)
        }
        _stateFlow.tryEmit(InfiniteListScreenState.Presenting(
            dataList = newList,
            isLoading = false,
        ))
    }

}
