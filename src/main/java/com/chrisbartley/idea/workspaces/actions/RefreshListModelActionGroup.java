package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.actions.BaseActionGroup;
import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.Workspace;
import com.chrisbartley.idea.workspaces.WorkspaceManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public final class RefreshListModelActionGroup extends BaseActionGroup {
    private final RefreshableListModel<Workspace> listModel;

    public RefreshListModelActionGroup(RefreshableListModel<Workspace> listModel) {
        this(null, false, listModel);
    }


    public RefreshListModelActionGroup(String shortName, boolean popup, RefreshableListModel<Workspace> listModel) {
        super(shortName, popup);
        this.listModel = listModel;
    }


    public void update(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project != null) {
            WorkspaceManager workspaceManager = project.getService(WorkspaceManager.class);
            workspaceManager.updateImplicitAutoPinningAndUnpinning();
            this.listModel.refreshAll();
        }
    }
}
