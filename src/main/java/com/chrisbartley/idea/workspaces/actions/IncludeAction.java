/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.IncludableItem;
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.workspaces.Icons;
/*    */ import com.intellij.openapi.actionSystem.AnAction;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import javax.swing.JList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class IncludeAction
/*    */   extends AnAction
/*    */ {
/*    */   private final JList list;
/*    */   private final RefreshableListModel listModel;
/*    */   
/*    */   public IncludeAction(JList list) {
/* 24 */     super("Include", "Include the selected files(s)", Icons.INCLUDE_WORKSPACE_ITEM);
/* 25 */     this.list = list;
/* 26 */     this.listModel = (RefreshableListModel)list.getModel();
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 31 */     if (!this.list.getSelectionModel().isSelectionEmpty()) {
/*    */       
/* 33 */       Object[] selectedItems = this.list.getSelectedValues();
/* 34 */       for (int i = 0; i < selectedItems.length; i++) {
/*    */         
/* 36 */         IncludableItem item = (IncludableItem)selectedItems[i];
/* 37 */         item.setIncluded(true);
/*    */       } 
/* 39 */       this.listModel.refresh(this.list.getSelectedIndices());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 45 */     event.getPresentation().setEnabled(!this.list.getSelectionModel().isSelectionEmpty());
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/IncludeAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */