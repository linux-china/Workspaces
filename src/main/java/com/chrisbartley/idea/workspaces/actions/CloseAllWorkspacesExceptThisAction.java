package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.Workspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public final class CloseAllWorkspacesExceptThisAction extends BaseWorkspaceAction {
    private final JList<Workspace> jList;
    private final Workspace workspace;

    public CloseAllWorkspacesExceptThisAction(Workspace workspace) {
        this(workspace.getName(), "Close all workspaces except '" + workspace.getName() + "'", null, workspace);
    }


    public CloseAllWorkspacesExceptThisAction(JList<Workspace> jList) {
        this(null, null, jList, null);
    }


    private CloseAllWorkspacesExceptThisAction(String text, String description, JList<Workspace> jList, Workspace workspace) {
        super(text, description, null);
        this.jList = jList;
        this.workspace = workspace;
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {
            if (this.jList != null) {
                if (!this.jList.getSelectionModel().isSelectionEmpty()) {
                    getWorkspaceManager(project).closeAllButTheseWorkspaces(this.jList.getSelectedValuesList());
                    RefreshableListModel<Workspace> model = (RefreshableListModel<Workspace>) this.jList.getModel();
                    model.refreshAllButThese(this.jList.getSelectedIndices());
                }
            } else {
                getWorkspaceManager(project).closeAllButThisWorkspace(this.workspace);
            }
        }
    }


    public void update(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {
            if (this.jList != null) {
                event.getPresentation().setEnabled(!this.jList.getSelectionModel().isSelectionEmpty());
                if (this.jList.getSelectedValuesList().size() == 1) {
                    event.getPresentation().setText("Close All Workspaces Except This");
                    event.getPresentation().setDescription("Close all workspaces except the currently selected one");
                } else {
                    event.getPresentation().setText("Close All Workspaces Except These");
                    event.getPresentation().setDescription("Close all workspaces except the currently selected ones");
                }
            } else {
                event.getPresentation().setEnabled(true);
            }
        }
    }
}
