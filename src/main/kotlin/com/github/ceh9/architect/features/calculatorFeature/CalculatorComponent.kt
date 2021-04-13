package com.github.ceh9.architect.features.calculatorFeature

import com.arkivanov.decompose.value.Value

interface CalculatorComponent {
    val model: Value<Model>

    fun onIncrementClicked()
    fun onDecrementClicked()

    data class Model(
        val count: Int = 0,
    )
}