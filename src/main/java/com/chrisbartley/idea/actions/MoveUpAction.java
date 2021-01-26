/*    */ package com.chrisbartley.idea.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.Icons;
/*    */ import com.chrisbartley.idea.util.ReorderableListModel;
/*    */ import com.intellij.openapi.actionSystem.AnAction;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.ListSelectionModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoveUpAction
/*    */   extends AnAction
/*    */ {
/*    */   private final JList list;
/*    */   private final ReorderableListModel listModel;
/*    */   
/*    */   public MoveUpAction(JList list) {
/* 24 */     super("Move up", "Move the selected item(s) up", Icons.MOVE_UP);
/* 25 */     this.list = list;
/* 26 */     this.listModel = (ReorderableListModel)list.getModel();
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 31 */     ListSelectionModel selectionModel = this.list.getSelectionModel();
/* 32 */     if (!selectionModel.isSelectionEmpty()) {
/*    */ 
/*    */       
/* 35 */       int[] selectedIndices = this.list.getSelectedIndices();
/* 36 */       this.listModel.shiftElements(selectedIndices, false);
/*    */ 
/*    */       
/* 39 */       for (int i = 0; i < selectedIndices.length; i++)
/*    */       {
/* 41 */         selectedIndices[i] = selectedIndices[i] - 1;
/*    */       }
/* 43 */       this.list.setSelectedIndices(selectedIndices);
/* 44 */       this.list.ensureIndexIsVisible(selectedIndices[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 50 */     event.getPresentation().setEnabled((this.list.getSelectionModel().getMinSelectionIndex() > 0));
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/actions/MoveUpAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */