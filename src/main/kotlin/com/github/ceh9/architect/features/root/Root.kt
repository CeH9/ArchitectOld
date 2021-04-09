package com.github.ceh9.architect.features.root

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value

interface Root {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Calculator(val component: com.github.ceh9.architect.features.calculator.components.Calculator): Child()
    }
}