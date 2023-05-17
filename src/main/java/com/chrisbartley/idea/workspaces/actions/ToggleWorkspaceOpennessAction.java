package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.workspaces.Workspace;
import com.chrisbartley.idea.workspaces.WorkspaceState;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public final class ToggleWorkspaceOpennessAction extends BaseWorkspaceAction {
    private final Project project;
    private final Workspace workspace;

    public ToggleWorkspaceOpennessAction(Project project, Workspace workspace) {
        super(workspace.getName(), "Open the '" + workspace.getName() + "' workspace", null);
        this.project = project;
        this.workspace = workspace;
    }

    protected String getActionRegistrationId() {
        return "ToggleWorkspaceOpenClosed." + this.project.getProjectFile().getNameWithoutExtension() + "." + this.workspace.getName();
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        if (this.project != null) {
            getWorkspaceManager(this.project).toggleWorkspaceOpenness(this.workspace);
        }
    }


    public void update(@NotNull AnActionEvent event) {
        if (this.project != null) {

            WorkspaceState workspaceState = this.workspace.getState(FileEditorManager.getInstance(this.project));

            event.getPresentation().setIcon(workspaceState.getComboStatusIcon());
            event.getPresentation().setDescription(getWorkspaceManager(this.project).getToggleWorkspaceOpennessDescription(this.workspace));
        }
    }


    public String toString() {
        return this.workspace.getName();
    }
}

