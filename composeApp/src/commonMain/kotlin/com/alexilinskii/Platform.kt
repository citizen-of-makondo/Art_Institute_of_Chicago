package com.alexilinskii

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform