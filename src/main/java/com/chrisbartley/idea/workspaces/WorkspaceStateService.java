package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.util.ReorderableListModel;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@State(name = "Workspaces", storages = {@Storage(value = "workspaces.xml")})
public class WorkspaceStateService implements PersistentStateComponent<WorkspaceStateService.State> {

    public static class State {
        public List<Workspace> workspaces = new ArrayList<>();

        public List<Workspace> getWorkspaces() {
            return workspaces;
        }

        public void setWorkspaces(List<Workspace> workspaces) {
            this.workspaces = workspaces;
        }
    }

    private State myState = new State();
    private ReorderableListModel<Workspace> workspacesModel;
    JBList<Workspace> workspaceJBList;

    public @NotNull State getState() {
        return myState;
    }

    public void loadState(@NotNull State state) {
        myState = state;
    }

    public @NotNull ReorderableListModel<Workspace> getWorkspacesModel() {
        if (workspacesModel == null) {
            workspacesModel = new ReorderableListModel<>(myState.workspaces);
        }
        return workspacesModel;
    }

    public @NotNull JBList<Workspace> getWorkspaceJBList(Project project) {
        if (workspaceJBList == null) {
            workspaceJBList = new JBList<>();
            workspaceJBList.setModel(getWorkspacesModel());
            workspaceJBList.setSelectionMode(2);
            workspaceJBList.setCellRenderer(new WorkspacesToolWindowListCellRenderer(project));
        }
        return workspaceJBList;
    }
}
