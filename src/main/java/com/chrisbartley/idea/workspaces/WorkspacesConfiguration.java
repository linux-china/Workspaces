package com.chrisbartley.idea.workspaces;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@State(name = "WorkspacesConfiguration", storages = {@Storage(value = "workspaces-configuration.xml")})
public final class WorkspacesConfiguration implements PersistentStateComponent<WorkspacesConfiguration.State> {
    public static class State {
        private boolean isDisplayMainMenuUI = true;
        private boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu = true;
        private boolean isDisplayToolWindowUI = false;
        private boolean isDisplayButtonActionsInContextMenu = true;
        private boolean isAutoPin = true;
        private boolean isAutoPinUponExplicitOpenOnly = true;
        private boolean isAutoPinUponCreateWorkspace = true;
        private boolean isAutoUnpin = true;
        private boolean isAutoUnpinUponExplicitCloseOnly = true;

        public boolean isDisplayMainMenuUI() {
            return isDisplayMainMenuUI;
        }

        public void setDisplayMainMenuUI(boolean displayMainMenuUI) {
            isDisplayMainMenuUI = displayMainMenuUI;
        }

        public boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu() {
            return isPinAnUnpinnedOpenWorkspaceSelectedFromMenu;
        }

        public void setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(boolean pinAnUnpinnedOpenWorkspaceSelectedFromMenu) {
            isPinAnUnpinnedOpenWorkspaceSelectedFromMenu = pinAnUnpinnedOpenWorkspaceSelectedFromMenu;
        }

        public boolean isDisplayToolWindowUI() {
            return isDisplayToolWindowUI;
        }

        public void setDisplayToolWindowUI(boolean displayToolWindowUI) {
            isDisplayToolWindowUI = displayToolWindowUI;
        }

        public boolean isDisplayButtonActionsInContextMenu() {
            return isDisplayButtonActionsInContextMenu;
        }

        public void setDisplayButtonActionsInContextMenu(boolean displayButtonActionsInContextMenu) {
            isDisplayButtonActionsInContextMenu = displayButtonActionsInContextMenu;
        }

        public boolean isAutoPin() {
            return isAutoPin;
        }

        public void setAutoPin(boolean autoPin) {
            isAutoPin = autoPin;
        }

        public boolean isAutoPinUponExplicitOpenOnly() {
            return isAutoPinUponExplicitOpenOnly;
        }

        public void setAutoPinUponExplicitOpenOnly(boolean autoPinUponExplicitOpenOnly) {
            isAutoPinUponExplicitOpenOnly = autoPinUponExplicitOpenOnly;
        }

        public boolean isAutoPinUponCreateWorkspace() {
            return isAutoPinUponCreateWorkspace;
        }

        public void setAutoPinUponCreateWorkspace(boolean autoPinUponCreateWorkspace) {
            isAutoPinUponCreateWorkspace = autoPinUponCreateWorkspace;
        }

        public boolean isAutoUnpin() {
            return isAutoUnpin;
        }

        public void setAutoUnpin(boolean autoUnpin) {
            isAutoUnpin = autoUnpin;
        }

        public boolean isAutoUnpinUponExplicitCloseOnly() {
            return isAutoUnpinUponExplicitCloseOnly;
        }

        public void setAutoUnpinUponExplicitCloseOnly(boolean autoUnpinUponExplicitCloseOnly) {
            isAutoUnpinUponExplicitCloseOnly = autoUnpinUponExplicitCloseOnly;
        }
    }

    private State myState = new State();

    @Override
    public @NotNull State getState() {
        return this.myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }
}
