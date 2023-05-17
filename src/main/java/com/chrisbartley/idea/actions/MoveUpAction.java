package com.chrisbartley.idea.actions;

import com.chrisbartley.idea.util.Icons;
import com.chrisbartley.idea.util.ReorderableListModel;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class MoveUpAction extends AnAction {
    private final JList<?> list;
    private final ReorderableListModel<?> listModel;

    public MoveUpAction(JList<?> list) {
        super("Move Up", "Move the selected item(s) up", Icons.MOVE_UP);
        this.list = list;
        this.listModel = (ReorderableListModel<?>) list.getModel();
    }


    public void actionPerformed(@NotNull AnActionEvent event) {
        ListSelectionModel selectionModel = this.list.getSelectionModel();
        if (!selectionModel.isSelectionEmpty()) {
            int[] selectedIndices = this.list.getSelectedIndices();
            this.listModel.shiftElements(selectedIndices, false);
            for (int i = 0; i < selectedIndices.length; i++) {
                selectedIndices[i] = selectedIndices[i] - 1;
            }
            this.list.setSelectedIndices(selectedIndices);
            this.list.ensureIndexIsVisible(selectedIndices[0]);
        }
    }


    public void update(AnActionEvent event) {
        event.getPresentation().setEnabled((this.list.getSelectionModel().getMinSelectionIndex() > 0));
    }
}
