package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.util.WrappedListModel;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;


final class WorkspacesToolWindowMouseListener extends MouseInputAdapter {
    private final JList<Workspace> jList;
    private final Project project;
    private final JPopupMenu jPopupMenu;

    public WorkspacesToolWindowMouseListener(JList<Workspace> jList, Project project, JPopupMenu jPopupMenu) {
        this.jList = jList;
        this.project = project;
        this.jPopupMenu = jPopupMenu;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            int indexOfClickedItem = this.jList.locationToIndex(e.getPoint());
            if (indexOfClickedItem >= 0 && !this.jList.isSelectedIndex(indexOfClickedItem)) {
                this.jList.setSelectedIndex(indexOfClickedItem);
            }
            this.jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        } else if (e.getButton() == 1) {
            if (e.getClickCount() == 2) {
                openWorkspace(this.jList.locationToIndex(e.getPoint()));
            }
        }
    }

    private void openWorkspace(int index) {
        Workspace workspace = getWorkspaceByIndex(index);
        if (workspace != null) {
            WorkspaceManager workspaceManager = this.project.getService(WorkspaceManager.class);
            workspaceManager.openWorkspace(workspace);
            RefreshableListModel<Workspace> model = (RefreshableListModel<Workspace>) this.jList.getModel();
            model.refresh(index);
        }
    }

    private Workspace getWorkspaceByIndex(int index) {
        WrappedListModel<Workspace> model = (WrappedListModel<Workspace>) this.jList.getModel();
        return model.isIndexWithinBounds(index) ? model.get(index) : null;
    }
}
