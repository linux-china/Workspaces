/*    */ package com.chrisbartley.idea.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IncludableItem
/*    */ {
/*    */   private final Object item;
/*    */   private boolean isIncluded = true;
/*    */   
/*    */   public IncludableItem(Object item) {
/* 13 */     this.item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getItem() {
/* 18 */     return this.item;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isIncluded() {
/* 23 */     return this.isIncluded;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIncluded(boolean included) {
/* 28 */     this.isIncluded = included;
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/util/IncludableItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */