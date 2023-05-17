package com.chrisbartley.idea.workspaces;

import javax.swing.*;
import java.util.Set;


public final class WorkspaceState {
    private final Set<String> openUrls;
    private final int numOpenFiles;
    private final int numFiles;
    private final boolean isPinned;

    public WorkspaceState(Set<String> openUrls, int numFiles, boolean isPinned) {
        this.openUrls = openUrls;
        this.isPinned = isPinned;
        this.numOpenFiles = openUrls.size();
        this.numFiles = numFiles;
    }


    public Set<String> getOpenUrls() {
        return this.openUrls;
    }


    public boolean isOpen() {
        return (this.numOpenFiles == this.numFiles);
    }


    public boolean isPartiallyOpen() {
        return (this.numOpenFiles > 0 && this.numOpenFiles < this.numFiles);
    }


    public boolean isClosed() {
        return (this.numOpenFiles == 0);
    }


    public Icon getOpenedStatusIcon() {
        if (this.numOpenFiles > 0) {
            return (this.numOpenFiles == this.numFiles) ? Icons.OPENED : Icons.PARTIALLY_OPENED;
        }


        return Icons.UNOPENED;
    }


    public int getNumOpenFiles() {
        return this.numOpenFiles;
    }


    public int getNumFiles() {
        return this.numFiles;
    }


    public boolean isPinned() {
        return this.isPinned;
    }


    public Icon getPinnedStatusIcon() {
        return this.isPinned ? Icons.PINNED : Icons.UNPINNED;
    }


    public Icon getComboStatusIcon() {
        if (this.isPinned) {

            if (this.numOpenFiles > 0) {
                return (this.numOpenFiles == this.numFiles) ? Icons.PINNED_AND_OPEN : Icons.PINNED_AND_PARTIALLY_OPEN;
            }


            return Icons.PINNED;
        }


        return getOpenedStatusIcon();
    }

}

