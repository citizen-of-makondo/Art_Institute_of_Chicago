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
) {
    MainScreenContent(
        navigateToArtworkList = navigateToArtworkList,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    navigateToArtworkList: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Art Institute of Chicago") }) }
    ) {
        Column {
            MainScreenItem(
                title = "Go to Artwork List",
                onClick = navigateToArtworkList
            )
        }
    }
}

@Composable
fun MainScreenItem(
    title: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        content = { Text(title) }
    )
}