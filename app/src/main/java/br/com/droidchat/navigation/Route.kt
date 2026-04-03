package br.com.droidchat.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    object SplashRoute

    @Serializable
    object SignInRoute

    @Serializable
    object SignUpRoute

    @Serializable
    object ChatsRoute

    @Serializable
    object UsersRoute
    @Serializable
    object ProfileRoute
}