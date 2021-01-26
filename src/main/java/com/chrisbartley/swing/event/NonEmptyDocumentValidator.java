/*    */ package com.chrisbartley.swing.event;
/*    */ 
/*    */ import javax.swing.event.DocumentEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NonEmptyDocumentValidator
/*    */   implements DocumentEventValidator
/*    */ {
/*    */   public boolean isValid(DocumentEvent event) {
/* 17 */     return (event.getDocument().getLength() > 0);
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/swing/event/NonEmptyDocumentValidator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */