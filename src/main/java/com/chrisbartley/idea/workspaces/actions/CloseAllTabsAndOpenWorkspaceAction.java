package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.Workspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class CloseAllTabsAndOpenWorkspaceAction extends BaseWorkspaceAction {
    private final JList<Workspace> jList;

    public CloseAllTabsAndOpenWorkspaceAction(JList<Workspace> jList) {
        super("Close tabs and open", "Close all tabs and open workspace", null);
        this.jList = jList;
    }

    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project != null) {
            if (!this.jList.getSelectionModel().isSelectionEmpty()) {
                final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                for (VirtualFile openFile : fileEditorManager.getOpenFiles()) {
                    fileEditorManager.closeFile(openFile);
                }
                getWorkspaceManager(project).openWorkspaces(this.jList.getSelectedValuesList());
                RefreshableListModel<Workspace> model = (RefreshableListModel<Workspace>) this.jList.getModel();
                model.refresh(this.jList.getSelectedIndices());
            }
        }
    }
}

