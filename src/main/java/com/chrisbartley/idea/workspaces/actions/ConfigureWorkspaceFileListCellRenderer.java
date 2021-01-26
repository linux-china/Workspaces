/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.IncludableItem;
/*    */ import com.chrisbartley.idea.workspaces.Icons;
/*    */ import com.intellij.openapi.vfs.VirtualFile;
/*    */ import com.intellij.openapi.vfs.VirtualFileManager;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.ListCellRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ConfigureWorkspaceFileListCellRenderer
/*    */   extends JLabel
/*    */   implements ListCellRenderer
/*    */ {
/*    */   public ConfigureWorkspaceFileListCellRenderer() {
/* 20 */     setOpaque(true);
/* 21 */     setHorizontalAlignment(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 26 */     IncludableItem item = (IncludableItem)value;
/* 27 */     String url = (String)item.getItem();
/* 28 */     VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(url);
/*    */     
/* 30 */     setText(virtualFile.getPresentableUrl());
/* 31 */     setIcon(item.isIncluded() ? Icons.INCLUDED : Icons.EXCLUDED);
/*    */     
/* 33 */     if (isSelected) {
/*    */       
/* 35 */       setBackground(list.getSelectionBackground());
/* 36 */       setForeground(list.getSelectionForeground());
/*    */     }
/*    */     else {
/*    */       
/* 40 */       setBackground(list.getBackground());
/* 41 */       setForeground(list.getForeground());
/*    */     } 
/*    */     
/* 44 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/ConfigureWorkspaceFileListCellRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */