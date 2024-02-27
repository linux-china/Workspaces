package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.workspaces.Icons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import org.jetbrains.annotations.NotNull;


public class AppendActionGroup extends DefaultActionGroup {
    public AppendActionGroup() {
        super("Append", true);
    }


    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setIcon(Icons.INCLUDE_WORKSPACE_ITEM);
    }

    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }

}
