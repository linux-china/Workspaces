/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.workspaces.Icons;
/*    */ import com.chrisbartley.idea.workspaces.Workspace;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.JList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ConfigureWorkspaceAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   private final JList list;
/*    */   private Workspace workspace;
/*    */   
/*    */   public ConfigureWorkspaceAction(Workspace workspace) {
/* 23 */     this(workspace.getName(), "Configure '" + workspace.getName() + "'", null, null, workspace);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConfigureWorkspaceAction(JList list) {
/* 31 */     this("Properties", "Configure the selected workspace", Icons.CONFIGURE_WORKSPACE, list, null);
/*    */   }
/*    */ 
/*    */   
/*    */   private ConfigureWorkspaceAction(String text, String description, Icon icon, JList list, Workspace workspace) {
/* 36 */     super(text, description, icon);
/* 37 */     this.list = list;
/* 38 */     this.workspace = workspace;
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 43 */     Project project = getProject(event);
/* 44 */     if (project != null) {
/*    */ 
/*    */       
/* 47 */       if (this.list != null && !this.list.getSelectionModel().isSelectionEmpty()) {
/*    */         
/* 49 */         Object[] selectedWorkspaces = this.list.getSelectedValues();
/* 50 */         if (selectedWorkspaces.length == 1)
/*    */         {
/* 52 */           this.workspace = (Workspace)selectedWorkspaces[0];
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 57 */       if (this.workspace != null) {
/*    */         
/* 59 */         ConfigureWorkspaceDialog dialog = new ConfigureWorkspaceDialog("Workspace Properties", "OK", this.workspace);
/* 60 */         dialog.pack();
/* 61 */         dialog.show();
/* 62 */         if (dialog.getExitCode() == 0)
/*    */         {
/* 64 */           this.workspace.update(dialog.getNewWorkspaceName(), dialog.getNewWorkspaceUrls());
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 72 */     if (getProject(event) != null)
/*    */     {
/* 74 */       if (this.list != null) {
/*    */         
/* 76 */         event.getPresentation().setEnabled((!this.list.getSelectionModel().isSelectionEmpty() && (this.list.getSelectedIndices()).length == 1));
/*    */       }
/*    */       else {
/*    */         
/* 80 */         event.getPresentation().setEnabled(true);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/ConfigureWorkspaceAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */