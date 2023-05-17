package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.IncludableItem;
import com.chrisbartley.idea.util.RefreshableListModel;
import com.chrisbartley.idea.workspaces.Icons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


final class IncludeAction extends AnAction {
    private final JList<IncludableItem<String>> jList;
    private final RefreshableListModel<IncludableItem<String>> listModel;

    public IncludeAction(JList<IncludableItem<String>> jList) {
        super("Include", "Include the selected files(s)", Icons.INCLUDE_WORKSPACE_ITEM);
        this.jList = jList;
        this.listModel = (RefreshableListModel<IncludableItem<String>>) jList.getModel();
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        if (!this.jList.getSelectionModel().isSelectionEmpty()) {
            for (IncludableItem<String> selectedItem : this.jList.getSelectedValuesList()) {
                selectedItem.setIncluded(true);
            }
            this.listModel.refresh(this.jList.getSelectedIndices());
        }
    }


    public void update(AnActionEvent event) {
        event.getPresentation().setEnabled(!this.jList.getSelectionModel().isSelectionEmpty());
    }
}

