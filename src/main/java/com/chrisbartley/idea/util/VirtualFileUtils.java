/*    */ package com.chrisbartley.idea.util;
/*    */ 
/*    */ import com.intellij.openapi.fileEditor.FileEditorManager;
/*    */ import com.intellij.openapi.vfs.VirtualFile;
/*    */ import com.intellij.openapi.vfs.VirtualFileManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.ListIterator;
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
/*    */ public final class VirtualFileUtils
/*    */ {
/*    */   public static List getUrls(VirtualFile[] virtualFiles) {
/* 23 */     return getUrls(Arrays.asList(virtualFiles));
/*    */   }
/*    */ 
/*    */   
/*    */   public static List getUrls(List virtualFiles) {
/* 28 */     List urls = new ArrayList();
/* 29 */     if (virtualFiles != null)
/*    */     {
/* 31 */       for (ListIterator listIterator = virtualFiles.listIterator(); listIterator.hasNext(); ) {
/*    */         
/* 33 */         VirtualFile virtualFile = (VirtualFile) listIterator.next();
/* 34 */         urls.add(virtualFile.getUrl());
/*    */       } 
/*    */     }
/* 37 */     return urls;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void closeFileByUrl(FileEditorManager fileEditorManager, String url) {
/* 42 */     if (fileEditorManager != null && url != null) {
/*    */       
/* 44 */       VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(url);
/* 45 */       if (virtualFile != null && fileEditorManager.isFileOpen(virtualFile))
/*    */       {
/* 47 */         fileEditorManager.closeFile(virtualFile);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/util/VirtualFileUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */