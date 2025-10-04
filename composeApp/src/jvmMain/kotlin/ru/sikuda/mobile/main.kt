package ru.sikuda.mobile

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "eratosfen3",
    ) {
        App()
    }
}