/*     */ package com.chrisbartley.idea.workspaces.actions;
/*     */ 
/*     */ import com.chrisbartley.idea.workspaces.Icons;
/*     */ import com.chrisbartley.idea.workspaces.Workspace;
/*     */ import com.chrisbartley.idea.workspaces.WorkspaceManager;
/*     */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*     */ import com.intellij.openapi.project.Project;
/*     */ import com.intellij.openapi.ui.Messages;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RemoveWorkspaceAction
/*     */   extends BaseWorkspaceAction
/*     */ {
/*     */   private static final int YES_RESPONSE = 0;
/*     */   private final Workspace workspace;
/*     */   private final JList list;
/*     */   private final boolean showConfirmation;
/*     */   
/*     */   public RemoveWorkspaceAction(Workspace workspace, boolean showConfirmation) {
/*  28 */     this(workspace.getName(), "Remove '" + workspace.getName() + "'", (JList)null, workspace, showConfirmation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoveWorkspaceAction(JList list, boolean showConfirmation) {
/*  37 */     this("Remove", "Remove the selected workspace(s)", list, (Workspace)null, showConfirmation);
/*     */   }
/*     */ 
/*     */   
/*     */   private RemoveWorkspaceAction(String text, String description, JList list, Workspace workspace, boolean showConfirmation) {
/*  42 */     super(text, description, (Icon)null);
/*  43 */     this.workspace = workspace;
/*  44 */     this.list = list;
/*  45 */     this.showConfirmation = showConfirmation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(AnActionEvent event) {
/*  50 */     Project project = getProject(event);
/*  51 */     if (project != null) {
/*     */ 
/*     */       
/*  54 */       int[] indicesToRemove = null;
/*  55 */       if (this.list != null) {
/*     */         
/*  57 */         if (!this.list.getSelectionModel().isSelectionEmpty())
/*     */         {
/*  59 */           indicesToRemove = this.list.getSelectedIndices();
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/*  64 */         int index = getWorkspaceManager(project).indexOf(this.workspace);
/*  65 */         if (index >= 0)
/*     */         {
/*  67 */           indicesToRemove = new int[] { index };
/*     */         }
/*     */       } 
/*     */       
/*  71 */       if (indicesToRemove != null) {
/*     */         
/*  73 */         int confirmationResult = 0;
/*  74 */         if (this.showConfirmation) {
/*     */           
/*  76 */           String prompt = (indicesToRemove.length == 1) ? "Remove the selected workspace?" : "Remove the selected workspaces?";
/*  77 */           confirmationResult = Messages.showYesNoDialog(project, prompt, "Confirm Remove", Messages.getQuestionIcon());
/*     */         } 
/*  79 */         if (confirmationResult == 0) {
/*     */           
/*  81 */           WorkspaceManager workspaceManager = getWorkspaceManager(project);
/*  82 */           workspaceManager.removeWorkspaces(indicesToRemove);
/*     */ 
/*     */           
/*  85 */           if (workspaceManager.getWorkspaceCount() == 0)
/*     */           {
/*  87 */             event.getPresentation().setEnabled(false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(AnActionEvent event) {
/*  96 */     if (getProject(event) != null) {
/*     */       
/*  98 */       if (this.list != null)
/*     */       {
/* 100 */         event.getPresentation().setEnabled(!this.list.getSelectionModel().isSelectionEmpty());
/*     */       }
/*     */ 
/*     */       
/* 104 */       if ("WORKSPACES_TOOL_WINDOW".equals(event.getPlace()))
/*     */       {
/* 106 */         event.getPresentation().setIcon(Icons.REMOVE_WORKSPACE);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/RemoveWorkspaceAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */