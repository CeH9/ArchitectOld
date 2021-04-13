package com.github.ceh9.architect.features.calculatorFeature

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.asState

@Composable
fun CalculatorContent(component: CalculatorComponent) {
    val model by component.model.asState()

    Column {
        Text(
            text = "count: ${model.count}",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(Modifier.size(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = component::onIncrementClicked,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) { Text("Increment") }

            Spacer(Modifier.size(16.dp))

            Button(
                onClick = component::onDecrementClicked,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) { Text("Decrement") }
        }
    }
}