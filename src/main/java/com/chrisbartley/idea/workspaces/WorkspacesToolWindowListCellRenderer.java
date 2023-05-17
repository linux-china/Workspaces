package com.chrisbartley.idea.workspaces;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;


final class WorkspacesToolWindowListCellRenderer extends JPanel implements ListCellRenderer<Workspace> {
    private final FileEditorManager fileEditorManager;
    private final JLabel isPinnedArea = new JLabel();
    private final JLabel isOpenArea = new JLabel();
    private final JLabel nameArea = new JLabel();
    private final JLabel openFilesArea = new JLabel();
    private final JLabel numFilesArea = new JLabel();
    private final JLabel isActiveArea = new JLabel();


    public WorkspacesToolWindowListCellRenderer(Project project) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(0.0F);
        setOpaque(true);
        this.fileEditorManager = FileEditorManager.getInstance(project);
        add(Box.createRigidArea(new Dimension(3, 0)));
        add(this.isPinnedArea);
        add(this.isOpenArea);
        add(this.nameArea);
        add(Box.createGlue());
        add(new JLabel("[ "));
        add(this.openFilesArea);
        add(new JLabel(" / "));
        add(this.numFilesArea);
        add(new JLabel(" ]"));
        add(Box.createRigidArea(new Dimension(3, 0)));
        add(this.isActiveArea);
        add(Box.createRigidArea(new Dimension(3, 0)));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Workspace> list, Workspace workspace, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        } else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }

        WorkspaceState workspaceState = workspace.getState(this.fileEditorManager);

        this.nameArea.setText(workspace.getName());
        this.openFilesArea.setText(String.valueOf(workspaceState.getNumOpenFiles()));
        this.numFilesArea.setText(String.valueOf(workspaceState.getNumFiles()));
        this.isPinnedArea.setIcon(workspaceState.getPinnedStatusIcon());
        this.isOpenArea.setIcon(workspaceState.getOpenedStatusIcon());
        this.isActiveArea.setIcon(workspace.contains(this.fileEditorManager.getSelectedFiles()) ? Icons.ACTIVE_WORKSPACE : Icons.INACTIVE_WORKSPACE);

        return this;
    }

}
