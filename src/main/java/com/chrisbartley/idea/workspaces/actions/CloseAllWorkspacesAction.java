package com.chrisbartley.idea.workspaces.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public final class CloseAllWorkspacesAction extends BaseWorkspaceAction {
    public CloseAllWorkspacesAction() {
        super("Close All Workspaces", "Close all workspaces", (Icon) null);
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project != null) {
            getWorkspaceManager(project).closeAllWorkspaces();
        }
    }


    public void update(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project != null) {

            event.getPresentation().setEnabled((getWorkspaceManager(project).getWorkspaceCount() > 0));
        } else {

            event.getPresentation().setEnabled(false);
        }
    }
}

