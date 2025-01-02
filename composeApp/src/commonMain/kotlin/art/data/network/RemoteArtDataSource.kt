package art.data.network

import art.core.domain.DataError
import art.core.domain.Result
import art.data.dto.ArtDto
import art.data.dto.ArtResponseDto

interface RemoteArtDataSource {
    
    suspend fun getArtsRemote(): Result<ArtResponseDto, DataError.Remote>

    suspend fun getArtByIdRemote(id: Long): Result<ArtDto, DataError.Remote>
}