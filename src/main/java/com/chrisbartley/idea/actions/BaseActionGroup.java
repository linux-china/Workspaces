package com.chrisbartley.idea.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;


public abstract class BaseActionGroup extends DefaultActionGroup {
    public BaseActionGroup() {
    }

    public BaseActionGroup(String shortName, boolean popup) {
        super(shortName, popup);
    }


    protected final Project getProject(AnActionEvent event) {
        return (Project) event.getDataContext().getData("project");
    }
}

