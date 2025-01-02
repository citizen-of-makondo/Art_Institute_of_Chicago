package art.data.network

import art.core.data.safeCall
import art.core.domain.DataError
import art.core.domain.Result
import art.data.dto.ArtDto
import art.data.dto.ArtResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://api.artic.edu/api/v1"

class RemoteArtDataSourceImpl(
    private val httpClient: HttpClient
): RemoteArtDataSource {

    override suspend fun getArtsRemote(): Result<ArtResponseDto, DataError.Remote> =
        safeCall<ArtResponseDto> {
            httpClient.get(urlString = "$BASE_URL/artworks") {
                parameter("limit", 100)
            }
        }

    override suspend fun getArtByIdRemote(id: Long): Result<ArtDto, DataError.Remote> =
        safeCall<ArtDto> {
            httpClient.get(urlString = "$BASE_URL/artworks/$id")
        }
}
