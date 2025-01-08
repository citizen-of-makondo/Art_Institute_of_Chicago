package art.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MainScreenRoot(
    navigateToArtworkList: () -> Unit,
    navigateToArtistList: () -> Unit
) {
    MainScreenContent(
        navigateToArtworkList = navigateToArtworkList,
        navigateToArtistList = navigateToArtistList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    navigateToArtworkList: () -> Unit,
    navigateToArtistList: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Art Institute of Chicago") }) }
    ) {
        Column {
            Button(
                onClick = { navigateToArtworkList() },
                content = { Text("Go to Artwork List") }
            )

            Button(
                onClick = { navigateToArtistList() },
                content = { Text("Go to Artist List") }
            )
        }
    }
}