package com.alexilinskii

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import art.presentation.art_details.ArtDetailsAction
import art.presentation.art_details.ArtDetailsRoot
import art.presentation.art_details.ArtDetailsViewModel
import art.presentation.art_list.ArtListRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.ArtList
        ) {
            composable<Route.ArtList> {
                ArtListRoot(
                    onArtworkClick = { artId ->
                        navController.navigate(Route.ArtDetails(artId))
                    }
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

                ArtDetailsRoot(viewModel = viewModel)
            }
        }
    }
}