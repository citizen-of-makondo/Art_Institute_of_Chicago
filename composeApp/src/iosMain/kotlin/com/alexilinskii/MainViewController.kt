package com.alexilinskii

import AppTheme
import androidx.compose.ui.window.ComposeUIViewController
import art.di.initKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    AppTheme(
        darkTheme = isDarkTheme,
        dynamicColor = false,
    ) {
        App()
    }
}
