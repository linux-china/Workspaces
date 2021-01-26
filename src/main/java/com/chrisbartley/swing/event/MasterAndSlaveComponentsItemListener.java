/*    */ package com.chrisbartley.swing.event;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.event.ItemEvent;
/*    */ import java.awt.event.ItemListener;
/*    */ import javax.swing.AbstractButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MasterAndSlaveComponentsItemListener
/*    */   implements ItemListener
/*    */ {
/*    */   private final AbstractButton masterComponent;
/*    */   private final Component[] slaveComponents;
/*    */   
/*    */   public MasterAndSlaveComponentsItemListener(AbstractButton masterComponent, Component[] slaveComponents) {
/* 18 */     this.masterComponent = masterComponent;
/* 19 */     this.slaveComponents = slaveComponents;
/*    */   }
/*    */ 
/*    */   
/*    */   public void itemStateChanged(ItemEvent e) {
/* 24 */     for (int i = 0; i < this.slaveComponents.length; i++)
/*    */     {
/* 26 */       this.slaveComponents[i].setEnabled(this.masterComponent.isSelected());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/swing/event/MasterAndSlaveComponentsItemListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */