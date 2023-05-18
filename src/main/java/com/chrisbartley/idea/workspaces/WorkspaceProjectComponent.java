package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.actions.MutableActionGroup;
import com.chrisbartley.idea.actions.RegisterableAction;
import com.chrisbartley.idea.workspaces.actions.*;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkspaceProjectComponent implements ProjectComponent {
    private static final Map<Project, DefaultActionGroup> PROJECT_WORKSPACES_MENUS = new HashMap<>();
    private static final Map<Project, DefaultActionGroup> PROJECT_POPUP_MENUS = new HashMap<>();
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

    private final MutableActionGroup appendToWorkspaceActionGroup = new MutableActionGroup(new AppendFileToWorkspaceActionGroup(), "Append", false);
    private final DefaultActionGroup appendToWorkspaceMenu = new AppendActionGroup();


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
        registerAppendFileMenu();
    }

    @Override
    public void projectClosed() {
        PROJECT_WORKSPACES_MENUS.remove(this.project);
        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("MainMenu");
        mainMenu.remove(this.workspacesMenu);
        this.createWorkspaceAction.unregister();
        this.closeAllWorkspacesAction.unregister();
        this.closeAllNonWorkspaceFilesAction.unregister();
        this.toggleWorkspaceOpennessActionGroup.removeAll();
        this.closeAllWorkspacesExceptThisActionGroup.removeAll();
        this.togglePinActionGroup.removeAll();
        this.configureActionGroup.removeAll();
        this.removeActionGroup.removeAll();
        this.workspacesMenu.removeAll();
        unRegisterAppendFileMenu();
    }


    private void showHideWorkspacesMenu() {
        DefaultActionGroup mainMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("MainMenu");
        mainMenu.remove(this.workspacesMenu);
        mainMenu.add(this.workspacesMenu, WORKSPACES_MENU_PLACEMENT);
        PROJECT_WORKSPACES_MENUS.put(this.project, this.workspacesMenu);
    }

    private void registerAppendFileMenu() {
        appendToWorkspaceMenu.add(this.appendToWorkspaceActionGroup);
        DefaultActionGroup editorTabPopupMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("EditorTabPopupMenu");
        DefaultActionGroup projectViewPopupMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("ProjectViewPopupMenu");
        editorTabPopupMenu.remove(this.appendToWorkspaceMenu);
        editorTabPopupMenu.add(this.appendToWorkspaceMenu);
        projectViewPopupMenu.remove(this.appendToWorkspaceMenu);
        projectViewPopupMenu.add(this.appendToWorkspaceMenu);
        PROJECT_POPUP_MENUS.put(this.project, this.appendToWorkspaceMenu);
    }

    private void unRegisterAppendFileMenu() {
        DefaultActionGroup editorTabPopupMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("EditorTabPopupMenu");
        DefaultActionGroup projectViewPopupMenu = (DefaultActionGroup) ActionManager.getInstance().getAction("ProjectViewPopupMenu");
        editorTabPopupMenu.remove(this.appendToWorkspaceMenu);
        projectViewPopupMenu.remove(this.appendToWorkspaceMenu);
        PROJECT_POPUP_MENUS.remove(this.project);
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

    /**
     * append file to workspace action group: list all workspaces
     */
    private static final class AppendFileToWorkspaceActionGroup extends WorkspaceMutableActionGroupStrategy {
        private AppendFileToWorkspaceActionGroup() {
        }

        public List<RegisterableAction> getActions(AnActionEvent event) {
            return getWorkspaceManager(event).getAppendWorkspaceActions();
        }
    }
}
