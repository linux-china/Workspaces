/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CloseAllWorkspacesAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   public CloseAllWorkspacesAction() {
/* 13 */     super("Close All Workspaces", "Close all workspaces", (Icon)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 18 */     Project project = getProject(event);
/* 19 */     if (project != null)
/*    */     {
/* 21 */       getWorkspaceManager(project).closeAllWorkspaces();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 27 */     Project project = getProject(event);
/* 28 */     if (project != null) {
/*    */       
/* 30 */       event.getPresentation().setEnabled((getWorkspaceManager(project).getWorkspaceCount() > 0));
/*    */     }
/*    */     else {
/*    */       
/* 34 */       event.getPresentation().setEnabled(false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/CloseAllWorkspacesAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */