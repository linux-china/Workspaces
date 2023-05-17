package com.chrisbartley.idea.workspaces;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class WorkspaceToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final WorkspaceStateService workspaceService = project.getService(WorkspaceStateService.class);
        final JBList<Workspace> workspaceList = workspaceService.getWorkspaceJBList(project);
        final WorkspacesConfiguration workspacesConfiguration = ApplicationManager.getApplication().getService(WorkspacesConfiguration.class);
        var workspaceWindowPanel = new WorkspacesToolWindow(project, workspacesConfiguration, workspaceList);
        var contentFactory = ContentFactory.SERVICE.getInstance();
        var content = contentFactory.createContent(workspaceWindowPanel, "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
