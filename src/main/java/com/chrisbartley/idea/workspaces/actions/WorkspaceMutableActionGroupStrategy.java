package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.actions.MutableActionGroupStrategy;
import com.chrisbartley.idea.workspaces.WorkspaceManager;
import com.intellij.openapi.actionSystem.AnActionEvent;

public abstract class WorkspaceMutableActionGroupStrategy extends MutableActionGroupStrategy {
    protected final WorkspaceManager getWorkspaceManager(AnActionEvent event) {
        return event.getProject().getService(WorkspaceManager.class);
    }
}

