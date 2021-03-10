package com.github.ceh9.architect.demo.services

import com.github.ceh9.architect.core.i18n.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
