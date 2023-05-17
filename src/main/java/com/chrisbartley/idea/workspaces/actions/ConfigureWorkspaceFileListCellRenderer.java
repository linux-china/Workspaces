package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.util.IncludableItem;
import com.chrisbartley.idea.workspaces.Icons;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

import javax.swing.*;
import java.awt.*;


final class ConfigureWorkspaceFileListCellRenderer extends JLabel implements ListCellRenderer<IncludableItem<String>> {
    public ConfigureWorkspaceFileListCellRenderer() {
        setOpaque(true);
        setHorizontalAlignment(2);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends IncludableItem<String>> list, IncludableItem<String> item, int index, boolean isSelected, boolean cellHasFocus) {
        String url = item.getItem();
        VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(url);
        setText(virtualFile.getPresentableUrl());
        setIcon(item.isIncluded() ? Icons.INCLUDED : Icons.EXCLUDED);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}


