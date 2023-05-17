package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.Icons;
import com.chrisbartley.idea.workspaces.Workspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public final class ToggleWorkspacePinAction extends BaseWorkspaceAction {
    private final JList<Workspace> list;
    private final Workspace workspace;

    public ToggleWorkspacePinAction(Workspace workspace) {
        this(workspace.getName(), "Pin/unpin '" + workspace.getName() + "'", (Icon) null, null, workspace);
    }


    public ToggleWorkspacePinAction(JList<Workspace> list) {
        this("Toggle Pin", "Pin/unpin the selected workspace(s)", Icons.PINNED, list, (Workspace) null);
    }


    private ToggleWorkspacePinAction(String text, String description, Icon icon, JList<Workspace> list, Workspace workspace) {
        super(text, description, icon);
        this.list = list;
        this.workspace = workspace;
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {
            if (this.list != null) {
                if (!this.list.getSelectionModel().isSelectionEmpty()) {
                    getWorkspaceManager(project).toggleWorkspacePinnedness(this.list.getSelectedValuesList());
                    RefreshableListModel<Workspace> model = (RefreshableListModel<Workspace>) this.list.getModel();
                    model.refresh(this.list.getSelectedIndices());
                }
            } else {
                getWorkspaceManager(project).toggleWorkspacePinnedness(this.workspace);
            }
        }
    }


    public void update(@NotNull AnActionEvent event) {
        if (getProject(event) != null) {
            if (this.list != null) {
                event.getPresentation().setEnabled(!this.list.getSelectionModel().isSelectionEmpty());
            } else {
                event.getPresentation().setEnabled(true);
                event.getPresentation().setDescription((this.workspace.isPinned() ? "Unpin" : "Pin") + " '" + this.workspace.getName() + "'");
            }
        }
    }
}

