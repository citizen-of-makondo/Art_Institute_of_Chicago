package art.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArtResponseDto(
    val pagination: PaginationDto,
    val data: List<ArtDataDto>
)