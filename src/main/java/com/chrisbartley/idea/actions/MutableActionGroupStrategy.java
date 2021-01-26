/*    */ package com.chrisbartley.idea.actions;
/*    */ 
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MutableActionGroupStrategy
/*    */ {
/*    */   public abstract List getActions(AnActionEvent paramAnActionEvent);
/*    */   
/*    */   public void preparePresentation(AnActionEvent event) {}
/*    */   
/*    */   protected final Project getProject(AnActionEvent event) {
/* 23 */     return (Project)event.getDataContext().getData("project");
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/actions/MutableActionGroupStrategy.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */