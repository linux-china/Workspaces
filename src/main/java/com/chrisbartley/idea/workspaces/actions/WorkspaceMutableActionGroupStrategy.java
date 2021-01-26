/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.actions.MutableActionGroupStrategy;
/*    */ import com.chrisbartley.idea.workspaces.WorkspaceManager;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorkspaceMutableActionGroupStrategy
/*    */   extends MutableActionGroupStrategy
/*    */ {
/*    */   protected final WorkspaceManager getWorkspaceManager(AnActionEvent event) {
/* 15 */     return (WorkspaceManager)getProject(event).getComponent(WorkspaceManager.class);
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/WorkspaceMutableActionGroupStrategy.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */