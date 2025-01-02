package com.alexilinskii

import androidx.compose.ui.window.ComposeUIViewController
import art.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }