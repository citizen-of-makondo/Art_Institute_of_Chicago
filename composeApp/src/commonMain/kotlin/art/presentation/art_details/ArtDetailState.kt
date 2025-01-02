package art.presentation.art_details

import art.domain.ArtItem

data class ArtDetailState(
    val isLoading: Boolean = true,
    val id: Long = 0L,
    val art: ArtItem? = null,
)