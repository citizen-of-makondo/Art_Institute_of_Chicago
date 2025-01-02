package com.alexilinskii

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import art.di.initKoin

fun main() {
    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Art Institute of Chicago",
        ) {
            App()
        }
    }
}