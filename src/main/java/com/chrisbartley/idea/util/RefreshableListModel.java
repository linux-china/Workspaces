/*    */ package com.chrisbartley.idea.util;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import javax.swing.AbstractListModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class RefreshableListModel
/*    */   extends AbstractListModel
/*    */ {
/*    */   public final void refreshAll() {
/* 13 */     fireContentsChanged(this, 0, getSize() - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public final void refresh(int[] indices) {
/* 18 */     if (indices != null)
/*    */     {
/* 20 */       for (int i = 0; i < indices.length; i++)
/*    */       {
/* 22 */         refresh(indices[i]);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public final void refresh(int index) {
/* 29 */     if (index >= 0 && index < getSize())
/*    */     {
/* 31 */       fireContentsChanged(this, index, index);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshAllButThese(int[] indicesToIgnore) {
/* 37 */     if (indicesToIgnore != null)
/*    */     {
/* 39 */       if (indicesToIgnore.length == 0) {
/*    */         
/* 41 */         refreshAll();
/*    */       }
/*    */       else {
/*    */         
/* 45 */         int[] indicesNotToRefresh = new int[indicesToIgnore.length];
/* 46 */         System.arraycopy(indicesToIgnore, 0, indicesNotToRefresh, 0, indicesToIgnore.length);
/* 47 */         for (int i = 0; i < getSize(); i++) {
/*    */           
/* 49 */           if (Arrays.binarySearch(indicesNotToRefresh, i) < 0)
/*    */           {
/* 51 */             refresh(i);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/util/RefreshableListModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */