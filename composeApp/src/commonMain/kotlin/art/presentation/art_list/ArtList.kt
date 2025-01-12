package art.presentation.art_list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import art.domain.ArtItem
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

enum class ArtListScreenState {
    DEFAULT,
    SEARCH
}

@Composable
fun ArtListRoot(
    viewModel: ArtListViewModel = koinViewModel(),
    onArtworkClick: (Long) -> Unit,
    navigateToMainScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ArtListScreen(
        state = state,
        loadNextPage = viewModel::loadNextPage,
        onArtworkClick = onArtworkClick,
        navigateToMainScreen = navigateToMainScreen
    )
}

@Composable
fun AnimatedCircularProgressIndicator() {
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Magenta
    )

    var colorIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            colorIndex = (colorIndex + 1) % colors.size
            delay(300)
        }
    }

    val animatedColor by animateColorAsState(
        targetValue = colors[colorIndex],
        animationSpec = tween(durationMillis = 300)
    )

    CircularProgressIndicator(
        color = animatedColor,
        strokeWidth = 4.dp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtListScreen(
    state: ArtListState,
    loadNextPage: () -> Unit,
    onArtworkClick: (Long) -> Unit,
    navigateToMainScreen: () -> Unit
) {
    var isSearch by remember {
        mutableStateOf(ArtListScreenState.DEFAULT)
    }
    var searchQuery by remember {
        mutableStateOf("")
    }
    var previousFirstVisibleItemIndex by remember { mutableStateOf(0) }
    val page by remember(state.searchResults) {
        mutableStateOf(state.searchResults?.pagination?.currentPage ?: 0)
    }

    val staggeredGridState = rememberLazyStaggeredGridState()

    LaunchedEffect(staggeredGridState, page) {
        snapshotFlow { staggeredGridState.firstVisibleItemIndex }
            .collect { firstVisibleItemIndex ->
                Logger.d { "ArtListScreen ${firstVisibleItemIndex} ${previousFirstVisibleItemIndex} ${page}" }
                if (firstVisibleItemIndex > previousFirstVisibleItemIndex) { // Проверяем направление скролла (вниз)
                    if (previousFirstVisibleItemIndex > (70 * page)) { // Каждые 70 элементов
                        loadNextPage()
                    }
                    previousFirstVisibleItemIndex = firstVisibleItemIndex
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    when (isSearch) {
                        ArtListScreenState.DEFAULT -> Text("Artwork List")
                        ArtListScreenState.SEARCH -> TextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                searchQuery = query
                            },
                            label = { Text("Search") }
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            when (isSearch) {
                                ArtListScreenState.DEFAULT -> navigateToMainScreen()
                                ArtListScreenState.SEARCH -> isSearch = ArtListScreenState.DEFAULT
                            }
                        },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFe9e9dc)
                ),
                actions = {
                    IconButton(
                        onClick = { isSearch = ArtListScreenState.SEARCH },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.Send,
                                contentDescription = "Search"
                            )
                        }
                    )
                }
            )
        },
        containerColor = Color(0xFFe9e9dc),
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedCircularProgressIndicator()
            }
        }

        BoxWithConstraints {
            val size = if (maxWidth < 600.dp) {
                StaggeredGridCells.Adaptive(150.dp)
            } else {
                StaggeredGridCells.Adaptive(300.dp)
            }
            LazyVerticalStaggeredGrid(
                state = staggeredGridState,
                columns = size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(8.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    items(
                        items = state.artworkList,
                     //   key = { art -> "${art.id ?: 0L} + ${art.imageId.hashCode()} + ${art.artistTitle.hashCode()}" }
                    ) { art ->
                        ArtItem(
                            art = art,
                            onArtworkClick = { artId -> onArtworkClick(artId) }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ArtItem(
    art: ArtItem,
    onArtworkClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .shadow(3.dp)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = "https://www.artic.edu/iiif/2/${art.imageId}/full/400,/0/default.jpg",
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = { onArtworkClick(art.id ?: 0L) })
        )

        Text(text = art.title.orEmpty())

        Text(text = art.artistTitle.orEmpty())
    }
}