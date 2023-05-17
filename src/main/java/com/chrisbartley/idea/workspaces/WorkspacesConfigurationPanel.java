package com.chrisbartley.idea.workspaces;

import com.chrisbartley.swing.event.MasterAndSlaveComponentsItemListener;

import javax.swing.*;
import java.awt.*;


final class WorkspacesConfigurationPanel extends JPanel {
    private final JCheckBox isDisplayMainMenuUI = new JCheckBox("Workspaces Menu");
    private final JLabel pinAnUnpinnedOpenWorkspaceSelectedFromMenuLabel = new JLabel("Selecting an unpinned, open (i.e. marked with a black check) workspace should...");
    private final JRadioButton isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue = new JRadioButton("Pin the workspace");
    private final JRadioButton isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse = new JRadioButton("Close the workspace");
    private final Component[] isDisplayMainMenuUIControlledComponents = new Component[]{this.pinAnUnpinnedOpenWorkspaceSelectedFromMenuLabel, this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue, this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse};

    private final JCheckBox isDisplayToolWindowUI = new JCheckBox("Workspaces Tool Window");
    private final JCheckBox isDisplayButtonActionsInContextMenu = new JCheckBox("Include the Open, Close, Toggle Pin, Remove, and Properties actions in the context menu (effective upon restart)");
    private final Component[] isDisplayToolWindowUIControlledComponents = new Component[]{this.isDisplayButtonActionsInContextMenu};

    private final JCheckBox isAutoPin = new JCheckBox("Automatically pin workspaces when...");
    private final JRadioButton isAutoPinUponExplicitOpenOnlyTrue = new JRadioButton("Explicitly opened");
    private final JRadioButton isAutoPinUponExplicitOpenOnlyFalse = new JRadioButton("Implicitly or explicitly opened");
    private final JCheckBox isAutoPinUponCreateWorkspace = new JCheckBox("Created");
    private final Component[] isAutoPinControlledComponents = new Component[]{this.isAutoPinUponExplicitOpenOnlyTrue, this.isAutoPinUponExplicitOpenOnlyFalse, this.isAutoPinUponCreateWorkspace};

    private final JCheckBox isAutoUnpin = new JCheckBox("Automatically unpin workspaces when...");
    private final JRadioButton isAutoUnpinUponExplicitCloseOnlyTrue = new JRadioButton("Explicitly closed");
    private final JRadioButton isAutoUnpinUponExplicitCloseOnlyFalse = new JRadioButton("Implicitly or explicitly closed");
    private final Component[] isAutoUnpinControlledComponents = new Component[]{this.isAutoUnpinUponExplicitCloseOnlyTrue, this.isAutoUnpinUponExplicitCloseOnlyFalse};

    public WorkspacesConfigurationPanel() {
        super(new BorderLayout());
        createPanel();
    }


