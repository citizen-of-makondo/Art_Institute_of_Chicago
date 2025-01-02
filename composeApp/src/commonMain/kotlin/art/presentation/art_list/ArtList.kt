package art.presentation.art_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import art.domain.ArtItem
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ArtListRoot(
    viewModel: ArtListViewModel = koinViewModel(),
    onArtworkClick: (Long) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ArtListScreen(
        state = state,
        onArtworkClick = onArtworkClick
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ArtListScreen(
    state: ArtListState,
    onArtworkClick: (Long) -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(8.dp)
                .verticalScroll(scrollState),
            maxItemsInEachRow = 10,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }

            state.searchResults?.art?.forEach { art ->
                ArtItem(
                    art = art,
                    onArtworkClick = { artId -> onArtworkClick(artId) }
                )
            }
        }
        /*LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(state.searchResults?.art.orEmpty()) { art ->

            }
        }*/
    }
}

@Composable
fun ArtItem(
    art: ArtItem,
    onArtworkClick: (Long) -> Unit
) {
    AsyncImage(
        model = art.imageId.orEmpty(),
        contentDescription = null,
        modifier = Modifier.heightIn(max = 200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onArtworkClick(art.id ?: 0L) })
    )
    /*
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {


        Column {
            Text(text = art.artistDisplay.orEmpty())

            Text(text = art.title.orEmpty())
        }
    }*/
}