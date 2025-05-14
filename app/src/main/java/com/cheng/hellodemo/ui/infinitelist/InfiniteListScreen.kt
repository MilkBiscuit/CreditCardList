package com.cheng.hellodemo.ui.infinitelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cheng.hellodemo.domain.CreditCardExpiryChecker
import com.cheng.hellodemo.domain.model.CreditCardBrand
import com.cheng.hellodemo.domain.model.CreditCardData
import com.cheng.hellodemo.ui.common.widget.CircularProgressIndicator
import com.cheng.hellodemo.ui.getResourceId
import com.cheng.hellodemo.ui.theme.HelloDemoTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@Composable
fun InfiniteListScreen(
    viewModel: InfiniteListScreenVM = hiltViewModel(),
) {
    val screenState by viewModel.stateFlow.collectAsState()

    InfiniteListView(
        screenState = screenState,
        loadMore = viewModel::loadMore,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InfiniteListView(
    screenState: InfiniteListScreenState,
    loadMore: () -> Unit,
) {
    Scaffold(
        topBar = @Composable {
            TopAppBar(
                title = { Text("Infinite List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            when (screenState) {
                is InfiniteListScreenState.Loading -> LoadingView()
                is InfiniteListScreenState.Presenting -> PresentingView(
                    screenState = screenState,
                    loadMore = loadMore,
                )
                is InfiniteListScreenState.Error -> ErrorView(
                    message = screenState
                )
            }
        }
    }
}

@Composable
private fun BoxScope.LoadingView() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(150.dp)
            .align(Alignment.Center)
    )
}

@Composable
private fun BoxScope.PresentingView(
    screenState: InfiniteListScreenState.Presenting,
    loadMore: () -> Unit,
) {
    CreditCardListView(
        isLoading = screenState.isLoading,
        creditCardList = screenState.dataList,
        loadMore = loadMore,
    )
}

@Composable
private fun BoxScope.ErrorView(message: InfiniteListScreenState.Error) {
    Text(
        text=message.message,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .align(Alignment.Center),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun CreditCardListView(
    isLoading: Boolean,
    creditCardList: List<CreditCardData>,
    loadMore: () -> Unit,
) {
    val listState = rememberLazyListState().also {
        it.ScrollEndCallback(callback = loadMore)
    }
    LazyColumn(state = listState) {
        items(creditCardList) { creditCardData ->
            ListItem(
                headlineContent = { Text(creditCardData.creditCardNumber) },
                supportingContent = {
                    Column {
                        Text(
                            text = creditCardData.creditCardExpiryDate,
                            color = if (CreditCardExpiryChecker.expiresInThreeYears(creditCardData.creditCardExpiryDate)) Color.Red else Color.Black,
                        )
                        val creditCardBrand = CreditCardBrand.fromString(creditCardData.creditCardType)
                        val imageResId = creditCardBrand.getResourceId()
                        if (imageResId != null) {
                            Image(
                                painter = painterResource(imageResId),
                                contentDescription = creditCardData.creditCardType,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(20.dp)
                            )
                        } else {
                            Text(creditCardData.creditCardType)
                        }
                    }
                },
                leadingContent = {
                    Text(
                        text = creditCardData.id.toString(),
                        modifier = Modifier.width(40.dp)
                    )
                }
            )
        }
        if (isLoading) {
            item {
                Box(
                    Modifier.fillParentMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(Modifier.size(50.dp))
                }
            }
        }
    }
}


@Composable
inline fun LazyListState.ScrollEndCallback(crossinline callback: () -> Unit) {
    LaunchedEffect(key1 = this) {
        snapshotFlow { layoutInfo }
            .filter { it.totalItemsCount > 0 }
            .map { it.totalItemsCount == (it.visibleItemsInfo.lastOrNull()?.index ?: -1).inc() }
            .distinctUntilChanged()
            .filter { it }
            .onEach { callback() }
            .collect()
    }
}


////////////////////////////////////// Preview //////////////////////////////////////
@Preview(showBackground = true, heightDp = 640)
@Composable
private fun PreviewLoading() {
    HelloDemoTheme {
        InfiniteListView(
            screenState = InfiniteListScreenState.Loading,
            loadMore = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCreditCardList() {
    HelloDemoTheme {
        InfiniteListView(
            screenState = InfiniteListScreenState.Presenting(
                dataList = listOf(
                    CreditCardData(
                        creditCardNumber = "1212-1221-1121-1234",
                        creditCardExpiryDate = "2028-05-02",
                        creditCardType = "discover",
                    ),
                    CreditCardData(
                        creditCardNumber = "1211-1221-1234-2201",
                        creditCardExpiryDate = "2025-03-26",
                        creditCardType = "visa",
                    ),
                    CreditCardData(
                        creditCardNumber = "1234-2121-1221-1211",
                        creditCardExpiryDate = "2028-05-01",
                        creditCardType = "diners_club",
                    ),
                    CreditCardData(
                        creditCardNumber = "1234-2121-1221-1211",
                        creditCardExpiryDate = "2026-03-26",
                        creditCardType = "mastercard",
                    ),
                ),
                isLoading = true
            ),
            loadMore = {},
        )
    }
}

@Preview(showBackground = true, heightDp = 640)
@Composable
private fun PreviewError() {
    HelloDemoTheme {
        InfiniteListView(
            screenState = InfiniteListScreenState.Error("ERROR: Credit Cards did not Load"),
            loadMore = {}
        )
    }
}
