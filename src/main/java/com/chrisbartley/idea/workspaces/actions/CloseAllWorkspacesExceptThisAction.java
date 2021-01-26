/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.workspaces.Workspace;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import java.util.Arrays;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.JList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CloseAllWorkspacesExceptThisAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   private final JList list;
/*    */   private final Workspace workspace;
/*    */   
/*    */   public CloseAllWorkspacesExceptThisAction(Workspace workspace) {
/* 23 */     this(workspace.getName(), "Close all workspaces except '" + workspace.getName() + "'", (JList)null, workspace);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CloseAllWorkspacesExceptThisAction(JList list) {
/* 32 */     this((String)null, (String)null, list, (Workspace)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private CloseAllWorkspacesExceptThisAction(String text, String description, JList list, Workspace workspace) {
/* 37 */     super(text, description, (Icon)null);
/* 38 */     this.list = list;
/* 39 */     this.workspace = workspace;
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 44 */     Project project = getProject(event);
/* 45 */     if (project != null)
/*    */     {
/* 47 */       if (this.list != null) {
/*    */         
/* 49 */         if (!this.list.getSelectionModel().isSelectionEmpty())
/*    */         {
/* 51 */           getWorkspaceManager(project).closeAllButTheseWorkspaces(Arrays.asList(this.list.getSelectedValues()));
/*    */ 
/*    */           
/* 54 */           RefreshableListModel model = (RefreshableListModel)this.list.getModel();
/* 55 */           model.refreshAllButThese(this.list.getSelectedIndices());
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 60 */         getWorkspaceManager(project).closeAllButThisWorkspace(this.workspace);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 67 */     Project project = getProject(event);
/* 68 */     if (project != null)
/*    */     {
/* 70 */       if (this.list != null) {
/*    */         
/* 72 */         event.getPresentation().setEnabled(!this.list.getSelectionModel().isSelectionEmpty());
/* 73 */         if ((this.list.getSelectedValues()).length == 1)
/*    */         {
/* 75 */           event.getPresentation().setText("Close All Workspaces Except This");
/* 76 */           event.getPresentation().setDescription("Close all workspaces except the currently selected one");
/*    */         }
/*    */         else
/*    */         {
/* 80 */           event.getPresentation().setText("Close All Workspaces Except These");
/* 81 */           event.getPresentation().setDescription("Close all workspaces except the currently selected ones");
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 86 */         event.getPresentation().setEnabled(true);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/CloseAllWorkspacesExceptThisAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */