package art.presentation.art_list

import art.domain.Art

data class ArtListState(
    val searchResults: Art? = null,
    val isLoading: Boolean = true
)