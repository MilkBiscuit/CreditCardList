package com.cheng.hellodemo.ui.infinitelist

import android.util.Log
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
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<InfiniteListScreenState> =
        MutableStateFlow(InfiniteListScreenState.Loading)
    val stateFlow: StateFlow<InfiniteListScreenState> = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getCreditCardListUC.invoke().onSuccess { creditCardList ->
                _stateFlow.value = InfiniteListScreenState.Presenting(
                    dataList = creditCardList,
                    isLoading = false,
                )
            }.onFailure { err ->
                val errorMessage = err.toString()
                _stateFlow.value = InfiniteListScreenState.Error(
                    message = errorMessage
                )
            }
        }
    }

    fun loadMore() = viewModelScope.launch {
        val currentState = _stateFlow.value as InfiniteListScreenState.Presenting
        if (currentState.isLoading) return@launch

        val currentList = currentState.dataList
        _stateFlow.value = currentState.copy(isLoading = true)
        Log.v("trpb67", "currentList is ${currentList.joinToString { it.creditCardType }}")

        getCreditCardListUC.invoke().onSuccess { newlyFetchedList ->
            Log.v("trpb67","newlyFetchedList is ${newlyFetchedList.joinToString { it.creditCardType }}")

            val newList = buildList {
                addAll(currentList)
                addAll(newlyFetchedList)
            }
            val emitResult = _stateFlow.tryEmit(
                InfiniteListScreenState.Presenting(
                    dataList = newList,
                    isLoading = false,
                )
            )
            Log.v("trpb67", "emitResult is $emitResult")
        }.onFailure { err ->
            val emitResult = _stateFlow.tryEmit(
                InfiniteListScreenState.Error(
                    message = err.toString()
                )
            )
            Log.v("trpb67", "emitResult is $emitResult")
        }
    }

}
