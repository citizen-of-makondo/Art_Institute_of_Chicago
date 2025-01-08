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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import art.domain.ArtItem
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ArtListRoot(
    viewModel: ArtListViewModel = koinViewModel(),
    onArtworkClick: (Long) -> Unit,
    navigateToMainScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ArtListScreen(
        state = state,
        onArtworkClick = onArtworkClick,
        navigateToMainScreen = navigateToMainScreen
    )
}

@Composable
fun AnimatedCircularProgressIndicator() {
    // Список цветов для анимации
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Magenta
    )

    // Запоминаем индекс текущего цвета
    var colorIndex by remember { mutableStateOf(0) }

    // Запускаем цикл смены цветов каждые 100 мс
    LaunchedEffect(Unit) {
        while (true) {
            colorIndex = (colorIndex + 1) % colors.size
            delay(300)
        }
    }

    // Анимация цвета
    val animatedColor by animateColorAsState(
        targetValue = colors[colorIndex],
        animationSpec = tween(durationMillis = 300)
    )

    // Отображение индикатора
    CircularProgressIndicator(
        color = animatedColor,
        strokeWidth = 4.dp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtListScreen(
    state: ArtListState,
    onArtworkClick: (Long) -> Unit,
    navigateToMainScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Artwork List") },
                navigationIcon = {
                    IconButton(
                        onClick = { navigateToMainScreen() },
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
                )
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
                columns = size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(8.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    items(
                        state.searchResults?.art.orEmpty(),
                        key = { art -> art.id ?: 0L }
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