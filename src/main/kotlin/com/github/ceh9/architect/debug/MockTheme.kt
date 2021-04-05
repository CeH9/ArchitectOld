package com.github.ceh9.architect.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.github.ceh9.architect.core.ui.theme.WidgetTheme
import com.github.ceh9.architect.core.ui.theme.intellij.SwingColor

private val backgroundDarcula = Color(red = 0.23529412f, green = 0.24705882f, blue = 0.25490198f, alpha = 1.0f)
private val onBackgroundDarcula = Color(red = 0.73333335f, green = 0.73333335f, blue = 0.73333335f, alpha = 1.0f)
private val backgroundIntellijLight = Color(red = 0.9490196f, green = 0.9490196f, blue = 0.9490196f, alpha = 1.0f)
private val onBackgroundIntellijLight = Color(red = 0.9490196f, green = 0.9490196f, blue = 0.9490196f, alpha = 1.0f)

@Composable
fun MockWidgetTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) = WidgetTheme(darkTheme, MockSwingColor(darkTheme), content)

@Composable
private fun MockSwingColor(isDark: Boolean = false) = if (isDark) {
    MockDarculaColor()
} else {
    MockIntellijLightColor()
}

@Composable
private fun MockIntellijLightColor() = object : SwingColor {
    override val background = backgroundIntellijLight
    override val onBackground = onBackgroundIntellijLight
}

@Composable
private fun MockDarculaColor() = object : SwingColor {
    override val background = backgroundDarcula
    override val onBackground = onBackgroundDarcula
}