package com.github.ceh9.architect.features.calculator.components

import com.arkivanov.decompose.value.Value

interface Calculator {
    val model: Value<Model>

    fun onIncrementClicked()
    fun onDecrementClicked()

    data class Model(
        val count: Int = 0,
    )
}