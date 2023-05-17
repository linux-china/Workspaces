package com.chrisbartley.idea.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

import javax.swing.*;


public abstract class BaseAction extends AnAction {
    public BaseAction() {
    }

    public BaseAction(String text) {
        super(text);
    }


    public BaseAction(String text, String description, Icon icon) {
        super(text, description, icon);
    }


    protected final Project getProject(AnActionEvent event) {
        return (Project) event.getDataContext().getData("project");
    }
}

