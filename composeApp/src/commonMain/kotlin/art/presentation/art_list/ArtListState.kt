package art.presentation.art_list

import art.domain.Art
import art.domain.ArtItem

data class ArtListState(
    val searchResults: Art? = null,
    val artworkList: List<ArtItem> = emptyList(),
    val isLoading: Boolean = true
)