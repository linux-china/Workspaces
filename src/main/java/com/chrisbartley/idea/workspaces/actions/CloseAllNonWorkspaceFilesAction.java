/*    */ package com.chrisbartley.idea.workspaces.actions;
/*    */ 
/*    */ import com.chrisbartley.idea.util.VirtualFileUtils;
/*    */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*    */ import com.intellij.openapi.fileEditor.FileEditorManager;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import com.intellij.openapi.vfs.VirtualFile;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CloseAllNonWorkspaceFilesAction
/*    */   extends BaseWorkspaceAction
/*    */ {
/*    */   public CloseAllNonWorkspaceFilesAction() {
/* 19 */     super("Close All Non-Workspace Files", "Close all files which are not bound to a workspace", (Icon)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(AnActionEvent event) {
/* 24 */     Project project = getProject(event);
/* 25 */     if (project != null) {
/*    */       
/* 27 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
/* 28 */       VirtualFile[] openFiles = fileEditorManager.getOpenFiles();
/* 29 */       if (openFiles.length > 0) {
/*    */         
/* 31 */         Set openNonWorkspacedUrls = getOpenNonWorkspacedUrls(project, openFiles);
/* 32 */         for (Iterator iterator = openNonWorkspacedUrls.iterator(); iterator.hasNext(); ) {
/*    */           
/* 34 */           String url =(String) iterator.next();
/* 35 */           VirtualFileUtils.closeFileByUrl(fileEditorManager, url);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(AnActionEvent event) {
/* 43 */     Project project = getProject(event);
/* 44 */     if (project != null) {
/*    */       
/* 46 */       VirtualFile[] openFiles = FileEditorManager.getInstance(project).getOpenFiles();
/* 47 */       if (openFiles.length > 0)
/*    */       {
/* 49 */         Set openNonWorkspacedUrls = getOpenNonWorkspacedUrls(project, openFiles);
/* 50 */         event.getPresentation().setEnabled((openNonWorkspacedUrls.size() > 0));
/*    */       }
/*    */       else
/*    */       {
/* 54 */         event.getPresentation().setEnabled(false);
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 59 */       event.getPresentation().setEnabled(false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private Set getOpenNonWorkspacedUrls(Project project, VirtualFile[] openFiles) {
/* 65 */     Set openUrls = new HashSet(VirtualFileUtils.getUrls(openFiles));
/* 66 */     openUrls.removeAll(getWorkspaceManager(project).getBoundFileUrls());
/* 67 */     return openUrls;
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/actions/CloseAllNonWorkspaceFilesAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */