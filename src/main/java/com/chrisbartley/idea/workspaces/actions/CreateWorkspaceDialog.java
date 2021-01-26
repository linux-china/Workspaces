/*     */ package com.chrisbartley.idea.workspaces.actions;
/*     */ 
/*     */ import com.chrisbartley.idea.actions.MoveDownAction;
/*     */ import com.chrisbartley.idea.actions.MoveUpAction;
/*     */ import com.chrisbartley.idea.util.IncludableItem;
/*     */ import com.chrisbartley.idea.util.ReorderableListModel;
/*     */ import com.chrisbartley.idea.util.WrappedListModel;
/*     */ import com.chrisbartley.swing.event.ButtonTogglingDocumentListener;
/*     */ import com.chrisbartley.swing.event.DocumentEventValidator;
/*     */ import com.chrisbartley.swing.event.NonEmptyDocumentValidator;
/*     */ import com.intellij.openapi.actionSystem.ActionGroup;
/*     */ import com.intellij.openapi.actionSystem.ActionManager;
/*     */ import com.intellij.openapi.actionSystem.ActionToolbar;
/*     */ import com.intellij.openapi.actionSystem.AnAction;
/*     */ import com.intellij.openapi.actionSystem.DefaultActionGroup;
/*     */ import com.intellij.openapi.ui.DialogWrapper;
/*     */ import com.intellij.openapi.vfs.VirtualFile;
/*     */ import com.intellij.openapi.vfs.VirtualFileManager;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
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
/*     */ final class CreateWorkspaceDialog
/*     */   extends DialogWrapper
/*     */ {
/*  42 */   private static final NonEmptyDocumentValidator NON_EMPTY_DOCUMENT_VALIDATOR = new NonEmptyDocumentValidator();
/*     */   
/*     */   public static final int CREATE_EXIT_CODE = 0;
/*     */   
/*  46 */   private final JTextField workspaceNameTextField = new JTextField(20);
/*     */   
/*     */   private final WrappedListModel listModel;
/*     */   private final Set boundFileUrls;
/*     */   
/*     */   public CreateWorkspaceDialog(String windowTitle, String okButtonLabel, Set boundFileUrls, List candidateFileUrls) {
/*  52 */     super(false);
/*  53 */     setTitle(windowTitle);
/*  54 */     setOKButtonText(okButtonLabel);
/*  55 */     String defaultName = getDefaultName(candidateFileUrls);
/*  56 */     this.workspaceNameTextField.setText(defaultName);
/*  57 */     this.workspaceNameTextField.setCaretPosition(0);
/*  58 */     this.workspaceNameTextField.moveCaretPosition((defaultName == null) ? 0 : defaultName.length());
/*  59 */     this.listModel = (WrappedListModel)new ReorderableListModel(createListModel(candidateFileUrls));
/*  60 */     this.boundFileUrls = boundFileUrls;
/*  61 */     init();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getDefaultName(List candidateFileUrls) {
/*  66 */     if (candidateFileUrls.size() == 1) {
/*     */       
/*  68 */       String url = (String) candidateFileUrls.get(0);
/*  69 */       VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
/*  70 */       VirtualFile virtualFile = virtualFileManager.findFileByUrl(url);
/*  71 */       return virtualFile.getNameWithoutExtension();
/*     */     } 
/*  73 */     return "Untitled";
/*     */   }
/*     */ 
/*     */   
/*     */   private ArrayList createListModel(List fileUrls) {
/*  78 */     ArrayList includableItems = new ArrayList();
/*  79 */     for (ListIterator listIterator = fileUrls.listIterator(); listIterator.hasNext(); ) {
/*     */       
/*  81 */       String url =(String) listIterator.next();
/*  82 */       includableItems.add(new IncludableItem(url));
/*     */     } 
/*  84 */     return includableItems;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComponent createCenterPanel() {
/*  90 */     JList fileList = new JList((ListModel)this.listModel);
/*  91 */     fileList.setSelectionMode(2);
/*  92 */     fileList.setCellRenderer(new ConfigureWorkspaceFileListCellRenderer());
/*  93 */     JScrollPane scrollPane = new JScrollPane(fileList);
/*     */ 
/*     */     
/*  96 */     this.workspaceNameTextField.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 100 */             CreateWorkspaceDialog.this.doOKAction();
/*     */           }
/*     */         });
/* 103 */     this.workspaceNameTextField.getDocument().addDocumentListener((DocumentListener)new ButtonTogglingDocumentListener(getOKAction(), (DocumentEventValidator)NON_EMPTY_DOCUMENT_VALIDATOR));
/* 104 */     this.workspaceNameTextField.setAlignmentX(0.0F);
/*     */     
/* 106 */     JPanel topPane = new JPanel();
/* 107 */     topPane.setLayout(new BoxLayout(topPane, 1));
/* 108 */     topPane.add(new JLabel("Workspace Name:"));
/* 109 */     topPane.add(Box.createRigidArea(new Dimension(0, 5)));
/* 110 */     topPane.add(this.workspaceNameTextField);
/*     */     
/* 112 */     DefaultActionGroup toolbarGroup = new DefaultActionGroup();
/* 113 */     toolbarGroup.add(new IncludeAction(fileList));
/* 114 */     toolbarGroup.add(new ExcludeAction(fileList));
/* 115 */     toolbarGroup.add(new ExcludeWorkspacedAction(this.boundFileUrls, (WrappedListModel)fileList.getModel()));
/* 116 */     toolbarGroup.add((AnAction)new MoveUpAction(fileList));
/* 117 */     toolbarGroup.add((AnAction)new MoveDownAction(fileList));
/* 118 */     ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("CREATE_WORKSPACE_DIALOG", (ActionGroup)toolbarGroup, true);
/* 119 */     toolbar.getComponent().setAlignmentX(0.0F);
/* 120 */     topPane.add(toolbar.getComponent());
/*     */     
/* 122 */     JPanel panel = new JPanel(new BorderLayout());
/* 123 */     panel.add(topPane, "North");
/* 124 */     panel.add(scrollPane, "Center");
/*     */     
/* 126 */     return panel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Action[] createActions() {
/* 131 */     getOKAction().putValue("DefaultAction", Boolean.TRUE);
/* 132 */     getCancelAction().putValue("DefaultAction", null);
/* 133 */     return new Action[] { getOKAction(), getCancelAction() };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent getPreferredFocusedComponent() {
/* 141 */     return this.workspaceNameTextField;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNewWorkspaceName() {
/* 146 */     return this.workspaceNameTextField.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public List getNewWorkspaceUrls() {
/* 151 */     if (!this.listModel.isEmpty()) {
/*     */       
/* 153 */       List selectedUrls = new ArrayList();
/* 154 */       for (ListIterator listIterator = this.listModel.listIterator(); listIterator.hasNext(); ) {
/*     */         
/* 156 */         IncludableItem includableItem =(IncludableItem) listIterator.next();
/* 157 */         if (includableItem.isIncluded())
/*     */         {
/* 159 */           selectedUrls.add(includableItem.getItem());
/*     */         }
/*     */       } 
/* 162 */       return selectedUrls;
/*     */     } 
/* 164 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/CreateWorkspaceDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */