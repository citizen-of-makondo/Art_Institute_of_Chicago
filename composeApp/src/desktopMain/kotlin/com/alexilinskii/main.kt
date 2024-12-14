package com.alexilinskii

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Art Institute of Chicago",
    ) {
        App()
    }
}