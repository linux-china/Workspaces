/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.workspaces.Icons;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import java.util.Arrays;
/*    */ import javax.swing.JList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class OpenWorkspaceAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   private final JList list;
/*    */   
/*    */   public OpenWorkspaceAction(JList list) {
/* 23 */     super("Open", "Open the selected workspace(s)", Icons.OPEN_WORKSPACE);
/* 24 */     this.list = list;
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 29 */     Project project = getProject(event);
/* 30 */     if (project != null)
/*    */     {
/* 32 */       if (!this.list.getSelectionModel().isSelectionEmpty()) {
/*    */         
/* 34 */         getWorkspaceManager(project).openWorkspaces(Arrays.asList(this.list.getSelectedValues()));
/*    */ 
/*    */         
/* 37 */         RefreshableListModel model = (RefreshableListModel)this.list.getModel();
/* 38 */         model.refresh(this.list.getSelectedIndices());
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 45 */     event.getPresentation().setEnabled(!this.list.getSelectionModel().isSelectionEmpty());
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/OpenWorkspaceAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */