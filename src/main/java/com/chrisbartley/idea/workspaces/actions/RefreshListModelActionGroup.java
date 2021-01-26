/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.actions.BaseActionGroup;
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.workspaces.WorkspaceManager;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RefreshListModelActionGroup
/*    */   extends BaseActionGroup
/*    */ {
/*    */   private final RefreshableListModel listModel;
/*    */   
/*    */   public RefreshListModelActionGroup(RefreshableListModel listModel) {
/* 18 */     this(null, false, listModel);
/*    */   }
/*    */ 
/*    */   
/*    */   public RefreshListModelActionGroup(String shortName, boolean popup, RefreshableListModel listModel) {
/* 23 */     super(shortName, popup);
/* 24 */     this.listModel = listModel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 29 */     Project project = getProject(event);
/* 30 */     if (project != null) {
/*    */       
/* 32 */       WorkspaceManager workspaceManager = (WorkspaceManager)project.getComponent(WorkspaceManager.class);
/* 33 */       workspaceManager.updateImplicitAutoPinningAndUnpinning();
/* 34 */       this.listModel.refreshAll();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/RefreshListModelActionGroup.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */