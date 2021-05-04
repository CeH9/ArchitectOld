package com.github.ceh9.architect.features.calculatorFeature

import kotlinx.coroutines.flow.StateFlow

interface CalculatorComponent {
    val model: StateFlow<CalculatorStore.State>

    fun onIncrementClicked()
    fun onDecrementClicked()
}