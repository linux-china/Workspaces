package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.Icons;
import com.chrisbartley.idea.workspaces.Workspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class CloseWorkspaceAction extends BaseWorkspaceAction {
    private final JList<Workspace> jList;

    public CloseWorkspaceAction(JList<Workspace> jList) {
        super("Close", "Close the selected workspace(s)", Icons.CLOSE_WORKSPACE);
        this.jList = jList;
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {
            if (!this.jList.getSelectionModel().isSelectionEmpty()) {
                getWorkspaceManager(project).closeWorkspaces(this.jList.getSelectedValuesList());
                RefreshableListModel<Workspace> model = (RefreshableListModel<Workspace>) this.jList.getModel();
                model.refresh(this.jList.getSelectedIndices());
            }
        }
    }


    public void update(AnActionEvent event) {
        event.getPresentation().setEnabled(!this.jList.getSelectionModel().isSelectionEmpty());
    }
}


