package art.data.repository

import art.core.domain.DataError
import art.core.domain.Result
import art.core.domain.map
import art.data.mappers.toDomain
import art.data.network.RemoteArtDataSource
import art.domain.Art
import art.domain.ArtItem
import art.domain.ArtworkRepository

class ArtworkRepositoryImpl(
    private val remoteArtDataSource: RemoteArtDataSource
) : ArtworkRepository {
    override suspend fun getArtsRemote(): Result<Art, DataError.Remote> =
        remoteArtDataSource.getArtsRemote().map { it.toDomain() }

    override suspend fun getArtByIdRemote(id: Long): Result<ArtItem, DataError.Remote> =
        remoteArtDataSource.getArtByIdRemote(id).map { it.toDomain() }
}