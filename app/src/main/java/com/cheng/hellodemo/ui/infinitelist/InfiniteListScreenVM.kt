package com.cheng.hellodemo.ui.infinitelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheng.hellodemo.usecase.GetCreditCardListUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
            delay(2000L)

            _stateFlow.value = InfiniteListScreenState.Presenting(
                dataList = getCreditCardListUC.invoke(),
            )
        }
    }

}
