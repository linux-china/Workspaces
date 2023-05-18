package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.VirtualFileUtils;
import com.chrisbartley.idea.workspaces.Icons;
import com.chrisbartley.idea.workspaces.Workspace;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;


public class AppendFileToWorkspaceAction extends BaseWorkspaceAction {
    private final Workspace workspace;

    public AppendFileToWorkspaceAction(Workspace workspace) {
        super(workspace.name, "Append file to workspace", Icons.WORKSPACES);
        this.workspace = workspace;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setIcon(Icons.WORKSPACES);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        final Project project = event.getData(CommonDataKeys.PROJECT);
        if (project != null) {
            final VirtualFile[] virtualFiles = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);
            if (virtualFiles != null && virtualFiles.length > 0) {
                VirtualFileUtils.getUrls(virtualFiles).forEach(fileUrl -> {
                    if (!workspace.getFileUrls().contains(fileUrl)) {
                        workspace.getFileUrls().add(fileUrl);
                    }
                });
            } else {
                final VirtualFile virtualFile = event.getData(CommonDataKeys.VIRTUAL_FILE);
                if (virtualFile != null) {
                    final String fileUrl = VirtualFileUtils.getUrl(virtualFile);
                    if (!workspace.getFileUrls().contains(fileUrl)) {
                        workspace.getFileUrls().add(fileUrl);
                    }
                }
            }
        }
    }
}
