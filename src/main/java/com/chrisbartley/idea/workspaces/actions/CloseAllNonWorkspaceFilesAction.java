package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.VirtualFileUtils;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public final class CloseAllNonWorkspaceFilesAction
        extends BaseWorkspaceAction {
    public CloseAllNonWorkspaceFilesAction() {
        super("Close All Non-Workspace Files", "Close all files which are not bound to a workspace", (Icon) null);
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {

            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            VirtualFile[] openFiles = fileEditorManager.getOpenFiles();
            if (openFiles.length > 0) {

                Set<String> openNonWorkspacedUrls = getOpenNonWorkspacedUrls(project, openFiles);
                for (String url : openNonWorkspacedUrls) {

                    VirtualFileUtils.closeFileByUrl(fileEditorManager, url);
                }
            }
        }
    }


    public void update(@NotNull AnActionEvent event) {
        Project project = getProject(event);
        if (project != null) {

            VirtualFile[] openFiles = FileEditorManager.getInstance(project).getOpenFiles();
            if (openFiles.length > 0) {
                Set<String> openNonWorkspacedUrls = getOpenNonWorkspacedUrls(project, openFiles);
                event.getPresentation().setEnabled((!openNonWorkspacedUrls.isEmpty()));
            } else {
                event.getPresentation().setEnabled(false);
            }

        } else {

            event.getPresentation().setEnabled(false);
        }
    }


    private Set<String> getOpenNonWorkspacedUrls(Project project, VirtualFile[] openFiles) {
        Set<String> openUrls = new HashSet<>(VirtualFileUtils.getUrls(openFiles));
        openUrls.removeAll(getWorkspaceManager(project).getBoundFileUrls());
        return openUrls;
    }
}

