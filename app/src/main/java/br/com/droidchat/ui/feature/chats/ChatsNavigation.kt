package br.com.droidchat.ui.feature.chats

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import br.com.droidchat.navigation.Route

fun NavController.navigateToChats(
    navOptions: NavOptions? = null,
) {
    this.navigate(Route.ChatsRoute, navOptions)
}