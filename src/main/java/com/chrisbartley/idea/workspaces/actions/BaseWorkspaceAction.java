package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.actions.RegisterableAction;
import com.chrisbartley.idea.workspaces.WorkspaceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class BaseWorkspaceAction extends RegisterableAction {
    public BaseWorkspaceAction() {
    }

    public BaseWorkspaceAction(String text) {
        super(text);
    }


    public BaseWorkspaceAction(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    final WorkspaceManager getWorkspaceManager(@NotNull Project project) {
        return project.getService(WorkspaceManager.class);
    }
}
