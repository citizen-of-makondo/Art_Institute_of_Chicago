package art.presentation.art_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import art.core.domain.onError
import art.core.domain.onSuccess
import art.data.mappers.toDomain
import art.domain.ArtworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArtListViewModel(
    private val artworkRepository: ArtworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(value = ArtListState())
    val state = _state.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    init {
        getAllArtworks()
    }

    fun onAction(action: ArtListAction) {
        when (action) {
            is ArtListAction.OnArtworkClick -> {}
            is ArtListAction.OnRetryClick -> getAllArtworks()
        }
    }

    private fun getAllArtworks() =
        viewModelScope.launch {
            artworkRepository.getArtsRemote(query = "", page = 1)
                .onSuccess {
                    _state.update { state ->
                        state.copy(
                            searchResults = it.toDomain(),
                            artworkList = it.toDomain().art,
                            isLoading = false
                        )
                    }
                }
                .onError { }
        }

    fun loadNextPage() =
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            artworkRepository.getArtsRemote(
                query = "",
                page = (state.value.searchResults?.pagination?.currentPage ?: 1) + 1
            )
                .onSuccess {
                    _state.update { state ->
                        state.copy(
                            searchResults = it.toDomain(),
                            artworkList = state.artworkList + it.toDomain().art,
                            isLoading = false
                        )
                    }
                }
                .onError { }
        }
}
