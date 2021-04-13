package com.github.ceh9.architect.features.rootFeature

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.github.ceh9.architect.features.calculatorFeature.CalculatorContent

@ExperimentalDecomposeApi
@Composable
fun RootContent(component: RootComponent) {
    Children(routerState = component.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is RootComponent.Child.Calculator -> CalculatorContent(child.component)
        }
    }
}