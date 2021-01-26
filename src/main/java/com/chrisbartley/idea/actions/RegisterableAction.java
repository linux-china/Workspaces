/*    */ package com.chrisbartley.idea.actions;
/*    */ 
/*    */ import com.intellij.openapi.actionSystem.ActionManager;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class RegisterableAction
/*    */   extends BaseAction
/*    */ {
/*    */   public RegisterableAction() {}
/*    */   
/*    */   public RegisterableAction(String text) {
/* 18 */     super(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public RegisterableAction(String text, String description, Icon icon) {
/* 23 */     super(text, description, icon);
/*    */   }
/*    */ 
/*    */   
/*    */   public final void register() {
/* 28 */     ActionManager actionManager = ActionManager.getInstance();
/* 29 */     String actionId = getActionRegistrationId();
/* 30 */     if (actionManager.getAction(actionId) == null)
/*    */     {
/* 32 */       actionManager.registerAction(actionId, this);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public final void unregister() {
/* 38 */     ActionManager actionManager = ActionManager.getInstance();
/* 39 */     String actionId = getActionRegistrationId();
/* 40 */     if (actionManager.getAction(actionId) != null)
/*    */     {
/* 42 */       actionManager.unregisterAction(actionId);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getActionRegistrationId() {
/* 48 */     return getClass().getName();
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/actions/RegisterableAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */