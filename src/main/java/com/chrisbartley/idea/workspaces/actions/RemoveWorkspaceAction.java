package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.workspaces.Icons;
import com.chrisbartley.idea.workspaces.Workspace;
import com.chrisbartley.idea.workspaces.WorkspaceManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public final class RemoveWorkspaceAction extends BaseWorkspaceAction {
    private final Workspace workspace;
    private final JList<Workspace> jList;
    private final boolean showConfirmation;

    public RemoveWorkspaceAction(Workspace workspace, boolean showConfirmation) {
        this(workspace.getName(), "Remove '" + workspace.getName() + "'", null, workspace, showConfirmation);
    }

    public RemoveWorkspaceAction(JList<Workspace> jList, boolean showConfirmation) {
        this("Remove", "Remove the selected workspace(s)", jList, null, showConfirmation);
    }


    private RemoveWorkspaceAction(String text, String description, JList<Workspace> jList, Workspace workspace, boolean showConfirmation) {
        super(text, description, null);
        this.workspace = workspace;
        this.jList = jList;
        this.showConfirmation = showConfirmation;
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {
            int[] indicesToRemove = null;
            if (this.jList != null) {
                if (!this.jList.getSelectionModel().isSelectionEmpty()) {
                    indicesToRemove = this.jList.getSelectedIndices();
                }
            } else {
                int index = getWorkspaceManager(project).indexOf(this.workspace);
                if (index >= 0) {
                    indicesToRemove = new int[]{index};
                }
            }
            if (indicesToRemove != null) {
                int confirmationResult = 0;
                if (this.showConfirmation) {
                    String prompt = (indicesToRemove.length == 1) ? "Remove the selected workspace?" : "Remove the selected workspaces?";
                    confirmationResult = Messages.showYesNoDialog(project, prompt, "Confirm Remove", Messages.getQuestionIcon());
                }
                if (confirmationResult == 0) {
                    WorkspaceManager workspaceManager = getWorkspaceManager(project);
                    workspaceManager.removeWorkspaces(indicesToRemove);
                    if (workspaceManager.getWorkspaceCount() == 0) {
                        event.getPresentation().setEnabled(false);
                    }
                }
            }
        }
    }


    public void update(@NotNull AnActionEvent event) {
        if (getProject(event) != null) {
            if (this.jList != null) {
                event.getPresentation().setEnabled(!this.jList.getSelectionModel().isSelectionEmpty());
            }

            if ("WORKSPACES_TOOL_WINDOW".equals(event.getPlace())) {
                event.getPresentation().setIcon(Icons.REMOVE_WORKSPACE);
            }
        }
    }
}

