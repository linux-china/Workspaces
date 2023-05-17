package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.actions.MutableActionGroup;
import com.chrisbartley.idea.actions.RegisterableAction;
import com.chrisbartley.idea.workspaces.actions.CloseAllNonWorkspaceFilesAction;
import com.chrisbartley.idea.workspaces.actions.CloseAllWorkspacesAction;
import com.chrisbartley.idea.workspaces.actions.CreateWorkspaceAction;
import com.chrisbartley.idea.workspaces.actions.WorkspaceMutableActionGroupStrategy;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;

import java.util.List;

public class WorkspaceProjectComponent implements ProjectComponent {
    private static final Constraints WORKSPACES_MENU_PLACEMENT = new Constraints(Anchor.BEFORE, "HelpMenu");
    private final DefaultActionGroup workspacesMenu = new DefaultActionGroup("Wor_kspaces", false);
    private final MutableActionGroup toggleWorkspaceOpennessActionGroup = new MutableActionGroup(new ToggleWorkspaceOpennessActionGroup());
    private final MutableActionGroup closeAllWorkspacesExceptThisActionGroup = new MutableActionGroup(new CloseAllWorkspacesExceptThisActionGroup(), "Close All Workspaces Except", true);

    private final MutableActionGroup togglePinActionGroup = new MutableActionGroup(new ToggleWorkspacePinActionGroup(), "Toggle Pin", true, Icons.PINNED);
    private final MutableActionGroup configureActionGroup = new MutableActionGroup(new ConfigureWorkspaceActionGroup(), "Properties", true, Icons.CONFIGURE_WORKSPACE);
    private final MutableActionGroup removeActionGroup = new MutableActionGroup(new RemoveWorkspaceActionGroup(), "Remove", true);

    private final RegisterableAction createWorkspaceAction = new CreateWorkspaceAction();

    private final RegisterableAction closeAllWorkspacesAction = new CloseAllWorkspacesAction();
    private final RegisterableAction closeAllNonWorkspaceFilesAction = new CloseAllNonWorkspaceFilesAction();
    private final Project project;

    public WorkspaceProjectComponent(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {

        this.workspacesMenu.add(this.toggleWorkspaceOpennessActionGroup);
        this.workspacesMenu.addSeparator();
        this.workspacesMenu.add(this.closeAllWorkspacesExceptThisActionGroup);
        this.workspacesMenu.add(this.closeAllWorkspacesAction);
        this.workspacesMenu.add(this.closeAllNonWorkspaceFilesAction);
        this.workspacesMenu.addSeparator();
        this.workspacesMenu.add(this.togglePinActionGroup);
        this.workspacesMenu.add(this.configureActionGroup);
        this.workspacesMenu.addSeparator();
        this.workspacesMenu.add(this.removeActionGroup);
        this.workspacesMenu.add(this.createWorkspaceAction);


        this.createWorkspaceAction.register();
        this.closeAllWorkspacesAction.register();
        this.closeAllNonWorkspaceFilesAction.register();

        final WorkspacesConfiguration workspacesConfiguration = ApplicationManager.getApplication().getService(WorkspacesConfiguration.class);
        if (workspacesConfiguration.getState().isDisplayMainMenuUI()) {
            showHideWorkspacesMenu();
        }
        if (workspacesConfiguration.getState().isDisplayToolWindowUI()) {
            showHideToolWindow();
        }
    }

    @Override
    public void projectClosed() {
        this.createWorkspaceAction.unregister();
        this.closeAllWorkspacesAction.unregister();
        this.closeAllNonWorkspaceFilesAction.unregister();
        this.toggleWorkspaceOpennessActionGroup.removeAll();
        this.closeAllWorkspacesExceptThisActionGroup.removeAll();
        this.togglePinActionGroup.removeAll();
        this.configureActionGroup.removeAll();
        this.removeActionGroup.removeAll();
        this.workspacesMenu.removeAll();
    }


    private void showHideWorkspacesMenu() {
        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("MainMenu");
        mainMenu.remove(this.workspacesMenu);
        mainMenu.add(this.workspacesMenu, WORKSPACES_MENU_PLACEMENT);
    }


    private void showHideToolWindow() {
        var toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Workspaces");
        if (toolWindow != null) {
            toolWindow.show();
        }
    }


    public RegisterableAction getCloseAllWorkspacesAction() {
        return this.closeAllWorkspacesAction;
    }


    public RegisterableAction getCloseAllNonWorkspaceFilesAction() {
        return this.closeAllNonWorkspaceFilesAction;
    }

    private static final class ToggleWorkspaceOpennessActionGroup extends WorkspaceMutableActionGroupStrategy {
        private ToggleWorkspaceOpennessActionGroup() {
        }

        public List<RegisterableAction> getActions(AnActionEvent event) {
            return getWorkspaceManager(event).getToggleWorkspaceOpennessActions();
        }
    }

    private static final class CloseAllWorkspacesExceptThisActionGroup extends WorkspaceMutableActionGroupStrategy {
        private CloseAllWorkspacesExceptThisActionGroup() {
        }

        public List<RegisterableAction> getActions(AnActionEvent event) {
            return getWorkspaceManager(event).getCloseAllWorkspacesExceptThisActions();
        }
    }

    private static final class ToggleWorkspacePinActionGroup extends WorkspaceMutableActionGroupStrategy {
        private ToggleWorkspacePinActionGroup() {
        }

        public List<RegisterableAction> getActions(AnActionEvent event) {
            return getWorkspaceManager(event).getToggleWorkspacePinActions();
        }
    }

    private static final class ConfigureWorkspaceActionGroup extends WorkspaceMutableActionGroupStrategy {
        private ConfigureWorkspaceActionGroup() {
        }

        public List<RegisterableAction> getActions(AnActionEvent event) {
            return getWorkspaceManager(event).getConfigureWorkspaceActions();
        }
    }

    private static final class RemoveWorkspaceActionGroup extends WorkspaceMutableActionGroupStrategy {
        private RemoveWorkspaceActionGroup() {
        }

        public List<RegisterableAction> getActions(AnActionEvent event) {
            return getWorkspaceManager(event).getRemoveWorkspaceActions();
        }
    }
}
