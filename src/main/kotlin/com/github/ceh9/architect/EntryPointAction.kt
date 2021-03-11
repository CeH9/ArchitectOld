package com.github.ceh9.architect

import com.github.ceh9.architect.features.home.HomeDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class EntryPointAction : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        actionEvent.project?.also { HomeDialog(it, actionEvent).show() }
    }
}