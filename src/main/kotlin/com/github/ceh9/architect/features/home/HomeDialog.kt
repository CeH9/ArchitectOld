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
import com.github.ceh9.architect.core.i18n.MyBundle
import com.github.ceh9.architect.core.utils.ComposeSizeAdjustmentWrapper
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.github.ceh9.architect.core.ui.theme.WidgetTheme
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import java.awt.Dimension
import java.util.*
import javax.swing.JComponent

class HomeDialog(
    private val project: Project,
    private val actionEvent: AnActionEvent
) : DialogWrapper(project) {
    init {
        title = MyBundle.message("name")
        init()
    }

    override fun createCenterPanel(): JComponent {
        val dialog = this

        return ComposePanel().apply {
            val width = 800
            val height = 600

            preferredSize = Dimension(width, height)

            setContent {
                ComposeSizeAdjustmentWrapper(
                    window = dialog,
                    panel = this,
                    preferredSize = IntSize(width, height)
                ) {
                    val count = remember { mutableStateOf(0) }

                    WidgetTheme(darkTheme = true) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            Counter(count, onRunTemplate = { runTemplate() })
                        }
                    }
                }
            }
        }
    }

    private fun runTemplate() {
        val template = FileTemplateManager
            .getInstance(project)
            .getTemplate("MyFileTemplate")

        val fileName = "FooBar"

        val properties = Properties()
        properties.put("NAME", fileName)

        val psiParent = actionEvent.getData(CommonDataKeys.PSI_FILE)!!.parent

        FileTemplateUtil.createFromTemplate(
            template,
            fileName,
            properties,
            psiParent!!
        )
    }
}

@Composable
fun Counter(
    count: MutableState<Int>,
    onRunTemplate: () -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onRunTemplate
        ) {
            Text("Run Template")
        }
    }
}