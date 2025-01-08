package com.alexilinskii

import AppTheme
import androidx.compose.foundation.isSystemInDarkTheme
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
            AppTheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            ) {
                App()
            }
        }
    }
}