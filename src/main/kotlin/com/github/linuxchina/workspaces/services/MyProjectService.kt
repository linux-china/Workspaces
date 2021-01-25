package com.github.linuxchina.workspaces.services

import com.github.linuxchina.workspaces.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
