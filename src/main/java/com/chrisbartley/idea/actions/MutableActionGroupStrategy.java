package com.chrisbartley.idea.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

import java.util.List;


public abstract class MutableActionGroupStrategy {
    public abstract List<RegisterableAction> getActions(AnActionEvent paramAnActionEvent);

    public void preparePresentation(AnActionEvent event) {
    }
    
}

