package art.presentation.art_details

import androidx.lifecycle.ViewModel
import art.core.domain.onError
import art.core.domain.onSuccess
import art.domain.ArtworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArtDetailsViewModel(
    private val artworkRepository: ArtworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ArtDetailState())
    val state = _state.asStateFlow()

    suspend fun onAction(action: ArtDetailsAction) {
        when (action) {
            is ArtDetailsAction.LoadArtDetails ->
                artworkRepository.getArtByIdRemote(action.artId)
                    .onSuccess { result ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                art = result
                            )
                        }
                    }
                    .onError {  }

        }
    }

}