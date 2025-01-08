package com.alexilinskii

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Main : Route

    @Serializable
    data object ArtList : Route

    @Serializable
    data class ArtDetails(val artId: Long) : Route
}