/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.workspaces.Icons;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashSet;
/*    */ import javax.swing.JList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CloseWorkspaceAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   private final JList list;
/*    */   
/*    */   public CloseWorkspaceAction(JList list) {
/* 24 */     super("Close", "Close the selected workspace(s)", Icons.CLOSE_WORKSPACE);
/* 25 */     this.list = list;
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 30 */     Project project = getProject(event);
/* 31 */     if (project != null)
/*    */     {
/* 33 */       if (!this.list.getSelectionModel().isSelectionEmpty()) {
/*    */         
/* 35 */         getWorkspaceManager(project).closeWorkspaces(new HashSet(Arrays.asList(this.list.getSelectedValues())));
/*    */ 
/*    */         
/* 38 */         RefreshableListModel model = (RefreshableListModel)this.list.getModel();
/* 39 */         model.refresh(this.list.getSelectedIndices());
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 46 */     event.getPresentation().setEnabled(!this.list.getSelectionModel().isSelectionEmpty());
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/CloseWorkspaceAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */