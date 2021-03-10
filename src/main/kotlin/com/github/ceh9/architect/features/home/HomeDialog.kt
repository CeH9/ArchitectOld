package com.github.ceh9.architect.features.home

import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.github.ceh9.architect.core.utils.ComposeSizeAdjustmentWrapper
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.github.ceh9.architect.core.ui.theme.WidgetTheme
import java.awt.Dimension
import javax.swing.JComponent

class HomeDialog (project: Project?) : DialogWrapper(project) {
    init {
        title = "Demo"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val dialog = this
        return ComposePanel().apply {
            preferredSize = Dimension(800, 600)

            setContent {
                ComposeSizeAdjustmentWrapper(
                    window = dialog,
                    panel = this,
                    preferredSize = IntSize(800, 600)
                ) {
                    val count = remember { mutableStateOf(0) }

                    WidgetTheme(darkTheme = true) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            Counter(count)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(count: MutableState<Int>) {
    Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                count.value++
            }) {
            Text(if (count.value == 0) "Hello World" else "Clicked ${count.value}!")
        }
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                count.value = 0
            }) {
            Text("Reset")
        }
    }
}