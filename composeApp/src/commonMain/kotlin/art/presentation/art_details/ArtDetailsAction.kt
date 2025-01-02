package art.presentation.art_details

sealed interface ArtDetailsAction {

    data class LoadArtDetails(val artId: Long) : ArtDetailsAction
}