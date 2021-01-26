/*     */ package com.chrisbartley.idea.workspaces;
/*     */ 
/*     */ import com.chrisbartley.swing.event.MasterAndSlaveComponentsItemListener;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ItemListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ 
/*     */ 
/*     */ final class WorkspacesConfigurationPanel
/*     */   extends JPanel
/*     */ {
/*  21 */   private final JCheckBox isDisplayMainMenuUI = new JCheckBox("Workspaces Menu");
/*  22 */   private final JLabel pinAnUnpinnedOpenWorkspaceSelectedFromMenuLabel = new JLabel("Selecting an unpinned, open (i.e. marked with a black check) workspace should...");
/*  23 */   private final JRadioButton isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue = new JRadioButton("Pin the workspace");
/*  24 */   private final JRadioButton isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse = new JRadioButton("Close the workspace");
/*  25 */   private final Component[] isDisplayMainMenuUIControlledComponents = new Component[] { this.pinAnUnpinnedOpenWorkspaceSelectedFromMenuLabel, this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue, this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse };
/*     */   
/*  27 */   private final JCheckBox isDisplayToolWindowUI = new JCheckBox("Workspaces Tool Window");
/*  28 */   private final JCheckBox isDisplayButtonActionsInContextMenu = new JCheckBox("Include the Open, Close, Toggle Pin, Remove, and Properties actions in the context menu (effective upon restart)");
/*  29 */   private final Component[] isDisplayToolWindowUIControlledComponents = new Component[] { this.isDisplayButtonActionsInContextMenu };
/*     */   
/*  31 */   private final JCheckBox isAutoPin = new JCheckBox("Automatically pin workspaces when...");
/*  32 */   private final JRadioButton isAutoPinUponExplicitOpenOnlyTrue = new JRadioButton("Explicitly opened");
/*  33 */   private final JRadioButton isAutoPinUponExplicitOpenOnlyFalse = new JRadioButton("Implicitly or explicitly opened");
/*  34 */   private final JCheckBox isAutoPinUponCreateWorkspace = new JCheckBox("Created");
/*  35 */   private final Component[] isAutoPinControlledComponents = new Component[] { this.isAutoPinUponExplicitOpenOnlyTrue, this.isAutoPinUponExplicitOpenOnlyFalse, this.isAutoPinUponCreateWorkspace };
/*     */   
/*  37 */   private final JCheckBox isAutoUnpin = new JCheckBox("Automatically unpin workspaces when...");
/*  38 */   private final JRadioButton isAutoUnpinUponExplicitCloseOnlyTrue = new JRadioButton("Explicitly closed");
/*  39 */   private final JRadioButton isAutoUnpinUponExplicitCloseOnlyFalse = new JRadioButton("Implicitly or explicitly closed");
/*  40 */   private final Component[] isAutoUnpinControlledComponents = new Component[] { this.isAutoUnpinUponExplicitCloseOnlyTrue, this.isAutoUnpinUponExplicitCloseOnlyFalse };
/*     */ 
/*     */   
/*     */   public WorkspacesConfigurationPanel() {
/*  44 */     super(new BorderLayout());
/*  45 */     createPanel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createPanel() {
/*  51 */     this.isDisplayMainMenuUI.addItemListener((ItemListener)new MasterAndSlaveComponentsItemListener(this.isDisplayMainMenuUI, this.isDisplayMainMenuUIControlledComponents));
/*  52 */     ButtonGroup isPinAnUnpinnedOpenWorkspaceSelectedFromMenuGroup = new ButtonGroup();
/*  53 */     isPinAnUnpinnedOpenWorkspaceSelectedFromMenuGroup.add(this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue);
/*  54 */     isPinAnUnpinnedOpenWorkspaceSelectedFromMenuGroup.add(this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse);
/*     */ 
/*     */     
/*  57 */     this.isDisplayToolWindowUI.addItemListener((ItemListener)new MasterAndSlaveComponentsItemListener(this.isDisplayToolWindowUI, this.isDisplayToolWindowUIControlledComponents));
/*     */ 
/*     */     
/*  60 */     this.isAutoPin.addItemListener((ItemListener)new MasterAndSlaveComponentsItemListener(this.isAutoPin, this.isAutoPinControlledComponents));
/*  61 */     ButtonGroup autoPinningButtonGroup = new ButtonGroup();
/*  62 */     autoPinningButtonGroup.add(this.isAutoPinUponExplicitOpenOnlyTrue);
/*  63 */     autoPinningButtonGroup.add(this.isAutoPinUponExplicitOpenOnlyFalse);
/*     */ 
/*     */     
/*  66 */     this.isAutoUnpin.addItemListener((ItemListener)new MasterAndSlaveComponentsItemListener(this.isAutoUnpin, this.isAutoUnpinControlledComponents));
/*  67 */     ButtonGroup autoUnpinningButtonGroup = new ButtonGroup();
/*  68 */     autoUnpinningButtonGroup.add(this.isAutoUnpinUponExplicitCloseOnlyTrue);
/*  69 */     autoUnpinningButtonGroup.add(this.isAutoUnpinUponExplicitCloseOnlyFalse);
/*     */ 
/*     */     
/*  72 */     JPanel uiOptionsSubPanel1 = createCheckBoxControlledComponentsPanel(this.isDisplayMainMenuUI, this.isDisplayMainMenuUIControlledComponents);
/*  73 */     JPanel uiOptionsSubPanel2 = createCheckBoxControlledComponentsPanel(this.isDisplayToolWindowUI, this.isDisplayToolWindowUIControlledComponents);
/*  74 */     JPanel uiOptionsPanel = createTitledBorderedPanel("UI Options", new JPanel[] { uiOptionsSubPanel1, uiOptionsSubPanel2 });
/*     */ 
/*     */     
/*  77 */     JPanel autoPinningUnpinningSubPanel1 = createCheckBoxControlledComponentsPanel(this.isAutoPin, this.isAutoPinControlledComponents);
/*  78 */     JPanel autoPinningUnpinningSubPanel2 = createCheckBoxControlledComponentsPanel(this.isAutoUnpin, this.isAutoUnpinControlledComponents);
/*  79 */     JPanel autoPinningUnpinningPanel = createTitledBorderedPanel("Automatic Pinning/Unpinning", new JPanel[] { autoPinningUnpinningSubPanel1, autoPinningUnpinningSubPanel2 });
/*     */ 
/*     */     
/*  82 */     JPanel workspacesOptionsPanel = new JPanel();
/*  83 */     workspacesOptionsPanel.setLayout(new BoxLayout(workspacesOptionsPanel, 1));
/*  84 */     workspacesOptionsPanel.add(uiOptionsPanel);
/*  85 */     workspacesOptionsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
/*  86 */     workspacesOptionsPanel.add(autoPinningUnpinningPanel);
/*  87 */     workspacesOptionsPanel.setAlignmentX(0.0F);
/*     */     
/*  89 */     add(workspacesOptionsPanel, "North");
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel createTitledBorderedPanel(String title, JPanel[] subPanels) {
/*  94 */     JPanel panel = new JPanel();
/*  95 */     panel.setLayout(new BoxLayout(panel, 1));
/*  96 */     panel.setBorder(BorderFactory.createTitledBorder(title));
/*  97 */     panel.setAlignmentX(0.0F);
/*  98 */     for (int i = 0; i < subPanels.length; i++)
/*     */     {
/* 100 */       panel.add(subPanels[i]);
/*     */     }
/*     */     
/* 103 */     return panel;
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel createCheckBoxControlledComponentsPanel(JCheckBox checkBox, Component[] controlledComponents) {
/* 108 */     JPanel optionsPanel = new JPanel();
/* 109 */     optionsPanel.setLayout(new BoxLayout(optionsPanel, 1));
/* 110 */     for (int i = 0; i < controlledComponents.length; i++)
/*     */     {
/* 112 */       optionsPanel.add(controlledComponents[i]);
/*     */     }
/* 114 */     optionsPanel.setAlignmentX(0.0F);
/*     */     
/* 116 */     JPanel indentedOptionsPanel = new JPanel();
/* 117 */     indentedOptionsPanel.setLayout(new BoxLayout(indentedOptionsPanel, 0));
/* 118 */     indentedOptionsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
/* 119 */     indentedOptionsPanel.add(optionsPanel);
/* 120 */     indentedOptionsPanel.setAlignmentX(0.0F);
/*     */     
/* 122 */     JPanel panel = new JPanel();
/* 123 */     panel.setLayout(new BoxLayout(panel, 1));
/* 124 */     panel.add(checkBox);
/* 125 */     panel.add(indentedOptionsPanel);
/* 126 */     panel.setAlignmentX(0.0F);
/* 127 */     return panel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyConfigurationTo(WorkspacesConfiguration configuration) {
/* 133 */     configuration.setDisplayMainMenuUI(isDisplayMainMenuUI());
/* 134 */     configuration.setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(isPinAnUnpinnedOpenWorkspaceSelectedFromMenu());
/*     */ 
/*     */     
/* 137 */     configuration.setDisplayToolWindowUI(isDisplayToolWindowUI());
/* 138 */     configuration.setDisplayButtonActionsInContextMenu(isDisplayButtonActionsInContextMenu());
/*     */ 
/*     */     
/* 141 */     configuration.setAutoPin(isAutoPin());
/* 142 */     configuration.setAutoPinUponExplicitOpenOnly(isAutoPinUponExplicitOpenOnly());
/* 143 */     configuration.setAutoPinUponCreateWorkspace(isAutoPinUponCreateWorkspace());
/* 144 */     configuration.setAutoUnpin(isAutoUnpin());
/* 145 */     configuration.setAutoUnpinUponExplicitCloseOnly(isAutoUnpinUponExplicitCloseOnly());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyConfigurationFrom(WorkspacesConfiguration configuration) {
/* 151 */     setDisplayMainMenuUI(configuration.isDisplayMainMenuUI());
/* 152 */     setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(configuration.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu());
/*     */ 
/*     */     
/* 155 */     setDisplayToolWindowUI(configuration.isDisplayToolWindowUI());
/* 156 */     setDisplayButtonActionsInContextMenu(configuration.isDisplayButtonActionsInContextMenu());
/*     */ 
/*     */     
/* 159 */     setAutoPin(configuration.isAutoPin());
/* 160 */     setAutoPinUponExplicitOpenOnly(configuration.isAutoPinUponExplicitOpenOnly());
/* 161 */     setAutoPinUponCreateWorkspace(configuration.isAutoPinUponCreateWorkspace());
/* 162 */     setAutoUnpin(configuration.isAutoUnpin());
/* 163 */     setAutoUnpinUponExplicitCloseOnly(configuration.isAutoUnpinUponExplicitCloseOnly());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isDisplayMainMenuUI() {
/* 168 */     return this.isDisplayMainMenuUI.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDisplayMainMenuUI(boolean isDisplayMainMenuUI) {
/* 173 */     this.isDisplayMainMenuUI.setSelected(isDisplayMainMenuUI);
/* 174 */     this.pinAnUnpinnedOpenWorkspaceSelectedFromMenuLabel.setEnabled(isDisplayMainMenuUI);
/* 175 */     this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.setEnabled(isDisplayMainMenuUI);
/* 176 */     this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.setEnabled(isDisplayMainMenuUI);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu() {
/* 181 */     return this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu) {
/* 186 */     this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuTrue.setSelected(isPinAnUnpinnedOpenWorkspaceSelectedFromMenu);
/* 187 */     this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenuFalse.setSelected(!isPinAnUnpinnedOpenWorkspaceSelectedFromMenu);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isDisplayToolWindowUI() {
/* 192 */     return this.isDisplayToolWindowUI.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDisplayToolWindowUI(boolean isDisplayToolWindowUI) {
/* 197 */     this.isDisplayToolWindowUI.setSelected(isDisplayToolWindowUI);
/* 198 */     this.isDisplayButtonActionsInContextMenu.setEnabled(isDisplayToolWindowUI);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isDisplayButtonActionsInContextMenu() {
/* 203 */     return this.isDisplayButtonActionsInContextMenu.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDisplayButtonActionsInContextMenu(boolean isDisplayButtonActionsInContextMenu) {
/* 208 */     this.isDisplayButtonActionsInContextMenu.setSelected(isDisplayButtonActionsInContextMenu);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAutoPin() {
/* 213 */     return this.isAutoPin.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAutoPin(boolean autoPin) {
/* 218 */     this.isAutoPin.setSelected(autoPin);
/* 219 */     this.isAutoPinUponExplicitOpenOnlyTrue.setEnabled(autoPin);
/* 220 */     this.isAutoPinUponExplicitOpenOnlyFalse.setEnabled(autoPin);
/* 221 */     this.isAutoPinUponCreateWorkspace.setEnabled(autoPin);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAutoPinUponExplicitOpenOnly() {
/* 226 */     return this.isAutoPinUponExplicitOpenOnlyTrue.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAutoPinUponExplicitOpenOnly(boolean autoPinUponExplicitOpenOnly) {
/* 231 */     this.isAutoPinUponExplicitOpenOnlyTrue.setSelected(autoPinUponExplicitOpenOnly);
/* 232 */     this.isAutoPinUponExplicitOpenOnlyFalse.setSelected(!autoPinUponExplicitOpenOnly);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAutoPinUponCreateWorkspace() {
/* 237 */     return this.isAutoPinUponCreateWorkspace.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAutoPinUponCreateWorkspace(boolean autoPinUponCreateWorkspace) {
/* 242 */     this.isAutoPinUponCreateWorkspace.setSelected(autoPinUponCreateWorkspace);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAutoUnpin() {
/* 247 */     return this.isAutoUnpin.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAutoUnpin(boolean autoUnpin) {
/* 252 */     this.isAutoUnpin.setSelected(autoUnpin);
/* 253 */     this.isAutoUnpinUponExplicitCloseOnlyTrue.setEnabled(autoUnpin);
/* 254 */     this.isAutoUnpinUponExplicitCloseOnlyFalse.setEnabled(autoUnpin);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAutoUnpinUponExplicitCloseOnly() {
/* 259 */     return this.isAutoUnpinUponExplicitCloseOnlyTrue.isSelected();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setAutoUnpinUponExplicitCloseOnly(boolean autoUnpinUponExplicitCloseOnly) {
/* 264 */     this.isAutoUnpinUponExplicitCloseOnlyTrue.setSelected(autoUnpinUponExplicitCloseOnly);
/* 265 */     this.isAutoUnpinUponExplicitCloseOnlyFalse.setSelected(!autoUnpinUponExplicitCloseOnly);
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspacesConfigurationPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */