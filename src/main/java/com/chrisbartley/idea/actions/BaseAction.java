/*    */ package com.chrisbartley.idea.actions;
/*    */ 
/*    */ import com.intellij.openapi.actionSystem.AnAction;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BaseAction
/*    */   extends AnAction
/*    */ {
/*    */   public BaseAction() {}
/*    */   
/*    */   public BaseAction(String text) {
/* 21 */     super(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaseAction(String text, String description, Icon icon) {
/* 26 */     super(text, description, icon);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected final Project getProject(AnActionEvent event) {
/* 32 */     return (Project)event.getDataContext().getData("project");
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/actions/BaseAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */