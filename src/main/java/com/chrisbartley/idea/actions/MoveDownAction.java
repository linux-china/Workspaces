package com.chrisbartley.idea.actions;

import com.chrisbartley.idea.util.Icons;
import com.chrisbartley.idea.util.ReorderableListModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class MoveDownAction extends AnAction {
    private final JList<?> list;
    private final ReorderableListModel<?> listModel;

    public MoveDownAction(JList<?> list) {
        super("Move Down", "Move the selected item(s) down", Icons.MOVE_DOWN);
        this.list = list;
        this.listModel = (ReorderableListModel<?>) list.getModel();
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        ListSelectionModel selectionModel = this.list.getSelectionModel();
        if (!selectionModel.isSelectionEmpty()) {
            int[] selectedIndices = this.list.getSelectedIndices();
            this.listModel.shiftElements(selectedIndices, true);
            for (int i = 0; i < selectedIndices.length; i++) {
                selectedIndices[i] = selectedIndices[i] + 1;
            }
            this.list.setSelectedIndices(selectedIndices);
            this.list.ensureIndexIsVisible(selectedIndices[selectedIndices.length - 1]);
        }
    }


    public void update(AnActionEvent event) {
        ListSelectionModel selectionModel = this.list.getSelectionModel();
        int lastSelectedIndex = selectionModel.getMaxSelectionIndex();
        event.getPresentation().setEnabled((lastSelectedIndex >= 0 && lastSelectedIndex < this.listModel.size() - 1));
    }
}
