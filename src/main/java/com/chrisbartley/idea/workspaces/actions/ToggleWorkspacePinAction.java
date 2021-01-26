/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.workspaces.Icons;
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
/*    */ 
/*    */ public final class ToggleWorkspacePinAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   private final JList list;
/*    */   private final Workspace workspace;
/*    */   
/*    */   public ToggleWorkspacePinAction(Workspace workspace) {
/* 25 */     this(workspace.getName(), "Pin/unpin '" + workspace.getName() + "'", (Icon)null, (JList)null, workspace);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ToggleWorkspacePinAction(JList list) {
/* 34 */     this("Toggle Pin", "Pin/unpin the selected workspace(s)", Icons.PINNED, list, (Workspace)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private ToggleWorkspacePinAction(String text, String description, Icon icon, JList list, Workspace workspace) {
/* 39 */     super(text, description, icon);
/* 40 */     this.list = list;
/* 41 */     this.workspace = workspace;
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 46 */     Project project = getProject(event);
/* 47 */     if (project != null)
/*    */     {
/* 49 */       if (this.list != null) {
/*    */         
/* 51 */         if (!this.list.getSelectionModel().isSelectionEmpty())
/*    */         {
/* 53 */           getWorkspaceManager(project).toggleWorkspacePinnedness(Arrays.asList(this.list.getSelectedValues()));
/*    */ 
/*    */           
/* 56 */           RefreshableListModel model = (RefreshableListModel)this.list.getModel();
/* 57 */           model.refresh(this.list.getSelectedIndices());
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 62 */         getWorkspaceManager(project).toggleWorkspacePinnedness(this.workspace);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 69 */     if (getProject(event) != null)
/*    */     {
/* 71 */       if (this.list != null) {
/*    */         
/* 73 */         event.getPresentation().setEnabled(!this.list.getSelectionModel().isSelectionEmpty());
/*    */       }
/*    */       else {
/*    */         
/* 77 */         event.getPresentation().setEnabled(true);
/* 78 */         event.getPresentation().setDescription((this.workspace.isPinned() ? "Unpin" : "Pin") + " '" + this.workspace.getName() + "'");
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/ToggleWorkspacePinAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */