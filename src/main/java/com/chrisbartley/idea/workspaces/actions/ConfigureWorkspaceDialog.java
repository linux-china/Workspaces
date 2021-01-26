/*     */ package com.chrisbartley.idea.workspaces.actions;
/*     */ 
/*     */ import com.chrisbartley.idea.actions.MoveDownAction;
/*     */ import com.chrisbartley.idea.actions.MoveUpAction;
/*     */ import com.chrisbartley.idea.util.IncludableItem;
/*     */ import com.chrisbartley.idea.util.ReorderableListModel;
/*     */ import com.chrisbartley.idea.util.WrappedListModel;
/*     */ import com.chrisbartley.idea.workspaces.Workspace;
/*     */ import com.chrisbartley.swing.event.ButtonTogglingDocumentListener;
/*     */ import com.chrisbartley.swing.event.DocumentEventValidator;
/*     */ import com.chrisbartley.swing.event.NonEmptyDocumentValidator;
/*     */ import com.intellij.openapi.actionSystem.ActionGroup;
/*     */ import com.intellij.openapi.actionSystem.ActionManager;
/*     */ import com.intellij.openapi.actionSystem.ActionToolbar;
/*     */ import com.intellij.openapi.actionSystem.AnAction;
/*     */ import com.intellij.openapi.actionSystem.DefaultActionGroup;
/*     */ import com.intellij.openapi.ui.DialogWrapper;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.ListModel;
/*     */ import javax.swing.event.DocumentListener;
/*     */ 
/*     */ final class ConfigureWorkspaceDialog
/*     */   extends DialogWrapper
/*     */ {
/*  40 */   private static final NonEmptyDocumentValidator NON_EMPTY_DOCUMENT_VALIDATOR = new NonEmptyDocumentValidator();
/*     */   
/*  42 */   private final JTextField workspaceNameTextField = new JTextField(20);
/*     */   
/*     */   private final WrappedListModel listModel;
/*     */   
/*     */   public ConfigureWorkspaceDialog(String windowTitle, String okButtonLabel, Workspace oldWorkspace) {
/*  47 */     super(false);
/*  48 */     setTitle(windowTitle);
/*  49 */     setOKButtonText(okButtonLabel);
/*  50 */     this.workspaceNameTextField.setText(oldWorkspace.getName());
/*  51 */     this.workspaceNameTextField.setCaretPosition(0);
/*  52 */     this.workspaceNameTextField.moveCaretPosition((oldWorkspace.getName() == null) ? 0 : oldWorkspace.getName().length());
/*  53 */     this.listModel = (WrappedListModel)new ReorderableListModel(createListModel(oldWorkspace.getFileUrls()));
/*  54 */     init();
/*     */   }
/*     */ 
/*     */   
/*     */   private ArrayList createListModel(List fileUrls) {
/*  59 */     ArrayList includableItems = new ArrayList();
/*  60 */     for (ListIterator listIterator = fileUrls.listIterator(); listIterator.hasNext(); ) {
/*     */       
/*  62 */       String url = (String) listIterator.next();
/*  63 */       includableItems.add(new IncludableItem(url));
/*     */     } 
/*  65 */     return includableItems;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComponent createCenterPanel() {
/*  71 */     JList fileList = new JList((ListModel)this.listModel);
/*  72 */     fileList.setSelectionMode(2);
/*  73 */     fileList.setCellRenderer(new ConfigureWorkspaceFileListCellRenderer());
/*  74 */     JScrollPane scrollPane = new JScrollPane(fileList);
/*     */ 
/*     */     
/*  77 */     this.workspaceNameTextField.addActionListener(new ActionListener() {
/*     */
/*     */           public void actionPerformed(ActionEvent e) {
/*  81 */             ConfigureWorkspaceDialog.this.doOKAction();
/*     */           }
/*     */         });
/*  84 */     this.workspaceNameTextField.getDocument().addDocumentListener((DocumentListener)new ButtonTogglingDocumentListener(getOKAction(), (DocumentEventValidator)NON_EMPTY_DOCUMENT_VALIDATOR));
/*  85 */     this.workspaceNameTextField.setAlignmentX(0.0F);
/*     */     
/*  87 */     JPanel topPane = new JPanel();
/*  88 */     topPane.setLayout(new BoxLayout(topPane, 1));
/*  89 */     topPane.add(new JLabel("Workspace Name:"));
/*  90 */     topPane.add(Box.createRigidArea(new Dimension(0, 5)));
/*  91 */     topPane.add(this.workspaceNameTextField);
/*     */     
/*  93 */     DefaultActionGroup toolbarGroup = new DefaultActionGroup();
/*  94 */     toolbarGroup.add(new IncludeAction(fileList));
/*  95 */     toolbarGroup.add(new ExcludeAction(fileList));
/*  96 */     toolbarGroup.add((AnAction)new MoveUpAction(fileList));
/*  97 */     toolbarGroup.add((AnAction)new MoveDownAction(fileList));
/*  98 */     ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("CONFIGURE_WORKSPACE_DIALOG", (ActionGroup)toolbarGroup, true);
/*  99 */     toolbar.getComponent().setAlignmentX(0.0F);
/* 100 */     topPane.add(toolbar.getComponent());
/*     */     
/* 102 */     JPanel panel = new JPanel(new BorderLayout());
/* 103 */     panel.add(topPane, "North");
/* 104 */     panel.add(scrollPane, "Center");
/*     */     
/* 106 */     return panel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Action[] createActions() {
/* 111 */     getOKAction().putValue("DefaultAction", Boolean.TRUE);
/* 112 */     getCancelAction().putValue("DefaultAction", null);
/* 113 */     return new Action[] { getOKAction(), getCancelAction() };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent getPreferredFocusedComponent() {
/* 121 */     return this.workspaceNameTextField;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNewWorkspaceName() {
/* 126 */     return this.workspaceNameTextField.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public List getNewWorkspaceUrls() {
/* 131 */     if (!this.listModel.isEmpty()) {
/*     */       
/* 133 */       List selectedUrls = new ArrayList();
/* 134 */       for (ListIterator listIterator = this.listModel.listIterator(); listIterator.hasNext(); ) {
/*     */         
/* 136 */         IncludableItem includableItem =(IncludableItem) listIterator.next();
/* 137 */         if (includableItem.isIncluded())
/*     */         {
/* 139 */           selectedUrls.add(includableItem.getItem());
/*     */         }
/*     */       } 
/* 142 */       return selectedUrls;
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/ConfigureWorkspaceDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */