package com.github.ceh9.architect.debug

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

/**
 * Playground (sandbox) for testing stuff without launching IDE.
 */
fun main() = Window(
    title = "Compose for Desktop",
    size = IntSize(600, 600),
    location = IntOffset(1700, 500),
    centered = false,
) {
    MockWidgetTheme(darkTheme = true) {
        Surface(Modifier
            .fillMaxSize()
            .background(Color.Green, RoundedCornerShape(0))) {
            Text(
                text = "Hello World!"
            )
        }
    }
}