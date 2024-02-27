package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.IncludableItem;
import com.chrisbartley.idea.util.WrappedListModel;
import com.chrisbartley.idea.workspaces.Icons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;


final class ExcludeWorkspacedAction extends AnAction {
    private final Set<String> boundFileUrls;
    private final WrappedListModel<IncludableItem<String>> listModel;

    public ExcludeWorkspacedAction(Set<String> boundFileUrls, WrappedListModel<IncludableItem<String>> listModel) {
        super("Exclude bound files", "Exclude files that are already bound to a workspace", Icons.EXCLUDE_WORKSPACED);
        this.boundFileUrls = boundFileUrls;
        this.listModel = listModel;
    }

    public void actionPerformed(@NotNull AnActionEvent event) {
        if (this.listModel.getSize() > 0) {
            for (IncludableItem<String> item : this.listModel) {
                if (this.boundFileUrls.contains(item.getItem())) {
                    item.setIncluded(false);
                }
            }
            this.listModel.refreshAll();
        }
    }


    public void update(AnActionEvent event) {
        event.getPresentation().setEnabled((this.listModel.getSize() > 0));
    }

    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }
}

