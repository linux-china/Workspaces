/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.actions.RegisterableAction;
/*    */ import com.chrisbartley.idea.workspaces.WorkspaceManager;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BaseWorkspaceAction
/*    */   extends RegisterableAction
/*    */ {
/*    */   public BaseWorkspaceAction() {}
/*    */   
/*    */   public BaseWorkspaceAction(String text) {
/* 20 */     super(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaseWorkspaceAction(String text, String description, Icon icon) {
/* 25 */     super(text, description, icon);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   final WorkspaceManager getWorkspaceManager(Project project) {
/* 31 */     if (project != null)
/*    */     {
/* 33 */       return (WorkspaceManager)project.getComponent(WorkspaceManager.class);
/*    */     }
/* 35 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/BaseWorkspaceAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */