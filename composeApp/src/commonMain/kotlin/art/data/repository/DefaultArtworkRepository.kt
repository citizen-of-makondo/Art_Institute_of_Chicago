package art.data.repository

import art.core.domain.DataError
import art.core.domain.Result
import art.core.domain.map
import art.data.dto.ArtResponseDto
import art.data.mappers.toDomain
import art.data.network.RemoteArtDataSource
import art.domain.ArtItem
import art.domain.ArtworkRepository

class ArtworkRepositoryImpl(
    private val remoteArtDataSource: RemoteArtDataSource
) : ArtworkRepository {

    override suspend fun getArtsRemote(
        query: String,
        page: Int
    ): Result<ArtResponseDto, DataError.Remote> =
        remoteArtDataSource.getArtsRemote(
            query = query,
            page = page,
            limit = 100
        )

    override suspend fun getArtByIdRemote(id: Long): Result<ArtItem, DataError.Remote> =
        remoteArtDataSource.getArtByIdRemote(id).map { it.toDomain() }

    companion object {
        private const val NETWORK_PAGE_SIZE = 100
    }
}
