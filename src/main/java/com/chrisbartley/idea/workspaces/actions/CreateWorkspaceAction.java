/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.VirtualFileUtils;
/*    */ import com.chrisbartley.idea.workspaces.Icons;
/*    */ import com.chrisbartley.idea.workspaces.WorkspaceManager;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.actionSystem.Presentation;
/*    */ import com.intellij.openapi.fileEditor.FileEditorManager;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import com.intellij.openapi.vfs.VirtualFile;
/*    */ import java.util.Set;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CreateWorkspaceAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   public CreateWorkspaceAction() {
/* 21 */     super("Create New from Open File(s)...", "Create a new workspace from some or all of the currently open files", (Icon)null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 29 */     Project project = getProject(event);
/* 30 */     if (project != null) {
/*    */       
/* 32 */       WorkspaceManager workspaceManager = getWorkspaceManager(project);
/* 33 */       Set boundFileUrls = workspaceManager.getBoundFileUrls();
/* 34 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
/*    */       
/* 36 */       CreateWorkspaceDialog dialog = new CreateWorkspaceDialog("Create a New Workspace", "Create", boundFileUrls, VirtualFileUtils.getUrls(fileEditorManager.getOpenFiles()));
/* 37 */       dialog.pack();
/* 38 */       dialog.show();
/* 39 */       if (dialog.getExitCode() == 0)
/*    */       {
/* 41 */         workspaceManager.createWorkspace(dialog.getNewWorkspaceName(), dialog.getNewWorkspaceUrls());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 51 */     Project project = getProject(event);
/* 52 */     Presentation presentation = event.getPresentation();
/* 53 */     if (project != null) {
/*    */       String text, description;
/* 55 */       VirtualFile[] openFiles = FileEditorManager.getInstance(project).getOpenFiles();
/*    */ 
/*    */       
/* 58 */       switch (openFiles.length) {
/*    */ 
/*    */         
/*    */         case 0:
/* 62 */           text = "Create New from Open File(s)...";
/* 63 */           description = "Create a new workspace from some or all of the currently open files";
/*    */           break;
/*    */ 
/*    */         
/*    */         case 1:
/* 68 */           text = "Create New from Open File...";
/* 69 */           description = "Create a new workspace from the currently open file";
/*    */           break;
/*    */ 
/*    */         
/*    */         default:
/* 74 */           text = "Create New from Open Files...";
/* 75 */           description = "Create a new workspace from some or all of the currently open files";
/*    */           break;
/*    */       } 
/*    */       
/* 79 */       presentation.setText(text);
/* 80 */       presentation.setDescription(description);
/* 81 */       presentation.setEnabled((openFiles != null && openFiles.length > 0));
/*    */     }
/*    */     else {
/*    */       
/* 85 */       presentation.setText("Create New from Open File(s)...");
/* 86 */       presentation.setDescription("Create a new workspace from some or all of the currently open files");
/* 87 */       presentation.setEnabled(false);
/*    */     } 
/*    */ 
/*    */     
/* 91 */     if ("WORKSPACES_TOOL_WINDOW".equals(event.getPlace()))
/*    */     {
/* 93 */       presentation.setIcon(Icons.ADD_WORKSPACE);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/CreateWorkspaceAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */