package com.chrisbartley.idea.workspaces.actions;

import com.chrisbartley.idea.actions.MoveDownAction;
import com.chrisbartley.idea.actions.MoveUpAction;
import com.chrisbartley.idea.util.IncludableItem;
import com.chrisbartley.idea.util.ReorderableListModel;
import com.chrisbartley.idea.util.WrappedListModel;
import com.chrisbartley.idea.workspaces.Workspace;
import com.chrisbartley.swing.event.ButtonTogglingDocumentListener;
import com.chrisbartley.swing.event.NonEmptyDocumentValidator;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class ConfigureWorkspaceDialog extends DialogWrapper {
    private static final NonEmptyDocumentValidator NON_EMPTY_DOCUMENT_VALIDATOR = new NonEmptyDocumentValidator();

    private final JTextField workspaceNameTextField = new JTextField(20);

    private final WrappedListModel<IncludableItem<String>> listModel;

    public ConfigureWorkspaceDialog(String windowTitle, String okButtonLabel, Workspace oldWorkspace) {
        super(false);
        setTitle(windowTitle);
        setOKButtonText(okButtonLabel);
        this.workspaceNameTextField.setText(oldWorkspace.getName());
        this.workspaceNameTextField.setCaretPosition(0);
        this.workspaceNameTextField.moveCaretPosition((oldWorkspace.getName() == null) ? 0 : oldWorkspace.getName().length());
        this.listModel = new ReorderableListModel<>(createListModel(oldWorkspace.getFileUrls()));
        init();
    }


    private List<IncludableItem<String>> createListModel(List<String> fileUrls) {
        List<IncludableItem<String>> includableItems = new ArrayList<>();
        for (String url : fileUrls) {
            includableItems.add(new IncludableItem<>(url));
        }
        return includableItems;
    }


    protected JComponent createCenterPanel() {
        JList<IncludableItem<String>> fileList = new JList<>(this.listModel);
        fileList.setSelectionMode(2);
        fileList.setCellRenderer(new ConfigureWorkspaceFileListCellRenderer());
        JScrollPane scrollPane = new JBScrollPane(fileList);


        this.workspaceNameTextField.addActionListener(e -> ConfigureWorkspaceDialog.this.doOKAction());
        this.workspaceNameTextField.getDocument().addDocumentListener(new ButtonTogglingDocumentListener(getOKAction(), NON_EMPTY_DOCUMENT_VALIDATOR));
        this.workspaceNameTextField.setAlignmentX(0.0F);

        JPanel topPane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.Y_AXIS));
        topPane.add(new JLabel("Workspace Name:"));
        topPane.add(Box.createRigidArea(new Dimension(0, 5)));
        topPane.add(this.workspaceNameTextField);

        DefaultActionGroup toolbarGroup = new DefaultActionGroup();
        toolbarGroup.add(new IncludeAction(fileList));
        toolbarGroup.add(new ExcludeAction(fileList));
        toolbarGroup.add(new MoveUpAction(fileList));
        toolbarGroup.add(new MoveDownAction(fileList));
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("CONFIGURE_WORKSPACE_DIALOG", toolbarGroup, true);
        toolbar.getComponent().setAlignmentX(0.0F);
        topPane.add(toolbar.getComponent());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(topPane, "North");
        panel.add(scrollPane, "Center");

        return panel;
    }


    protected Action @NotNull [] createActions() {
        getOKAction().putValue("DefaultAction", Boolean.TRUE);
        getCancelAction().putValue("DefaultAction", null);
        return new Action[]{getOKAction(), getCancelAction()};
    }


    public JComponent getPreferredFocusedComponent() {
        return this.workspaceNameTextField;
    }


    public String getNewWorkspaceName() {
        return this.workspaceNameTextField.getText();
    }


    public List<String> getNewWorkspaceUrls() {
        if (!this.listModel.isEmpty()) {
            List<String> selectedUrls = new ArrayList<>();
            for (IncludableItem<String> includableItem : this.listModel) {
                if (includableItem.isIncluded()) {
                    selectedUrls.add(includableItem.getItem());
                }
            }
            return selectedUrls;
        }
        return null;
    }
}

