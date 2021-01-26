/*     */ package com.chrisbartley.idea.workspaces;
/*     */ 
/*     */ import java.util.Set;
/*     */ import javax.swing.Icon;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorkspaceState
/*     */ {
/*     */   private final Set openUrls;
/*     */   private final int numOpenFiles;
/*     */   private final int numFiles;
/*     */   private final boolean isPinned;
/*     */   
/*     */   public WorkspaceState(Set openUrls, int numFiles, boolean isPinned) {
/*  18 */     this.openUrls = openUrls;
/*  19 */     this.isPinned = isPinned;
/*  20 */     this.numOpenFiles = openUrls.size();
/*  21 */     this.numFiles = numFiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set getOpenUrls() {
/*  26 */     return this.openUrls;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/*  31 */     return (this.numOpenFiles == this.numFiles);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartiallyOpen() {
/*  36 */     return (this.numOpenFiles > 0 && this.numOpenFiles < this.numFiles);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/*  41 */     return (this.numOpenFiles == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getOpenedStatusIcon() {
/*  46 */     if (this.numOpenFiles > 0)
/*     */     {
/*  48 */       return (this.numOpenFiles == this.numFiles) ? Icons.OPENED : Icons.PARTIALLY_OPENED;
/*     */     }
/*     */ 
/*     */     
/*  52 */     return Icons.UNOPENED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumOpenFiles() {
/*  58 */     return this.numOpenFiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumFiles() {
/*  63 */     return this.numFiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPinned() {
/*  68 */     return this.isPinned;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getPinnedStatusIcon() {
/*  73 */     return this.isPinned ? Icons.PINNED : Icons.UNPINNED;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getComboStatusIcon() {
/*  78 */     if (this.isPinned) {
/*     */       
/*  80 */       if (this.numOpenFiles > 0)
/*     */       {
/*  82 */         return (this.numOpenFiles == this.numFiles) ? Icons.PINNED_AND_OPEN : Icons.PINNED_AND_PARTIALLY_OPEN;
/*     */       }
/*     */ 
/*     */       
/*  86 */       return Icons.PINNED;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  91 */     return getOpenedStatusIcon();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  97 */     StringBuffer str = new StringBuffer();
/*     */     
/*  99 */     str.append("isOpen()          = " + isOpen()).append("\n");
/* 100 */     str.append("isPartiallyOpen() = " + isPartiallyOpen()).append("\n");
/* 101 */     str.append("isClosed()        = " + isClosed()).append("\n");
/* 102 */     str.append("isPinned()        = " + isPinned()).append("\n");
/* 103 */     str.append("num files         = " + getNumFiles()).append("\n");
/* 104 */     str.append("num open files    = " + getNumOpenFiles());
/*     */     
/* 106 */     return str.toString();
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspaceState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */