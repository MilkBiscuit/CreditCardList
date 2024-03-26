package com.cheng.hellodemo.ui.infinitelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cheng.hellodemo.domain.model.CreditCardData
import com.cheng.hellodemo.ui.common.widget.CircularProgressIndicator
import com.cheng.hellodemo.ui.theme.HelloDemoTheme


@Composable
fun InfiniteListScreen(
    viewModel: InfiniteListScreenVM = hiltViewModel(),
) {
    val screenState by viewModel.stateFlow.collectAsState()

    InfiniteListView(screenState = screenState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InfiniteListView(
    screenState: InfiniteListScreenState,
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
            .padding(it)) {
            when (screenState) {
                is InfiniteListScreenState.Loading -> LoadingView()
                is InfiniteListScreenState.Presenting -> PresentingView(screenState)
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
) {
    LazyColumn {
        items(screenState.dataList) {creditCardData ->
            ListItem(
                headlineContent = { Text(creditCardData.creditCardNumber) },
                supportingContent = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(creditCardData.creditCardExpiryDate)
                        Spacer(Modifier.size(20.dp))
                        Text(creditCardData.creditCardType)
                    }
                }
            )
        }
    }
}


////////////////////////////////////// Preview //////////////////////////////////////
@Preview(showBackground = true, heightDp = 640)
@Composable
private fun PreviewLoading() {
    HelloDemoTheme {
        InfiniteListView(
            screenState = InfiniteListScreenState.Loading,
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
                        creditCardExpiryDate = "2028-03-25",
                        creditCardType = "discover",
                    ),
                    CreditCardData(
                        creditCardNumber = "1211-1221-1234-2201",
                        creditCardExpiryDate = "2025-03-26",
                        creditCardType = "visa",
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
                ),
            ),
        )
    }
}
