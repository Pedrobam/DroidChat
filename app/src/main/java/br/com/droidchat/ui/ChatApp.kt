package br.com.droidchat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.droidchat.navigation.ChatNavHost
import br.com.droidchat.navigation.rememberDroidChatNavigationState
import br.com.droidchat.ui.components.BottomNavigationMenu
import br.com.droidchat.ui.theme.Grey1

@Composable
fun ChatApp() {
    val navigationState = rememberDroidChatNavigationState()

    Scaffold(
        bottomBar = {
            val topLevelDestinations = navigationState.topLevelDestinations.toTypedArray()
            if (topLevelDestinations.contains(navigationState.currentTopLevelDestination)) {
                BottomNavigationMenu(
                    navigationState = navigationState
                )
            }
        },
        containerColor = Grey1
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
                .fillMaxSize()
        ) {
            ChatNavHost(
                navigationState = navigationState
            )
        }
    }
}