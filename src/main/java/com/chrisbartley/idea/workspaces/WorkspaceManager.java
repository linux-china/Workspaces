package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.actions.RegisterableAction;
import com.chrisbartley.idea.util.ReorderableListModel;
import com.chrisbartley.idea.workspaces.actions.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;

import java.util.*;


@Service(Service.Level.PROJECT)
public final class WorkspaceManager {
    private final WorkspacesConfiguration workspacesConfiguration;
    private final Project project;
    private final ReorderableListModel<Workspace> workspacesModel;
    private final List<RegisterableAction> toggleWorkspaceOpennessActions = new ArrayList<>();


    public WorkspaceManager(Project project) {
        this.project = project;
        this.workspacesModel = project.getService(WorkspaceStateService.class).getWorkspacesModel();
        this.workspacesConfiguration = ApplicationManager.getApplication().getService(WorkspacesConfiguration.class);
        buildActionsForMutableActionGroups();
    }


    public void updateImplicitAutoPinningAndUnpinning() {
        boolean isAutoPinImplicitly = (this.workspacesConfiguration.getState().isAutoPin() && !this.workspacesConfiguration.getState().isAutoPinUponExplicitOpenOnly());
        boolean isAutoUnpinImplicitly = (this.workspacesConfiguration.getState().isAutoUnpin() && !this.workspacesConfiguration.getState().isAutoUnpinUponExplicitCloseOnly());
        if (isAutoPinImplicitly || isAutoUnpinImplicitly) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
            for (Workspace workspace : this.workspacesModel) {
                WorkspaceState workspaceState = workspace.getState(fileEditorManager);
                if (isAutoPinImplicitly && workspaceState.isOpen()) {
                    workspace.setPinned(true);
                }
                if (isAutoUnpinImplicitly && workspaceState.isClosed()) {
                    workspace.setPinned(false);
                }
            }
        }
    }


    public Set<String> getBoundFileUrls() {
        Set<String> fileUrls = new HashSet<>();
        for (Workspace workspace : this.workspacesModel) {
            fileUrls.addAll(workspace.getFileUrls());
        }
        return Collections.unmodifiableSet(fileUrls);
    }


    public int getWorkspaceCount() {
        return this.workspacesModel.size();
    }


    public void createWorkspace(String name, List<String> urls) {
        if (name != null && !urls.isEmpty()) {
            this.workspacesModel.add(new Workspace(name, urls, (this.workspacesConfiguration.getState().isAutoPin() && this.workspacesConfiguration.getState().isAutoPinUponCreateWorkspace())));
            buildActionsForMutableActionGroups();
        }
    }


    public void removeWorkspaces(int[] indicesToRemove) {
        JBList<Workspace> workspaceList = project.getService(WorkspaceStateService.class).getWorkspaceJBList(project);
        int[] selectedIndices = workspaceList.getSelectedIndices();
        for (int i = indicesToRemove.length - 1; i >= 0; i--) {
            this.workspacesModel.remove(indicesToRemove[i]);
        }

        buildActionsForMutableActionGroups();

        if (this.workspacesModel.isEmpty()) {

            workspaceList.getSelectionModel().clearSelection();


        } else if (selectedIndices != null && selectedIndices.length > 0) {
            if (Arrays.equals(selectedIndices, indicesToRemove)) {
                int firstSelectedIndex = selectedIndices[0];
                if (firstSelectedIndex == this.workspacesModel.size()) {
                    firstSelectedIndex--;
                }
                workspaceList.setSelectedIndex(firstSelectedIndex);
            } else {
                int[] indicesToRemoveCopy = new int[indicesToRemove.length];
                System.arraycopy(indicesToRemove, 0, indicesToRemoveCopy, 0, indicesToRemove.length);
                Arrays.sort(indicesToRemoveCopy);
                int sizeOfNewSelectedIndicesArray = 0;
                for (int j = 0; j < selectedIndices.length; j++) {

                    int selectedIndex = selectedIndices[j];
                    int pos = Arrays.binarySearch(indicesToRemoveCopy, selectedIndex);
                    if (pos >= 0) {

                        selectedIndices[j] = -1;

                    } else {

                        int numberOfSmallerIndices = -(pos + 1);
                        selectedIndices[j] = selectedIndices[j] - numberOfSmallerIndices;
                        sizeOfNewSelectedIndicesArray++;
                    }
                }
                if (sizeOfNewSelectedIndicesArray > 0) {

                    int[] newSelectedIndeces = new int[sizeOfNewSelectedIndicesArray];
                    int currentPos = 0;
                    for (int selectedIndex : selectedIndices) {
                        if (selectedIndex != -1) {
                            newSelectedIndeces[currentPos++] = selectedIndex;
                        }
                    }
                    workspaceList.setSelectedIndices(newSelectedIndeces);
                }
            }
        }
    }


    private void buildActionsForMutableActionGroups() {
        updateImplicitAutoPinningAndUnpinning();
        unregisterActionsForMutableActionGroups();
        this.toggleWorkspaceOpennessActions.clear();
        for (Workspace workspace : this.workspacesModel) {
            ToggleWorkspaceOpennessAction toggleWorkspaceOpennessAction = new ToggleWorkspaceOpennessAction(this.project, workspace);
            toggleWorkspaceOpennessAction.register();
            this.toggleWorkspaceOpennessActions.add(toggleWorkspaceOpennessAction);
        }
    }


    private void unregisterActionsForMutableActionGroups() {
        unregisterActions(this.toggleWorkspaceOpennessActions);
    }


    private void unregisterActions(List<RegisterableAction> actions) {
        for (RegisterableAction action : actions) {
            action.unregister();
        }
    }


    public void openWorkspace(Workspace workspace) {
        if (workspace != null) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
            workspace.open(fileEditorManager, true, this.workspacesConfiguration.getState().isAutoPin());
        }
    }


    public void openWorkspaces(List<Workspace> workspacesToOpen) {
        if (workspacesToOpen != null && !workspacesToOpen.isEmpty()) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
            int i = 1;
            for (Workspace workspace : workspacesToOpen) {
                workspace.open(fileEditorManager, (i == workspacesToOpen.size()), this.workspacesConfiguration.getState().isAutoPin());
                i++;
            }
        }
    }


    public void toggleWorkspacePinnedness(List<Workspace> workspacesToToggle) {
        if (workspacesToToggle != null && !workspacesToToggle.isEmpty()) {
            for (Workspace workspace : workspacesToToggle) {
                toggleWorkspacePinnedness(workspace);
            }
        }
    }


    public void toggleWorkspacePinnedness(Workspace workspace) {
        if (workspace != null) {
            workspace.setPinned(!workspace.isPinned());
        }
    }


    public void toggleWorkspaceOpenness(Workspace workspace) {
        if (workspace != null) {

            FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
            WorkspaceState workspaceState = workspace.getState(fileEditorManager);
            if (workspaceState.isOpen()) {

                if (workspaceState.isPinned()) {
                    closeWorkspace(workspace);


                } else if (this.workspacesConfiguration.getState().isPinAnUnpinnedOpenWorkspaceSelectedFromMenu()) {
                    workspace.setPinned(true);
                } else {
                    closeWorkspace(workspace);
                }

            } else {

                openWorkspace(workspace);
            }
        }
    }


    public String getToggleWorkspaceOpennessDescription(Workspace workspace) {
        if (workspace != null) {
            String actionStr;
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
            WorkspaceState workspaceState = workspace.getState(fileEditorManager);
            if (workspaceState.isOpen()) {

                if (workspaceState.isPinned()) {
                    actionStr = "Close";


                } else if (this.workspacesConfiguration.getState().isPinAnUnpinnedOpenWorkspaceSelectedFromMenu()) {
                    actionStr = "Pin";
                } else {
                    actionStr = "Close";
                }

            } else {

                actionStr = "Open";
            }
            return actionStr + " the '" + workspace.getName() + "' workspace";
        }
        return null;
    }


    public void closeWorkspace(Workspace workspace) {
        if (workspace != null) {
            closeWorkspaces(Set.of(workspace));
        }
    }

    public void closeWorkspaces(Set<Workspace> workspacesToClose) {
        if (workspacesToClose != null && !workspacesToClose.isEmpty()) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
            Set<String> urlsNotToBeClosed = getUrlsNotToBeClosed(workspacesToClose);
            for (Workspace workspace : workspacesToClose) {
                workspace.close(fileEditorManager, urlsNotToBeClosed, this.workspacesConfiguration.getState().isAutoUnpin());
            }
        }
    }

    public void closeWorkspaces(List<Workspace> workspacesToClose) {
        closeWorkspaces(new HashSet<>(workspacesToClose));
    }

    public void closeAllWorkspaces() {
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
        for (Workspace workspace : this.workspacesModel) {
            workspace.close(fileEditorManager, null, this.workspacesConfiguration.getState().isAutoUnpin());
        }
    }


    public void closeAllButThisWorkspace(Workspace workspace) {
        Set<Workspace> workspacesToBeClosed = new HashSet<>(this.workspacesModel);
        if (workspace != null) {
            workspacesToBeClosed.remove(workspace);
        }
        closeWorkspaces(workspacesToBeClosed);
    }


    public void closeAllButTheseWorkspaces(List<Workspace> workspacesNotToClose) {
        Set<Workspace> workspacesToBeClosed = new HashSet<>(this.workspacesModel);
        if (workspacesNotToClose != null) {
            workspacesToBeClosed.removeAll(workspacesNotToClose);
        }
        closeWorkspaces(workspacesToBeClosed);
    }


    private Set<String> getUrlsNotToBeClosed(Set<Workspace> workspacesToClose) {
        Set<String> urlsNotToBeClosed = new HashSet<>();
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
        for (Workspace workspace : this.workspacesModel) {
            if (!workspacesToClose.contains(workspace)) {
                WorkspaceState workspaceState = workspace.getState(fileEditorManager);
                if (workspaceState.isPinned()) {
                    urlsNotToBeClosed.addAll(workspaceState.getOpenUrls());
                }
            }
        }
        return urlsNotToBeClosed;
    }


    public List<RegisterableAction> getToggleWorkspaceOpennessActions() {
        return this.toggleWorkspaceOpennessActions;
    }


    public List<RegisterableAction> getCloseAllWorkspacesExceptThisActions() {
        List<RegisterableAction> actions = new ArrayList<>();
        for (Workspace workspace : this.workspacesModel) {
            actions.add(new CloseAllWorkspacesExceptThisAction(workspace));
        }
        return actions;
    }


    public List<RegisterableAction> getToggleWorkspacePinActions() {
        List<RegisterableAction> actions = new ArrayList<>();
        for (Workspace workspace : this.workspacesModel) {
            actions.add(new ToggleWorkspacePinAction(workspace));
        }

        return actions;
    }


    public List<RegisterableAction> getConfigureWorkspaceActions() {
        List<RegisterableAction> actions = new ArrayList<>();
        for (Workspace workspace : this.workspacesModel) {
            actions.add(new ConfigureWorkspaceAction(workspace));
        }
        return actions;
    }


    public List<RegisterableAction> getRemoveWorkspaceActions() {
        List<RegisterableAction> actions = new ArrayList<>();
        for (Workspace workspace : this.workspacesModel) {
            actions.add(new RemoveWorkspaceAction(workspace, true));
        }
        return actions;
    }


    public int indexOf(Workspace workspace) {
        if (workspace != null) {
            return this.workspacesModel.indexOf(workspace);
        }
        return -1;
    }
}

