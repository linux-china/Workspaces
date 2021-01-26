/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.workspaces.Workspace;
/*    */ import com.chrisbartley.idea.workspaces.WorkspaceState;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.fileEditor.FileEditorManager;
/*    */ import com.intellij.openapi.project.Project;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ToggleWorkspaceOpennessAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   private final Project project;
/*    */   private final Workspace workspace;
/*    */   
/*    */   public ToggleWorkspaceOpennessAction(Project project, Workspace workspace) {
/* 19 */     super(workspace.getName(), "Open the '" + workspace.getName() + "' workspace", null);
/* 20 */     this.project = project;
/* 21 */     this.workspace = workspace;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getActionRegistrationId() {
/* 26 */     return "ToggleWorkspaceOpenClosed." + this.project.getProjectFile().getNameWithoutExtension() + "." + this.workspace.getName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 32 */     if (this.project != null)
/*    */     {
/* 34 */       getWorkspaceManager(this.project).toggleWorkspaceOpenness(this.workspace);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 41 */     if (this.project != null) {
/*    */       
/* 43 */       WorkspaceState workspaceState = this.workspace.getState(FileEditorManager.getInstance(this.project));
/*    */       
/* 45 */       event.getPresentation().setIcon(workspaceState.getComboStatusIcon());
/* 46 */       event.getPresentation().setDescription(getWorkspaceManager(this.project).getToggleWorkspaceOpennessDescription(this.workspace));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     return this.workspace.getName();
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/ToggleWorkspaceOpennessAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */