/*    */ package com.chrisbartley.idea.actions;
/*    */ 
/*    */ import com.intellij.openapi.actionSystem.AnAction;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import java.util.List;
/*    */ import java.util.ListIterator;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class MutableActionGroup
/*    */   extends BaseActionGroup
/*    */ {
/*    */   private final MutableActionGroupStrategy strategy;
/*    */   
/*    */   public MutableActionGroup(MutableActionGroupStrategy strategy) {
/* 18 */     this(strategy, (String)null, false, (Icon)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public MutableActionGroup(MutableActionGroupStrategy strategy, String shortName, boolean popup) {
/* 23 */     this(strategy, shortName, popup, (Icon)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public MutableActionGroup(MutableActionGroupStrategy strategy, String shortName, boolean popup, Icon icon) {
/* 28 */     super(shortName, popup);
/* 29 */     this.strategy = strategy;
/* 30 */     getTemplatePresentation().setIcon(icon);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 36 */     removeAll();
/*    */     
/* 38 */     if (getProject(event) != null) {
/*    */ 
/*    */       
/* 41 */       List actions = this.strategy.getActions(event);
/* 42 */       for (ListIterator listIterator = actions.listIterator(); listIterator.hasNext();)
/*    */       {
/* 44 */         add((AnAction) listIterator.next());
/*    */       }
/* 46 */       event.getPresentation().setEnabled((actions != null && actions.size() > 0));
/*    */     }
/*    */     else {
/*    */       
/* 50 */       event.getPresentation().setEnabled(false);
/*    */     } 
/* 52 */     this.strategy.preparePresentation(event);
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/actions/MutableActionGroup.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */