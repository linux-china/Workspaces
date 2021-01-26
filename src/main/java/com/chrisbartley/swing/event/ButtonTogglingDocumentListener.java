/*    */ package com.chrisbartley.swing.event;
/*    */ 
/*    */ import javax.swing.Action;
/*    */ import javax.swing.event.DocumentEvent;
/*    */ import javax.swing.event.DocumentListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ButtonTogglingDocumentListener
/*    */   implements DocumentListener
/*    */ {
/*    */   private final Action buttonToToggle;
/*    */   private final DocumentEventValidator documentEventValidator;
/*    */   
/*    */   public ButtonTogglingDocumentListener(Action buttonToToggle, DocumentEventValidator documentEventValidator) {
/* 17 */     this.buttonToToggle = buttonToToggle;
/* 18 */     this.documentEventValidator = documentEventValidator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void insertUpdate(DocumentEvent event) {
/* 27 */     toggleButton(event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void removeUpdate(DocumentEvent event) {
/* 36 */     toggleButton(event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void changedUpdate(DocumentEvent event) {
/* 45 */     toggleButton(event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void toggleButton(DocumentEvent event) {
/* 54 */     this.buttonToToggle.setEnabled(this.documentEventValidator.isValid(event));
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/swing/event/ButtonTogglingDocumentListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */