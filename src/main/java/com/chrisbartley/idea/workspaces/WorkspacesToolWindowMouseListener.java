/*    */ package com.chrisbartley.idea.workspaces;
/*    */ 
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.util.WrappedListModel;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JPopupMenu;
/*    */ import javax.swing.event.MouseInputAdapter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class WorkspacesToolWindowMouseListener
/*    */   extends MouseInputAdapter
/*    */ {
/*    */   private final JList list;
/*    */   private final Project project;
/*    */   private final JPopupMenu jPopupMenu;
/*    */   
/*    */   public WorkspacesToolWindowMouseListener(JList list, Project project, JPopupMenu jPopupMenu) {
/* 28 */     this.list = list;
/* 29 */     this.project = project;
/* 30 */     this.jPopupMenu = jPopupMenu;
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(MouseEvent e) {
/* 35 */     if (e.getButton() == 3) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 40 */       int indexOfClickedItem = this.list.locationToIndex(e.getPoint());
/* 41 */       if (indexOfClickedItem >= 0 && !this.list.isSelectedIndex(indexOfClickedItem))
/*    */       {
/* 43 */         this.list.setSelectedIndex(indexOfClickedItem);
/*    */       }
/*    */ 
/*    */       
/* 47 */       this.jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
/*    */     }
/* 49 */     else if (e.getButton() == 1) {
/*    */ 
/*    */       
/* 52 */       if (e.getClickCount() == 2)
/*    */       {
/* 54 */         openWorkspace(this.list.locationToIndex(e.getPoint()));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void openWorkspace(int index) {
/* 61 */     Workspace workspace = getWorkspaceByIndex(index);
/* 62 */     if (workspace != null) {
/*    */       
/* 64 */       WorkspaceManager workspaceManager = (WorkspaceManager)this.project.getComponent(WorkspaceManager.class);
/* 65 */       workspaceManager.openWorkspace(workspace);
/*    */ 
/*    */       
/* 68 */       RefreshableListModel model = (RefreshableListModel)this.list.getModel();
/* 69 */       model.refresh(index);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private Workspace getWorkspaceByIndex(int index) {
/* 75 */     WrappedListModel model = (WrappedListModel)this.list.getModel();
/* 76 */     return model.isIndexWithinBounds(index) ? (Workspace)model.get(index) : null;
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspacesToolWindowMouseListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */