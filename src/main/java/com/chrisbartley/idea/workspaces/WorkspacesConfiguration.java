/*     */ package com.chrisbartley.idea.workspaces;
/*     */ 
/*     */ import com.intellij.openapi.components.ApplicationComponent;
/*     */ import com.intellij.openapi.util.DefaultJDOMExternalizer;
/*     */ import com.intellij.openapi.util.InvalidDataException;
/*     */ import com.intellij.openapi.util.NamedJDOMExternalizable;
/*     */ import com.intellij.openapi.util.WriteExternalException;
/*     */ import org.jdom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorkspacesConfiguration
/*     */   implements ApplicationComponent, NamedJDOMExternalizable
/*     */ {
/*     */   private static final String COMPONENT_NAME = "WorkspacesConfiguration";
/*     */   private static final String EXTERNALIZED_FILENAME = "workspaces";
/*     */   public boolean isDisplayMainMenuUI = true;
/*     */   public boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu = true;
/*     */   public boolean isDisplayToolWindowUI = true;
/*     */   public boolean isDisplayButtonActionsInContextMenu = true;
/*     */   public boolean isAutoPin = true;
/*     */   public boolean isAutoPinUponExplicitOpenOnly = true;
/*     */   public boolean isAutoPinUponCreateWorkspace = true;
/*     */   public boolean isAutoUnpin = true;
/*     */   public boolean isAutoUnpinUponExplicitCloseOnly = true;
/*     */   
/*     */   public String getComponentName() {
/*  40 */     return "WorkspacesConfiguration";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initComponent() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void disposeComponent() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDisplayMainMenuUI() {
/*  55 */     return this.isDisplayMainMenuUI;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayMainMenuUI(boolean displayMainMenuUI) {
/*  60 */     this.isDisplayMainMenuUI = displayMainMenuUI;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPinAnUnpinnedOpenWorkspaceSelectedFromMenu() {
/*  65 */     return this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPinAnUnpinnedOpenWorkspaceSelectedFromMenu(boolean pinAnUnpinnedOpenWorkspaceSelectedFromMenu) {
/*  70 */     this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu = pinAnUnpinnedOpenWorkspaceSelectedFromMenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDisplayToolWindowUI() {
/*  75 */     return this.isDisplayToolWindowUI;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayToolWindowUI(boolean displayToolWindowUI) {
/*  80 */     this.isDisplayToolWindowUI = displayToolWindowUI;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDisplayButtonActionsInContextMenu() {
/*  85 */     return this.isDisplayButtonActionsInContextMenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayButtonActionsInContextMenu(boolean displayButtonActionsInContextMenu) {
/*  90 */     this.isDisplayButtonActionsInContextMenu = displayButtonActionsInContextMenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAutoPin() {
/*  95 */     return this.isAutoPin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoPin(boolean autoPin) {
/* 100 */     this.isAutoPin = autoPin;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAutoPinUponExplicitOpenOnly() {
/* 105 */     return this.isAutoPinUponExplicitOpenOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoPinUponExplicitOpenOnly(boolean autoPinUponExplicitOpenOnly) {
/* 110 */     this.isAutoPinUponExplicitOpenOnly = autoPinUponExplicitOpenOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAutoPinUponCreateWorkspace() {
/* 115 */     return this.isAutoPinUponCreateWorkspace;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoPinUponCreateWorkspace(boolean autoPinUponCreateWorkspace) {
/* 120 */     this.isAutoPinUponCreateWorkspace = autoPinUponCreateWorkspace;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAutoUnpin() {
/* 125 */     return this.isAutoUnpin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoUnpin(boolean autoUnpin) {
/* 130 */     this.isAutoUnpin = autoUnpin;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAutoUnpinUponExplicitCloseOnly() {
/* 135 */     return this.isAutoUnpinUponExplicitCloseOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAutoUnpinUponExplicitCloseOnly(boolean autoUnpinUponExplicitCloseOnly) {
/* 140 */     this.isAutoUnpinUponExplicitCloseOnly = autoUnpinUponExplicitCloseOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(Element parentElement) throws InvalidDataException {
/* 145 */     DefaultJDOMExternalizer.readExternal(this, parentElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(Element parentElement) throws WriteExternalException {
/* 150 */     DefaultJDOMExternalizer.writeExternal(this, parentElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getExternalFileName() {
/* 155 */     return "workspaces";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object bj) {
/* 160 */     if (this == bj)
/*     */     {
/* 162 */       return true;
/*     */     }
/* 164 */     if (!(bj instanceof WorkspacesConfiguration))
/*     */     {
/* 166 */       return false;
/*     */     }
/*     */     
/* 169 */     WorkspacesConfiguration workspacesConfiguration = (WorkspacesConfiguration)bj;
/*     */     
/* 171 */     if (this.isAutoPin != workspacesConfiguration.isAutoPin())
/*     */     {
/* 173 */       return false;
/*     */     }
/* 175 */     if (this.isAutoPinUponCreateWorkspace != workspacesConfiguration.isAutoPinUponCreateWorkspace())
/*     */     {
/* 177 */       return false;
/*     */     }
/* 179 */     if (this.isAutoPinUponExplicitOpenOnly != workspacesConfiguration.isAutoPinUponExplicitOpenOnly())
/*     */     {
/* 181 */       return false;
/*     */     }
/* 183 */     if (this.isAutoUnpin != workspacesConfiguration.isAutoUnpin())
/*     */     {
/* 185 */       return false;
/*     */     }
/* 187 */     if (this.isAutoUnpinUponExplicitCloseOnly != workspacesConfiguration.isAutoUnpinUponExplicitCloseOnly())
/*     */     {
/* 189 */       return false;
/*     */     }
/* 191 */     if (this.isDisplayButtonActionsInContextMenu != workspacesConfiguration.isDisplayButtonActionsInContextMenu())
/*     */     {
/* 193 */       return false;
/*     */     }
/* 195 */     if (this.isDisplayMainMenuUI != workspacesConfiguration.isDisplayMainMenuUI())
/*     */     {
/* 197 */       return false;
/*     */     }
/* 199 */     if (this.isDisplayToolWindowUI != workspacesConfiguration.isDisplayToolWindowUI())
/*     */     {
/* 201 */       return false;
/*     */     }
/* 203 */     if (this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu != workspacesConfiguration.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu())
/*     */     {
/* 205 */       return false;
/*     */     }
/*     */     
/* 208 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 214 */     int result = this.isDisplayMainMenuUI ? 1 : 0;
/* 215 */     result = 29 * result + (this.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu ? 1 : 0);
/* 216 */     result = 29 * result + (this.isDisplayToolWindowUI ? 1 : 0);
/* 217 */     result = 29 * result + (this.isDisplayButtonActionsInContextMenu ? 1 : 0);
/* 218 */     result = 29 * result + (this.isAutoPin ? 1 : 0);
/* 219 */     result = 29 * result + (this.isAutoPinUponExplicitOpenOnly ? 1 : 0);
/* 220 */     result = 29 * result + (this.isAutoPinUponCreateWorkspace ? 1 : 0);
/* 221 */     result = 29 * result + (this.isAutoUnpin ? 1 : 0);
/* 222 */     result = 29 * result + (this.isAutoUnpinUponExplicitCloseOnly ? 1 : 0);
/* 223 */     return result;
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspacesConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */