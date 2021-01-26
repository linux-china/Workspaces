/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.IncludableItem;
/*    */ import com.chrisbartley.idea.util.WrappedListModel;
/*    */ import com.chrisbartley.idea.workspaces.Icons;
/*    */ import com.intellij.openapi.actionSystem.AnAction;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import java.util.ListIterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ExcludeWorkspacedAction
/*    */   extends AnAction
/*    */ {
/*    */   private final Set boundFileUrls;
/*    */   private final WrappedListModel listModel;
/*    */   
/*    */   public ExcludeWorkspacedAction(Set boundFileUrls, WrappedListModel listModel) {
/* 24 */     super("Exclude bound files", "Exclude files that are already bound to a workspace", Icons.EXCLUDE_WORKSPACED);
/* 25 */     this.boundFileUrls = boundFileUrls;
/* 26 */     this.listModel = listModel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 31 */     if (this.listModel.getSize() > 0) {
/*    */       
/* 33 */       for (ListIterator listIterator = this.listModel.listIterator(); listIterator.hasNext(); ) {
/*    */         
/* 35 */         IncludableItem item = (IncludableItem) listIterator.next();
/* 36 */         if (this.boundFileUrls.contains(item.getItem()))
/*    */         {
/* 38 */           item.setIncluded(false);
/*    */         }
/*    */       } 
/*    */       
/* 42 */       this.listModel.refreshAll();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 48 */     event.getPresentation().setEnabled((this.listModel.getSize() > 0));
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/ExcludeWorkspacedAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */