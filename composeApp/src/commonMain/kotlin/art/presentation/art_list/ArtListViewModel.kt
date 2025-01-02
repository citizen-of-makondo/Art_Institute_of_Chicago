package art.presentation.art_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import art.core.domain.onError
import art.core.domain.onSuccess
import art.domain.ArtworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtListViewModel(
    private val artworkRepository: ArtworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ArtListState())
    val state = _state.asStateFlow()
        .onStart { getAllArtworks() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    fun onAction(action: ArtListAction) {
        when(action) {
            is ArtListAction.OnArtworkClick -> {}
            is ArtListAction.OnRetryClick -> getAllArtworks()
        }
    }

    private fun getAllArtworks() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        artworkRepository.getArtsRemote()
            .onSuccess { result ->
                _state.update {
                    it.copy(
                        searchResults = result,
                        isLoading = false
                    )
                }
            }
            .onError {  }
    }
}
