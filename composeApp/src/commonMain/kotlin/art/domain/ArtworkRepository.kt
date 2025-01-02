package art.domain

import art.core.domain.DataError
import art.core.domain.Result

interface ArtworkRepository {
    suspend fun getArtsRemote(): Result<Art, DataError.Remote>

    suspend fun getArtByIdRemote(id: Long): Result<ArtItem, DataError.Remote>
}
