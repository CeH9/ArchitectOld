package com.github.ceh9.architect.features.calculatorFeature

import androidx.compose.runtime.State

interface CalculatorComponent {
    val model: State<CalculatorStore.State>

    fun onIncrementClicked()
    fun onDecrementClicked()
}