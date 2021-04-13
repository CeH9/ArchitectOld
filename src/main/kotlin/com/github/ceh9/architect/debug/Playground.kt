package com.github.ceh9.architect.debug

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import com.arkivanov.mvikotlin.core.utils.setMainThreadId
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.github.ceh9.architect.features.rootFeature.RootComponent
import com.github.ceh9.architect.features.rootFeature.RootComponentImpl
import com.github.ceh9.architect.features.rootFeature.RootContent

/**
 * Playground (sandbox) for testing stuff without launching IDE.
 */
@ExperimentalDecomposeApi
fun main() = Window(
    title = "Compose for Desktop",
    size = IntSize(500, 500),
    location = IntOffset(1700, 500),
    undecorated = false,
    centered = false,
) {
    setMainThreadId(Thread.currentThread().id)
    val rootComponent = rememberRootComponent(factory = ::root)

    MockWidgetTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            RootContent(rootComponent)
        }
    }
}

private fun root(componentContext: ComponentContext): RootComponent {
    return RootComponentImpl(
        componentContext = componentContext,
        storeFactory = DefaultStoreFactory,
    )
}