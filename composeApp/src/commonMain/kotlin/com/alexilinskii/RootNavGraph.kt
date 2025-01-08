package com.alexilinskii

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import art.presentation.art_details.ArtDetailsAction
import art.presentation.art_details.ArtDetailsRoot
import art.presentation.art_details.ArtDetailsViewModel
import art.presentation.art_list.ArtListRoot
import art.presentation.main_screen.MainScreenRoot
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RootNavGraph(rootNavController: NavHostController) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.Main
    ) {
        composable<Route.Main> {
            MainScreenRoot(
                navigateToArtworkList = { rootNavController.navigate(Route.ArtList) },
            )
        }

        composable<Route.ArtList> {
            ArtListRoot(
                onArtworkClick = { artId ->
                    rootNavController.navigate(Route.ArtDetails(artId))
                },
                navigateToMainScreen = { rootNavController.popBackStack() }
            )
        }

        composable<Route.ArtDetails>(
            enterTransition = { slideInHorizontally { initialOffset -> initialOffset } },
            exitTransition = { slideOutHorizontally { initialOffset -> initialOffset } }
        ) { backStackEntry ->
            val viewModel = koinViewModel<ArtDetailsViewModel>()
            val route = backStackEntry.toRoute<Route.ArtDetails>()
            println("artid ${route.artId}")

            LaunchedEffect(route.artId) {
                viewModel.onAction(ArtDetailsAction.LoadArtDetails(route.artId))
            }

            ArtDetailsRoot(
                viewModel = viewModel,
                navigateBack = {
                    rootNavController.popBackStack()
                }
            )
        }
    }
}
