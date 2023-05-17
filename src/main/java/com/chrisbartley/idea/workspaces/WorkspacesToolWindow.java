package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.actions.MoveDownAction;
import com.chrisbartley.idea.actions.MoveUpAction;
import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.actions.*;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.awt.*;


final class WorkspacesToolWindow extends JPanel {
    public WorkspacesToolWindow(Project project, WorkspacesConfiguration workspacesConfiguration, JBList<Workspace> jList) {
        super(new BorderLayout());
        final WorkspaceProjectComponent workspaceProjectComponent = project.getComponent(WorkspaceProjectComponent.class);
        RefreshListModelActionGroup toolbarGroup = new RefreshListModelActionGroup((RefreshableListModel<Workspace>) jList.getModel());
        OpenWorkspaceAction openWorkspaceAction = new OpenWorkspaceAction(jList);
        CloseWorkspaceAction closeWorkspaceAction = new CloseWorkspaceAction(jList);
        ToggleWorkspacePinAction toggleWorkspacePinnedAction = new ToggleWorkspacePinAction(jList);
        ConfigureWorkspaceAction configureWorkspaceAction = new ConfigureWorkspaceAction(jList);
        RemoveWorkspaceAction removeAction = new RemoveWorkspaceAction(jList, true);
        toolbarGroup.add(openWorkspaceAction);
        toolbarGroup.add(closeWorkspaceAction);
        toolbarGroup.add(toggleWorkspacePinnedAction);
        toolbarGroup.add(new CreateWorkspaceAction());
        toolbarGroup.add(removeAction);
        toolbarGroup.add(new MoveUpAction(jList));
        toolbarGroup.add(new MoveDownAction(jList));
        toolbarGroup.add(configureWorkspaceAction);
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("WORKSPACES_TOOL_WINDOW", toolbarGroup, true);


        DefaultActionGroup popupActionGroup = new DefaultActionGroup("WorkspacesPopup", true);
        if (workspacesConfiguration.getState().isDisplayButtonActionsInContextMenu()) {
            popupActionGroup.add(openWorkspaceAction);
            popupActionGroup.add(closeWorkspaceAction);
            popupActionGroup.add(toggleWorkspacePinnedAction);
            popupActionGroup.add(Separator.getInstance());
        }
        CloseAllWorkspacesExceptThisAction closeAllWorkspacesExceptThisAction = new CloseAllWorkspacesExceptThisAction(jList);
        popupActionGroup.add(closeAllWorkspacesExceptThisAction);
        popupActionGroup.add(workspaceProjectComponent.getCloseAllWorkspacesAction());
        popupActionGroup.add(workspaceProjectComponent.getCloseAllNonWorkspaceFilesAction());
        if (workspacesConfiguration.getState().isDisplayButtonActionsInContextMenu()) {
            popupActionGroup.add(Separator.getInstance());
            popupActionGroup.add(removeAction);
            popupActionGroup.add(Separator.getInstance());
            popupActionGroup.add(configureWorkspaceAction);
        }
        ActionPopupMenu popup = ActionManager.getInstance().createActionPopupMenu("WORKSPACES_TOOL_WINDOW_POPUP", popupActionGroup);
        jList.addMouseListener(new WorkspacesToolWindowMouseListener(jList, project, popup.getComponent()));
        add(toolbar.getComponent(), "North");
        add(new JScrollPane(jList), "Center");
    }
}

