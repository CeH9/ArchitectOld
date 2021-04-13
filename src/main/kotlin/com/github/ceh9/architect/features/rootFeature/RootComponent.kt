package com.github.ceh9.architect.features.rootFeature

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.github.ceh9.architect.features.calculatorFeature.CalculatorComponent

interface RootComponent {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Calculator(val component: CalculatorComponent) : Child()
    }
}