package br.com.droidchat.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.droidchat.navigation.extension.slidInTo
import br.com.droidchat.navigation.extension.slidOutTo
import br.com.droidchat.ui.feature.chats.ChatsRoute
import br.com.droidchat.ui.feature.chats.navigateToChats
import br.com.droidchat.ui.feature.signin.SignInRoute
import br.com.droidchat.ui.feature.signup.SignUpRoute
import br.com.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost(
    navigationState: DroidChatNavigationState
) {
    val navController = navigationState.navHostController
    val activity = LocalActivity.current

    NavHost(navController = navController, startDestination = Route.SplashRoute) {
        composable<Route.SplashRoute> {
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = Route.SignInRoute,
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                },
                onNavigateToMain = {
                    navController.navigateToChats(
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute) {
                                inclusive = true
                            }
                        })
                },
                onCloseApp = {
                    activity?.finish()
                }
            )
        }
        composable<Route.SignInRoute>(
            enterTransition = {
                this.slidInTo(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            exitTransition = {
                this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(Route.SignUpRoute)
                },
                navigateToHome = {
                    navController.navigateToChats(
                        navOptions = navOptions {
                            popUpTo(Route.SignInRoute) {
                                inclusive = true
                            }
                        })
                }
            )
        }
        composable<Route.SignUpRoute>(
            enterTransition = {
                this.slidInTo(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            SignUpRoute(
                onSignUpSuccess = {
                    navController.popBackStack()
                }
            )
        }
        composable<Route.ChatsRoute> {
            ChatsRoute()
        }
    }
}