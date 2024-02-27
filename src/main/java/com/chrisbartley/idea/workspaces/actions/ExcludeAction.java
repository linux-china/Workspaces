package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.IncludableItem;
import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.Icons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;


final class ExcludeAction extends AnAction {
    private final JList<IncludableItem<String>> jList;
    private final RefreshableListModel<IncludableItem<String>> listModel;

    public ExcludeAction(JList<IncludableItem<String>> jList) {
        super("Exclude", "Exclude the selected files(s)", Icons.EXCLUDE_WORKSPACE_ITEM);
        this.jList = jList;
        this.listModel = (RefreshableListModel<IncludableItem<String>>) jList.getModel();
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        if (!this.jList.getSelectionModel().isSelectionEmpty()) {
            List<IncludableItem<String>> selectedItems = this.jList.getSelectedValuesList();
            for (IncludableItem<String> selectedItem : selectedItems) {
                selectedItem.setIncluded(false);
            }
            this.listModel.refresh(this.jList.getSelectedIndices());
        }
    }


    public void update(AnActionEvent event) {
        event.getPresentation().setEnabled(!this.jList.getSelectionModel().isSelectionEmpty());
    }

    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }
}