    private void createPanel() {
        this.isDisplayMainMenuUI.addItemListener(new MasterAndSlaveComponentsItemListener(this.isDisplayMainMenuUI, this.isDisplayMainMenuUIControlledComponents));
        ButtonGroup isPinAnUnpinnedOpenWorkspaceSelectedFromMenuGroup = new ButtonGroup();
        isPinAnUnpinnedOpenWorkspaceSelectedFromMenuGroup.add(this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue);
        isPinAnUnpinnedOpenWorkspaceSelectedFromMenuGroup.add(this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse);


        this.isDisplayToolWindowUI.addItemListener(new MasterAndSlaveComponentsItemListener(this.isDisplayToolWindowUI, this.isDisplayToolWindowUIControlledComponents));


        this.isAutoPin.addItemListener(new MasterAndSlaveComponentsItemListener(this.isAutoPin, this.isAutoPinControlledComponents));
        ButtonGroup autoPinningButtonGroup = new ButtonGroup();
        autoPinningButtonGroup.add(this.isAutoPinUponExplicitOpenOnlyTrue);
        autoPinningButtonGroup.add(this.isAutoPinUponExplicitOpenOnlyFalse);


        this.isAutoUnpin.addItemListener(new MasterAndSlaveComponentsItemListener(this.isAutoUnpin, this.isAutoUnpinControlledComponents));
        ButtonGroup autoUnpinningButtonGroup = new ButtonGroup();
        autoUnpinningButtonGroup.add(this.isAutoUnpinUponExplicitCloseOnlyTrue);
        autoUnpinningButtonGroup.add(this.isAutoUnpinUponExplicitCloseOnlyFalse);


        JPanel uiOptionsSubPanel1 = createCheckBoxControlledComponentsPanel(this.isDisplayMainMenuUI, this.isDisplayMainMenuUIControlledComponents);
        JPanel uiOptionsSubPanel2 = createCheckBoxControlledComponentsPanel(this.isDisplayToolWindowUI, this.isDisplayToolWindowUIControlledComponents);
        JPanel uiOptionsPanel = createTitledBorderedPanel("UI Options", new JPanel[]{uiOptionsSubPanel1, uiOptionsSubPanel2});


        JPanel autoPinningUnpinningSubPanel1 = createCheckBoxControlledComponentsPanel(this.isAutoPin, this.isAutoPinControlledComponents);
        JPanel autoPinningUnpinningSubPanel2 = createCheckBoxControlledComponentsPanel(this.isAutoUnpin, this.isAutoUnpinControlledComponents);
        JPanel autoPinningUnpinningPanel = createTitledBorderedPanel("Automatic Pinning/Unpinning", new JPanel[]{autoPinningUnpinningSubPanel1, autoPinningUnpinningSubPanel2});


        JPanel workspacesOptionsPanel = new JPanel();
        workspacesOptionsPanel.setLayout(new BoxLayout(workspacesOptionsPanel, BoxLayout.Y_AXIS));
        workspacesOptionsPanel.add(uiOptionsPanel);
        workspacesOptionsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        workspacesOptionsPanel.add(autoPinningUnpinningPanel);
        workspacesOptionsPanel.setAlignmentX(0.0F);

        add(workspacesOptionsPanel, "North");
    }


