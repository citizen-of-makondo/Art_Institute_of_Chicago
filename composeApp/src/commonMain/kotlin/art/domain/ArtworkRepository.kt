package art.domain

import art.core.domain.DataError
import art.core.domain.Result
import art.data.dto.ArtResponseDto

interface ArtworkRepository {
    suspend fun getArtsRemote(
        query: String,
        page: Int
    ): Result<ArtResponseDto, DataError.Remote>

    suspend fun getArtByIdRemote(id: Long): Result<ArtItem, DataError.Remote>
}
