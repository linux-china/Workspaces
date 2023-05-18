package com.chrisbartley.idea.workspaces;

import com.chrisbartley.idea.util.VirtualFileUtils;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class Workspace {
    public String name;
    public List<String> fileUrls = new ArrayList<>();
    public boolean isPinned;

    public Workspace() {
    }

    public Workspace(String name, List<String> fileUrls, boolean isPinned) {
        this.name = name;
        this.fileUrls.addAll(fileUrls);
        this.isPinned = isPinned;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getFileUrls() {
        return this.fileUrls;
    }


    public boolean isPinned() {
        return this.isPinned;
    }


    public void setPinned(boolean pinned) {
        this.isPinned = pinned;
    }


    public void addFileUrl(@NotNull String fileUrl) {
        if (!fileUrl.isEmpty() && !this.fileUrls.contains(fileUrl)) {
            this.fileUrls.add(fileUrl);
        }
    }

    public void addFileUrls(List<String> fileUrls) {
        for (String fileUrl : fileUrls) {
            addFileUrl(fileUrl);
        }
    }

    public void update(String newName, List<String> newUrls) {
        if (newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
        if (newUrls != null && !newUrls.isEmpty()) {
            this.fileUrls.clear();
            this.fileUrls.addAll(newUrls);
        }
    }

    public void open(FileEditorManager fileEditorManager, boolean selectFirstFile, boolean willAutoPin) {
        if (willAutoPin) {
            this.isPinned = true;
        }
        if (!this.fileUrls.isEmpty()) {
            VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
            for (String fileUrl : this.fileUrls) {
                openFile(fileUrl, virtualFileManager, fileEditorManager);
            }
            if (selectFirstFile) {
                openFile(this.fileUrls.get(0), virtualFileManager, fileEditorManager);
            }
        }
    }


    private void openFile(String url, VirtualFileManager virtualFileManager, FileEditorManager fileEditorManager) {
        VirtualFile virtualFile = virtualFileManager.findFileByUrl(url);
        if (virtualFile != null) {
            fileEditorManager.openFile(virtualFile, false);
        }
    }

    public void close(FileEditorManager fileEditorManager, Set<String> urlsNotToBeClosed, boolean willAutoUnpin) {
        if (willAutoUnpin) {
            this.isPinned = false;
        }
        for (String url : this.fileUrls) {
            if (urlsNotToBeClosed == null || !urlsNotToBeClosed.contains(url)) {
                VirtualFileUtils.closeFileByUrl(fileEditorManager, url);
            }
        }
    }

    public boolean contains(VirtualFile[] filesToCheck) {
        if (filesToCheck != null && filesToCheck.length != 0) {
            Set<String> fileUrlsSet = new HashSet<>(this.fileUrls);
            for (VirtualFile virtualFile : filesToCheck) {
                if (fileUrlsSet.contains(virtualFile.getUrl())) {
                    return true;
                }
            }
        }
        return false;
    }


    public WorkspaceState getState(FileEditorManager fileEditorManager) {
        return new WorkspaceState(getOpenFileUrls(fileEditorManager), this.fileUrls.size(), this.isPinned);
    }


    private Set<String> getOpenFileUrls(FileEditorManager fileEditorManager) {
        Set<String> boundUrls = new HashSet<>();
        VirtualFile[] openFiles = fileEditorManager.getOpenFiles();
        if (openFiles.length > 0) {
            boundUrls.addAll(VirtualFileUtils.getUrls(openFiles));
            boundUrls.retainAll(this.fileUrls);
        }
        return boundUrls;
    }


    public String toString() {
        return getName();
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Workspace)) {
            return false;
        }

        Workspace workspace = (Workspace) o;

        if (this.isPinned != workspace.isPinned) {
            return false;
        }
        if (!Objects.equals(this.fileUrls, workspace.fileUrls)) {
            return false;
        }
        if (!Objects.equals(this.name, workspace.name)) {
            return false;
        }

        return true;
    }


    public int hashCode() {
        int result = (this.name != null) ? this.name.hashCode() : 0;
        result = 29 * result + ((this.fileUrls != null) ? this.fileUrls.hashCode() : 0);
        result = 29 * result + (this.isPinned ? 1 : 0);
        return result;
    }
}
