package art.presentation.art_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage

@Composable
fun ArtDetailsRoot(
    viewModel: ArtDetailsViewModel
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    ArtDetailsScreen(
        state = state
    )
}

@Composable
fun ArtDetailsScreen(state: State<ArtDetailState>) {
    if (state.value.isLoading) {
        CircularProgressIndicator()
    } else {
        Column {
            AsyncImage(
                model = state.value.art?.imageId.orEmpty(),
                contentDescription = state.value.art?.title.orEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = state.value.art?.title.orEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = state.value.art?.artistDisplay.orEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
