/*    */ package com.chrisbartley.idea.actions;
/*    */ 
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.actionSystem.DefaultActionGroup;
/*    */ import com.intellij.openapi.project.Project;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BaseActionGroup
/*    */   extends DefaultActionGroup
/*    */ {
/*    */   public BaseActionGroup() {}
/*    */   
/*    */   public BaseActionGroup(String shortName, boolean popup) {
/* 20 */     super(shortName, popup);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected final Project getProject(AnActionEvent event) {
/* 26 */     return (Project)event.getDataContext().getData("project");
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/actions/BaseActionGroup.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */