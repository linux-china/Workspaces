/*    */ package com.chrisbartley.idea.workspaces;
/*    */ 
/*    */ import com.intellij.openapi.fileEditor.FileEditorManager;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.Box;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.ListCellRenderer;
/*    */ 
/*    */ 
/*    */ final class WorkspacesToolWindowListCellRenderer
/*    */   extends JPanel
/*    */   implements ListCellRenderer
/*    */ {
/*    */   private final FileEditorManager fileEditorManager;
/* 20 */   private final JLabel isPinnedArea = new JLabel();
/* 21 */   private final JLabel isOpenArea = new JLabel();
/* 22 */   private final JLabel nameArea = new JLabel();
/* 23 */   private final JLabel openFilesArea = new JLabel();
/* 24 */   private final JLabel numFilesArea = new JLabel();
/* 25 */   private final JLabel isActiveArea = new JLabel();
/*    */ 
/*    */   
/*    */   public WorkspacesToolWindowListCellRenderer(Project project) {
/* 29 */     setLayout(new BoxLayout(this, 0));
/* 30 */     setAlignmentX(0.0F);
/* 31 */     setOpaque(true);
/* 32 */     this.fileEditorManager = FileEditorManager.getInstance(project);
/* 33 */     add(Box.createRigidArea(new Dimension(3, 0)));
/* 34 */     add(this.isPinnedArea);
/* 35 */     add(this.isOpenArea);
/* 36 */     add(this.nameArea);
/* 37 */     add(Box.createGlue());
/* 38 */     add(new JLabel("[ "));
/* 39 */     add(this.openFilesArea);
/* 40 */     add(new JLabel(" / "));
/* 41 */     add(this.numFilesArea);
/* 42 */     add(new JLabel(" ]"));
/* 43 */     add(Box.createRigidArea(new Dimension(3, 0)));
/* 44 */     add(this.isActiveArea);
/* 45 */     add(Box.createRigidArea(new Dimension(3, 0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 50 */     if (isSelected) {
/*    */       
/* 52 */       setForeground(list.getSelectionForeground());
/* 53 */       setBackground(list.getSelectionBackground());
/*    */     }
/*    */     else {
/*    */       
/* 57 */       setForeground(list.getForeground());
/* 58 */       setBackground(list.getBackground());
/*    */     } 
/*    */     
/* 61 */     Workspace workspace = (Workspace)value;
/* 62 */     WorkspaceState workspaceState = workspace.getState(this.fileEditorManager);
/*    */     
/* 64 */     this.nameArea.setText(workspace.getName());
/* 65 */     this.openFilesArea.setText(String.valueOf(workspaceState.getNumOpenFiles()));
/* 66 */     this.numFilesArea.setText(String.valueOf(workspaceState.getNumFiles()));
/* 67 */     this.isPinnedArea.setIcon(workspaceState.getPinnedStatusIcon());
/* 68 */     this.isOpenArea.setIcon(workspaceState.getOpenedStatusIcon());
/* 69 */     this.isActiveArea.setIcon(workspace.contains(this.fileEditorManager.getSelectedFiles()) ? Icons.ACTIVE_WORKSPACE : Icons.INACTIVE_WORKSPACE);
/*    */     
/* 71 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspacesToolWindowListCellRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */