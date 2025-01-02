package art.presentation.art_list

sealed interface ArtListAction {

    data class OnArtworkClick(val id: String): ArtListAction

    data object OnRetryClick: ArtListAction
}