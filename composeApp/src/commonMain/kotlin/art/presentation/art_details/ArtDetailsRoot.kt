package art.presentation.art_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material.RichText

@Composable
fun ArtDetailsRoot(
    viewModel: ArtDetailsViewModel,
    navigateBack: () -> Unit
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    ArtDetailsScreen(
        state = state,
        navigateBack = navigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtDetailsScreen(
    state: State<ArtDetailState>,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (!state.value.art?.title.isNullOrEmpty() && !state.value.art?.artistTitle.isNullOrEmpty()) {
                        Text(text = "${state.value.art?.title.orEmpty()} by ${state.value.art?.artistTitle.orEmpty()}")
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navigateBack() },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else {
            BoxWithConstraints(modifier = Modifier.padding(paddingValues)) {
                if (maxWidth < 600.dp) {
                    ArtDetailsPhone(state = state.value)
                } else {
                    ArtDetailsTablet(state = state.value)
                }
            }

        }
    }
}

@Composable
fun ArtDetailsPhone(state: ArtDetailState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = "https://www.artic.edu/iiif/2/${state.art?.imageId.orEmpty()}/full/843,/0/default.jpg",
            contentDescription = state.art?.title.orEmpty()
        )

        state.art?.artistDisplay?.run {
            CharacteristicItem(
                title = "Artist",
                value = this
            )
        }

        state.art?.title?.run {
            CharacteristicItem(
                title = "Title",
                value = this
            )
        }

        state.art?.placeOfOrigin?.run {
            CharacteristicItem(
                title = "Place",
                value = this
            )
        }

        state.art?.dateDisplay?.run {
            CharacteristicItem(
                title = "Date",
                value = this
            )
        }

        state.art?.dimensions?.run {
            CharacteristicItem(
                title = "Dimensions",
                value = this
            )
        }

        state.art?.creditLine?.run {
            CharacteristicItem(
                title = "Credit Line",
                value = this
            )
        }

        state.art?.description?.run {
            CharacteristicItem(
                title = "Description",
                value = this
            )
        }
    }
}

@Composable
fun ArtDetailsTablet(state: ArtDetailState) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = "https://www.artic.edu/iiif/2/${state.art?.imageId.orEmpty()}/full/843,/0/default.jpg",
            contentDescription = state.art?.title.orEmpty()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            state.art?.artistDisplay?.run {
                CharacteristicItem(
                    title = "Artist",
                    value = this
                )
            }

            state.art?.title?.run {
                CharacteristicItem(
                    title = "Title",
                    value = this
                )
            }

            state.art?.placeOfOrigin?.run {
                CharacteristicItem(
                    title = "Place",
                    value = this
                )
            }

            state.art?.dateDisplay?.run {
                CharacteristicItem(
                    title = "Date",
                    value = this
                )
            }

            state.art?.dimensions?.run {
                CharacteristicItem(
                    title = "Dimensions",
                    value = this
                )
            }

            state.art?.creditLine?.run {
                CharacteristicItem(
                    title = "Credit Line",
                    value = this
                )
            }

            state.art?.description?.run {
                CharacteristicItem(
                    title = "Description",
                    value = this
                )
            }
        }
    }
}

@Composable
fun CharacteristicItem(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        RichText(
            state = rememberRichTextState().apply {
                setHtml(value)
                config.linkColor = Color.Blue
                config.linkTextDecoration = TextDecoration.Underline
            }
        )
    }
}