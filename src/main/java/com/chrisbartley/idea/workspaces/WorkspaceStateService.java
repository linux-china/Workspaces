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
    private ReorderableListModel workspacesModel;
    JBList<WorkspaceState> workspaceList;

    public @NotNull State getState() {
        return myState;
    }

    public void loadState(@NotNull State state) {
        myState = state;
    }

    public @NotNull ReorderableListModel getWorkspacesModel() {
        if (workspacesModel == null) {
            workspacesModel = new ReorderableListModel(myState.workspaces);
        }
        return workspacesModel;
    }

    public @NotNull JBList<WorkspaceState> getWorkspaceList(Project project) {
        if (workspaceList == null) {
            workspaceList = new JBList<>();
            workspaceList.setModel(getWorkspacesModel());
            workspaceList.setSelectionMode(2);
            workspaceList.setCellRenderer(new WorkspacesToolWindowListCellRenderer(project));
        }
        return workspaceList;
    }
}
