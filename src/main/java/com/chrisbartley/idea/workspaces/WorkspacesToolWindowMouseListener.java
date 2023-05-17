package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.util.WrappedListModel;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;


final class WorkspacesToolWindowMouseListener extends MouseInputAdapter {
    private final JList<Workspace> list;
    private final Project project;
    private final JPopupMenu jPopupMenu;

    public WorkspacesToolWindowMouseListener(JList<Workspace> list, Project project, JPopupMenu jPopupMenu) {
        this.list = list;
        this.project = project;
        this.jPopupMenu = jPopupMenu;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            int indexOfClickedItem = this.list.locationToIndex(e.getPoint());
            if (indexOfClickedItem >= 0 && !this.list.isSelectedIndex(indexOfClickedItem)) {
                this.list.setSelectedIndex(indexOfClickedItem);
            }
            this.jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        } else if (e.getButton() == 1) {
            if (e.getClickCount() == 2) {
                openWorkspace(this.list.locationToIndex(e.getPoint()));
            }
        }
    }

    private void openWorkspace(int index) {
        Workspace workspace = getWorkspaceByIndex(index);
        if (workspace != null) {
            WorkspaceManager workspaceManager = this.project.getService(WorkspaceManager.class);
            workspaceManager.openWorkspace(workspace);
            RefreshableListModel model = (RefreshableListModel) this.list.getModel();
            model.refresh(index);
        }
    }

    private Workspace getWorkspaceByIndex(int index) {
        WrappedListModel<Workspace> model = (WrappedListModel<Workspace>) this.list.getModel();
        return model.isIndexWithinBounds(index) ? model.get(index) : null;
    }
}
