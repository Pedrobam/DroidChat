package br.com.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.droidchat.ui.feature.chats.navigateToChats

@Composable
fun rememberDroidChatNavigationState(
    navHostController: NavHostController = rememberNavController()
): DroidChatNavigationState {
    return remember(navHostController) {
        DroidChatNavigationState(navHostController)
    }
}

@Stable
class DroidChatNavigationState(
    val navHostController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable
        get() = navHostController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable
        get() = TopLevelDestination.entries.firstOrNull { topLevelDestination ->
            currentDestination?.hasRoute(topLevelDestination.route) == true
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navHostController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.CHATS -> navHostController.navigateToChats(topLevelNavOptions)
            TopLevelDestination.PLUS_BUTTON -> {

            }

            TopLevelDestination.PROFILE -> {

            }
        }

    }
}