    private JPanel createTitledBorderedPanel(String title, JPanel[] subPanels) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setAlignmentX(0.0F);
        for (JPanel subPanel : subPanels) {
            panel.add(subPanel);
        }
        return panel;
    }


    private JPanel createCheckBoxControlledComponentsPanel(JCheckBox checkBox, Component[] controlledComponents) {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        for (Component controlledComponent : controlledComponents) {
            optionsPanel.add(controlledComponent);
        }
        optionsPanel.setAlignmentX(0.0F);
        JPanel indentedOptionsPanel = new JPanel();
        indentedOptionsPanel.setLayout(new BoxLayout(indentedOptionsPanel, BoxLayout.X_AXIS));
        indentedOptionsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        indentedOptionsPanel.add(optionsPanel);
        indentedOptionsPanel.setAlignmentX(0.0F);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(checkBox);
        panel.add(indentedOptionsPanel);
        panel.setAlignmentX(0.0F);
        return panel;
    }


    public void copyConfigurationTo(WorkspacesConfiguration configuration) {
        WorkspacesConfiguration.State state = configuration.getState();
        state.setDisplayMainMenuUI(isDisplayMainMenuUI());
        state.setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(isPinAnUnpinnedOpenWorkspaceSelectedFromMenu());


        state.setDisplayToolWindowUI(isDisplayToolWindowUI());
        state.setDisplayButtonActionsInContextMenu(isDisplayButtonActionsInContextMenu());


        state.setAutoPin(isAutoPin());
        state.setAutoPinUponExplicitOpenOnly(isAutoPinUponExplicitOpenOnly());
        state.setAutoPinUponCreateWorkspace(isAutoPinUponCreateWorkspace());
        state.setAutoUnpin(isAutoUnpin());
        state.setAutoUnpinUponExplicitCloseOnly(isAutoUnpinUponExplicitCloseOnly());
    }


    public void copyConfigurationFrom(WorkspacesConfiguration configuration) {
        final WorkspacesConfiguration.State state = configuration.getState();
        setDisplayMainMenuUI(state.isDisplayMainMenuUI());
        setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(state.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu());
        setDisplayToolWindowUI(state.isDisplayToolWindowUI());
        setDisplayButtonActionsInContextMenu(state.isDisplayButtonActionsInContextMenu());
        setAutoPin(state.isAutoPin());
        setAutoPinUponExplicitOpenOnly(state.isAutoPinUponExplicitOpenOnly());
        setAutoPinUponCreateWorkspace(state.isAutoPinUponCreateWorkspace());
        setAutoUnpin(state.isAutoUnpin());
        setAutoUnpinUponExplicitCloseOnly(state.isAutoUnpinUponExplicitCloseOnly());
    }


    private boolean isDisplayMainMenuUI() {
        return this.isDisplayMainMenuUI.isSelected();
    }


    private void setDisplayMainMenuUI(boolean isDisplayMainMenuUI) {
        this.isDisplayMainMenuUI.setSelected(isDisplayMainMenuUI);
        this.pinAnUnpinnedOpenWorkspaceSelectedFromMenuLabel.setEnabled(isDisplayMainMenuUI);
        this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.setEnabled(isDisplayMainMenuUI);
        this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.setEnabled(isDisplayMainMenuUI);
    }


    private boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu() {
        return this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.isSelected();
    }


    private void setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu) {
        this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.setSelected(isPinAnUnpinnedOpenWorkspaceSelectedFromMenu);
        this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse.setSelected(!isPinAnUnpinnedOpenWorkspaceSelectedFromMenu);
    }


    private boolean isDisplayToolWindowUI() {
        return this.isDisplayToolWindowUI.isSelected();
    }


    private void setDisplayToolWindowUI(boolean isDisplayToolWindowUI) {
        this.isDisplayToolWindowUI.setSelected(isDisplayToolWindowUI);
        this.isDisplayButtonActionsInContextMenu.setEnabled(isDisplayToolWindowUI);
    }


    private boolean isDisplayButtonActionsInContextMenu() {
        return this.isDisplayButtonActionsInContextMenu.isSelected();
    }


    private void setDisplayButtonActionsInContextMenu(boolean isDisplayButtonActionsInContextMenu) {
        this.isDisplayButtonActionsInContextMenu.setSelected(isDisplayButtonActionsInContextMenu);
    }


    private boolean isAutoPin() {
        return this.isAutoPin.isSelected();
    }


    private void setAutoPin(boolean autoPin) {
        this.isAutoPin.setSelected(autoPin);
        this.isAutoPinUponExplicitOpenOnlyTrue.setEnabled(autoPin);
        this.isAutoPinUponExplicitOpenOnlyFalse.setEnabled(autoPin);
        this.isAutoPinUponCreateWorkspace.setEnabled(autoPin);
    }


    private boolean isAutoPinUponExplicitOpenOnly() {
        return this.isAutoPinUponExplicitOpenOnlyTrue.isSelected();
    }


    private void setAutoPinUponExplicitOpenOnly(boolean autoPinUponExplicitOpenOnly) {
        this.isAutoPinUponExplicitOpenOnlyTrue.setSelected(autoPinUponExplicitOpenOnly);
        this.isAutoPinUponExplicitOpenOnlyFalse.setSelected(!autoPinUponExplicitOpenOnly);
    }


    private boolean isAutoPinUponCreateWorkspace() {
        return this.isAutoPinUponCreateWorkspace.isSelected();
    }


    private void setAutoPinUponCreateWorkspace(boolean autoPinUponCreateWorkspace) {
        this.isAutoPinUponCreateWorkspace.setSelected(autoPinUponCreateWorkspace);
    }


    private boolean isAutoUnpin() {
        return this.isAutoUnpin.isSelected();
    }


    private void setAutoUnpin(boolean autoUnpin) {
        this.isAutoUnpin.setSelected(autoUnpin);
        this.isAutoUnpinUponExplicitCloseOnlyTrue.setEnabled(autoUnpin);
        this.isAutoUnpinUponExplicitCloseOnlyFalse.setEnabled(autoUnpin);
    }


    private boolean isAutoUnpinUponExplicitCloseOnly() {
        return this.isAutoUnpinUponExplicitCloseOnlyTrue.isSelected();
    }


    private void setAutoUnpinUponExplicitCloseOnly(boolean autoUnpinUponExplicitCloseOnly) {
        this.isAutoUnpinUponExplicitCloseOnlyTrue.setSelected(autoUnpinUponExplicitCloseOnly);
        this.isAutoUnpinUponExplicitCloseOnlyFalse.setSelected(!autoUnpinUponExplicitCloseOnly);
    }

}

