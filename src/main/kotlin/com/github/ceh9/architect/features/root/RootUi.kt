package com.github.ceh9.architect.features.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.github.ceh9.architect.features.calculator.components.CalculatorContent

@ExperimentalDecomposeApi
@Composable
fun RootContent(component: Root) {
    Children(routerState = component.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is Root.Child.Calculator -> CalculatorContent(child.component)
        }
    }
}