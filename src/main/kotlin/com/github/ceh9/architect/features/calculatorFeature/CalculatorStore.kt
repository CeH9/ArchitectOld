package com.github.ceh9.architect.features.calculatorFeature

import com.arkivanov.mvikotlin.core.store.Store

interface CalculatorStore : Store<CalculatorStore.Intent, CalculatorStore.State, CalculatorStore.Label> {

    data class State(
        val count: Int = 0,
    )

    sealed class Intent {
        object Increment : Intent()
        object Decrement : Intent()
    }

    sealed class Label
}