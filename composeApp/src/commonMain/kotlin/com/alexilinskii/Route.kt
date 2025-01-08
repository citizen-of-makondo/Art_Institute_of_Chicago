package com.alexilinskii

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Main : Route

    @Serializable
    data object ArtList : Route

    @Serializable
    data class ArtDetails(val artId: Long) : Route

    @Serializable
    data object ArtistList : Route

    @Serializable
    data class ArtistDetails(val artistId: Long) : Route
}