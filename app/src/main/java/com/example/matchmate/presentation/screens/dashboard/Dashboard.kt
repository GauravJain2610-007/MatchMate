package com.example.matchmate.presentation.screens.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.matchmate.data.network.models.UserDetails
import com.example.matchmate.presentation.screens.dashboard.molecules.UserProfileCard


@Composable
fun Dashboard(viewModel: MatchMateViewModel) {

    val users = remember { viewModel.userPagingFlow }.collectAsLazyPagingItems()

    plotDashboardList(users, onAccept = { user ->
        viewModel.updateUserAction(true, user)
    }, onReject = { user ->
        viewModel.updateUserAction(false, user)

    })

}


@Composable
fun plotDashboardList(users: LazyPagingItems<UserDetails>, onAccept:(UserDetails) -> Unit = {}, onReject:(UserDetails) -> Unit = {}) {

    val context = LocalContext.current
    LaunchedEffect(key1 = users.loadState) {
        if (users.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (users.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (users.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)) {
                items(users.itemCount, key = { index -> users[index]?.id?.value ?: index }) { index ->
                    users[index]?.let {
                        UserProfileCard(it, onAccept = onAccept, onDecline = onReject)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                when (val state = users.loadState.append) {
                    is LoadState.Loading -> {
                        item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                    }

                    is LoadState.Error -> {
                        item {
                            Text(
                                text = "Error: ${state.error.localizedMessage}",
                                color = Color.Red,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }


}