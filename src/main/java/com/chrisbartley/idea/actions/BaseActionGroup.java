package com.chrisbartley.idea.actions;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public abstract class BaseActionGroup extends DefaultActionGroup {
    public BaseActionGroup() {
    }

    public BaseActionGroup(String shortName, boolean popup) {
        super(shortName, popup);
    }
    

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }
}

