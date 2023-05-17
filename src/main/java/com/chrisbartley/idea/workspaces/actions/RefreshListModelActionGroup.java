package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.actions.BaseActionGroup;
import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.WorkspaceManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public final class RefreshListModelActionGroup extends BaseActionGroup {
    private final RefreshableListModel listModel;

    public RefreshListModelActionGroup(RefreshableListModel listModel) {
        this(null, false, listModel);
    }


    public RefreshListModelActionGroup(String shortName, boolean popup, RefreshableListModel listModel) {
        super(shortName, popup);
        this.listModel = listModel;
    }


    public void update(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {
            WorkspaceManager workspaceManager = project.getService(WorkspaceManager.class);
            workspaceManager.updateImplicitAutoPinningAndUnpinning();
            this.listModel.refreshAll();
        }
    }
}
