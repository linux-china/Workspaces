/*     */ package com.chrisbartley.idea.workspaces;
/*     */ 
/*     */ import com.chrisbartley.idea.util.JDOMExternalizableList;
/*     */ import com.chrisbartley.idea.util.VirtualFileUtils;
/*     */ import com.intellij.openapi.fileEditor.FileEditorManager;
/*     */ import com.intellij.openapi.util.DefaultJDOMExternalizer;
/*     */ import com.intellij.openapi.util.InvalidDataException;
/*     */ import com.intellij.openapi.util.JDOMExternalizable;
/*     */ import com.intellij.openapi.util.WriteExternalException;
/*     */ import com.intellij.openapi.vfs.VirtualFile;
/*     */ import com.intellij.openapi.vfs.VirtualFileManager;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import org.jdom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Workspace
/*     */   implements JDOMExternalizable
/*     */ {
/*     */   public String name;
/*  27 */   public JDOMExternalizableList fileUrls = new JDOMExternalizableList();
/*     */ 
/*     */   
/*     */   public boolean isPinned;
/*     */ 
/*     */   
/*     */   public Workspace() {}
/*     */ 
/*     */   
/*     */   public Workspace(String name, List fileUrls, boolean isPinned) {
/*  37 */     this.name = name;
/*  38 */     this.fileUrls.addAll(fileUrls);
/*  39 */     this.isPinned = isPinned;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  44 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getFileUrls() {
/*  49 */     return Collections.unmodifiableList((List)this.fileUrls);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPinned() {
/*  54 */     return this.isPinned;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPinned(boolean pinned) {
/*  59 */     this.isPinned = pinned;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(String newName, List newUrls) {
/*  64 */     if (newName != null && newName.length() > 0)
/*     */     {
/*  66 */       this.name = newName;
/*     */     }
/*  68 */     if (newUrls != null && newUrls.size() > 0) {
/*     */       
/*  70 */       this.fileUrls.clear();
/*  71 */       this.fileUrls.addAll(newUrls);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(Element parentElement) throws InvalidDataException {
/*  77 */     DefaultJDOMExternalizer.readExternal(this, parentElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(Element parentElement) throws WriteExternalException {
/*  82 */     DefaultJDOMExternalizer.writeExternal(this, parentElement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(FileEditorManager fileEditorManager, boolean selectFirstFile, boolean willAutoPin) {
/*  91 */     if (willAutoPin)
/*     */     {
/*  93 */       this.isPinned = true;
/*     */     }
/*  95 */     if (this.fileUrls.size() > 0) {
/*     */ 
/*     */       
/*  98 */       VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
/*  99 */       for (ListIterator listIterator = this.fileUrls.listIterator(); listIterator.hasNext();)
/*     */       {
/* 101 */         openFile((String) listIterator.next(), virtualFileManager, fileEditorManager);
/*     */       }
/*     */ 
/*     */       
/* 105 */       if (selectFirstFile)
/*     */       {
/* 107 */         openFile((String)this.fileUrls.get(0), virtualFileManager, fileEditorManager);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void openFile(String url, VirtualFileManager virtualFileManager, FileEditorManager fileEditorManager) {
/* 114 */     VirtualFile virtualFile = virtualFileManager.findFileByUrl(url);
/* 115 */     fileEditorManager.openFile(virtualFile, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close(FileEditorManager fileEditorManager, Set urlsNotToBeClosed, boolean willAutoUnpin) {
/* 121 */     if (willAutoUnpin)
/*     */     {
/* 123 */       this.isPinned = false;
/*     */     }
/* 125 */     for (ListIterator listIterator = this.fileUrls.listIterator(); listIterator.hasNext(); ) {
/*     */       
/* 127 */       String url =(String) listIterator.next();
/* 128 */       if (urlsNotToBeClosed == null || !urlsNotToBeClosed.contains(url))
/*     */       {
/* 130 */         VirtualFileUtils.closeFileByUrl(fileEditorManager, url);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(VirtualFile[] filesToCheck) {
/* 137 */     if (filesToCheck != null && filesToCheck.length != 0) {
/*     */       
/* 139 */       Set fileUrlsSet = new HashSet((Collection)this.fileUrls);
/* 140 */       for (int i = 0; i < filesToCheck.length; i++) {
/*     */         
/* 142 */         if (fileUrlsSet.contains(filesToCheck[i].getUrl()))
/*     */         {
/* 144 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorkspaceState getState(FileEditorManager fileEditorManager) {
/* 153 */     return new WorkspaceState(getOpenFileUrls(fileEditorManager), this.fileUrls.size(), this.isPinned);
/*     */   }
/*     */ 
/*     */   
/*     */   private Set getOpenFileUrls(FileEditorManager fileEditorManager) {
/* 158 */     Set boundUrls = new HashSet();
/* 159 */     VirtualFile[] openFiles = fileEditorManager.getOpenFiles();
/* 160 */     if (openFiles != null && openFiles.length > 0) {
/*     */       
/* 162 */       boundUrls.addAll(VirtualFileUtils.getUrls(openFiles));
/* 163 */       boundUrls.retainAll((Collection)this.fileUrls);
/*     */     } 
/* 165 */     return boundUrls;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 170 */     return getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 175 */     if (this == o)
/*     */     {
/* 177 */       return true;
/*     */     }
/* 179 */     if (!(o instanceof Workspace))
/*     */     {
/* 181 */       return false;
/*     */     }
/*     */     
/* 184 */     Workspace workspace = (Workspace)o;
/*     */     
/* 186 */     if (this.isPinned != workspace.isPinned)
/*     */     {
/* 188 */       return false;
/*     */     }
/* 190 */     if ((this.fileUrls != null) ? !this.fileUrls.equals(workspace.fileUrls) : (workspace.fileUrls != null))
/*     */     {
/* 192 */       return false;
/*     */     }
/* 194 */     if ((this.name != null) ? !this.name.equals(workspace.name) : (workspace.name != null))
/*     */     {
/* 196 */       return false;
/*     */     }
/*     */     
/* 199 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 205 */     int result = (this.name != null) ? this.name.hashCode() : 0;
/* 206 */     result = 29 * result + ((this.fileUrls != null) ? this.fileUrls.hashCode() : 0);
/* 207 */     result = 29 * result + (this.isPinned ? 1 : 0);
/* 208 */     return result;
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/Workspace.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */