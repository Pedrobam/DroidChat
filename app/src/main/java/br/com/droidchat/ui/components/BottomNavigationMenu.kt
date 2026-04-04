package br.com.droidchat.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import br.com.droidchat.navigation.DroidChatNavigationState
import br.com.droidchat.navigation.TopLevelDestination
import br.com.droidchat.navigation.rememberDroidChatNavigationState
import br.com.droidchat.ui.theme.DroidChatTheme
import kotlin.reflect.KClass

@Composable
fun BottomNavigationMenu(
    navigationState: DroidChatNavigationState,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .padding(top = 1.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        navigationState.topLevelDestinations.forEach { topLevelDestination ->
            if (topLevelDestination == TopLevelDestination.PLUS_BUTTON) {
                FloatingActionButton(
                    onClick = {
                        navigationState.navigateToTopLevelDestination(topLevelDestination)
                    },
                    modifier = Modifier.padding(top = 6.dp),
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        pressedElevation = 0.dp,
                        focusedElevation = 0.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            } else {
                val selected = navigationState.currentDestination.isRouteInHierarchy(topLevelDestination.route)
                val label = topLevelDestination.titleRes?.let {
                    stringResource(id = it)
                }
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navigationState.navigateToTopLevelDestination(topLevelDestination)
                        }
                    },
                    icon = {
                        topLevelDestination.iconRes?.let {
                            Icon(
                                painter = painterResource(id = it),
                                contentDescription = label
                            )
                        }
                    },
                    label = {
                        label?.let {
                            Text(text = it)
                        }
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        selectedIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTextColor = MaterialTheme.colorScheme.onSurface,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}

@SuppressLint("RestrictedApi")
private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false

@Preview
@Composable
private fun BottomNavigationMenuPreview() {
    DroidChatTheme {
        BottomNavigationMenu(
            navigationState = rememberDroidChatNavigationState()
        )
    }
}