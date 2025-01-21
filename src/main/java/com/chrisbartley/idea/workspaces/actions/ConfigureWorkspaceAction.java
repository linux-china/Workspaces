package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.workspaces.Icons;
import com.chrisbartley.idea.workspaces.Workspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public final class ConfigureWorkspaceAction extends BaseWorkspaceAction {
    private final JList<Workspace> jList;
    private Workspace workspace;

    public ConfigureWorkspaceAction(Workspace workspace) {
        this(workspace.getName(), "Configure '" + workspace.getName() + "'", null, null, workspace);
    }

    public ConfigureWorkspaceAction(JList<Workspace> jList) {
        this("Properties", "Configure the selected workspace", Icons.CONFIGURE_WORKSPACE, jList, null);
    }

    private ConfigureWorkspaceAction(String text, String description, Icon icon, JList<Workspace> jList, Workspace workspace) {
        super(text, description, icon);
        this.jList = jList;
        this.workspace = workspace;
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project != null) {
            if (this.jList != null && !this.jList.getSelectionModel().isSelectionEmpty()) {
                Object[] selectedWorkspaces = this.jList.getSelectedValues();
                if (selectedWorkspaces.length == 1) {
                    this.workspace = (Workspace) selectedWorkspaces[0];
                }
            }


            if (this.workspace != null) {
                ConfigureWorkspaceDialog dialog = new ConfigureWorkspaceDialog("Workspace Properties", "OK", this.workspace);
                dialog.pack();
                dialog.show();
                if (dialog.getExitCode() == 0) {
                    this.workspace.update(dialog.getNewWorkspaceName(), dialog.getNewWorkspaceUrls());
                }
            }
        }
    }


    public void update(@NotNull AnActionEvent event) {
        if (event.getProject() != null) {
            if (this.jList != null) {
                event.getPresentation().setEnabled((!this.jList.getSelectionModel().isSelectionEmpty() && (this.jList.getSelectedIndices()).length == 1));
            } else {
                event.getPresentation().setEnabled(true);
            }
        }
    }
}


