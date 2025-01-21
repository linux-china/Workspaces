package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.VirtualFileUtils;
import com.chrisbartley.idea.workspaces.Icons;
import com.chrisbartley.idea.workspaces.WorkspaceManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.Set;


public final class CreateWorkspaceAction
        extends BaseWorkspaceAction {
    public CreateWorkspaceAction() {
        super("Create New from Open File(s)...", "Create a new workspace from some or all of the currently open files", Icons.ADD_WORKSPACE);
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project != null) {
            WorkspaceManager workspaceManager = getWorkspaceManager(project);
            Set<String> boundFileUrls = workspaceManager.getBoundFileUrls();
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);

            CreateWorkspaceDialog dialog = new CreateWorkspaceDialog("Create a New Workspace", "Create", boundFileUrls, VirtualFileUtils.getUrls(fileEditorManager.getOpenFiles()));
            dialog.pack();
            dialog.show();
            if (dialog.getExitCode() == 0) {
                workspaceManager.createWorkspace(dialog.getNewWorkspaceName(), dialog.getNewWorkspaceUrls());
            }
        }
    }


    public void update(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        Presentation presentation = event.getPresentation();
        if (project != null) {
            String text, description;
            VirtualFile[] openFiles = FileEditorManager.getInstance(project).getOpenFiles();
            switch (openFiles.length) {


                case 0:
                    text = "Create New from Open File(s)...";
                    description = "Create a new workspace from some or all of the currently open files";
                    break;


                case 1:
                    text = "Create New from Open File...";
                    description = "Create a new workspace from the currently open file";
                    break;


                default:
                    text = "Create New from Open Files...";
                    description = "Create a new workspace from some or all of the currently open files";
                    break;
            }

            presentation.setText(text);
            presentation.setDescription(description);
            presentation.setEnabled(openFiles.length > 0);
        } else {
            presentation.setText("Create New from Open File(s)...");
            presentation.setDescription("Create a new workspace from some or all of the currently open files");
            presentation.setEnabled(false);
        }


        if ("WORKSPACES_TOOL_WINDOW".equals(event.getPlace())) {
            presentation.setIcon(Icons.ADD_WORKSPACE);
        }
    }
}

