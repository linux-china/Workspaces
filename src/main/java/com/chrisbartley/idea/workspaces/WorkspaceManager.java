/*     */ package com.chrisbartley.idea.workspaces;
/*     */ 
/*     */ import com.chrisbartley.idea.actions.RegisterableAction;
/*     */ import com.chrisbartley.idea.util.JDOMExternalizableList;
/*     */ import com.chrisbartley.idea.util.ReorderableListModel;
/*     */ import com.chrisbartley.idea.workspaces.actions.CloseAllWorkspacesExceptThisAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.ConfigureWorkspaceAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.RemoveWorkspaceAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.ToggleWorkspaceOpennessAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.ToggleWorkspacePinAction;
/*     */ import com.intellij.openapi.application.ApplicationManager;
/*     */ import com.intellij.openapi.components.ProjectComponent;
/*     */ import com.intellij.openapi.fileEditor.FileEditorManager;
/*     */ import com.intellij.openapi.project.Project;
/*     */ import com.intellij.openapi.util.DefaultJDOMExternalizer;
/*     */ import com.intellij.openapi.util.InvalidDataException;
/*     */ import com.intellij.openapi.util.JDOMExternalizable;
/*     */ import com.intellij.openapi.util.WriteExternalException;
/*     */ import com.intellij.openapi.wm.ToolWindow;
/*     */ import com.intellij.openapi.wm.ToolWindowAnchor;
/*     */ import com.intellij.openapi.wm.ToolWindowManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListModel;
/*     */ import org.jdom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorkspaceManager
/*     */   implements ProjectComponent, JDOMExternalizable
/*     */ {
/*     */   private static final String COMPONENT_NAME = "WorkspaceManager";
/*     */   private static final String TOOL_WINDOW_ID = "Workspaces";
/*  42 */   public JDOMExternalizableList workspaces = new JDOMExternalizableList();
/*     */   
/*     */   private WorkspacesConfiguration workspacesConfiguration;
/*     */   private final Project project;
/*     */   private ReorderableListModel workspacesModel;
/*  47 */   private final JList jList = new JList();
/*  48 */   private final List toggleWorkspaceOpennessActions = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   public WorkspaceManager(Project project) {
/*  53 */     this.project = project;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComponentName() {
/*  58 */     return "WorkspaceManager";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initComponent() {
/*  64 */     this.workspacesConfiguration = (WorkspacesConfiguration)ApplicationManager.getApplication().getComponent(WorkspacesConfiguration.class);
/*     */ 
/*     */     
/*  67 */     this.workspacesModel = new ReorderableListModel((List)this.workspaces);
/*  68 */     this.jList.setModel((ListModel)this.workspacesModel);
/*  69 */     this.jList.setSelectionMode(2);
/*  70 */     this.jList.setCellRenderer(new WorkspacesToolWindowListCellRenderer(this.project));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void disposeComponent() {}
/*     */ 
/*     */   
/*     */   public void projectOpened() {
/*  79 */     if (this.workspacesConfiguration.isDisplayToolWindowUI())
/*     */     {
/*  81 */       showHideToolWindow(true);
/*     */     }
/*  83 */     buildActionsForMutableActionGroups();
/*     */   }
/*     */ 
/*     */   
/*     */   public void projectClosed() {
/*  88 */     showHideToolWindow(false);
/*  89 */     unregisterActionsForMutableActionGroups();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateImplicitAutoPinningAndUnpinning() {
/*  94 */     boolean isAutoPinImplicitly = (this.workspacesConfiguration.isAutoPin() && !this.workspacesConfiguration.isAutoPinUponExplicitOpenOnly());
/*  95 */     boolean isAutoUnpinImplicitly = (this.workspacesConfiguration.isAutoUnpin() && !this.workspacesConfiguration.isAutoUnpinUponExplicitCloseOnly());
/*  96 */     if (isAutoPinImplicitly || isAutoUnpinImplicitly) {
/*     */       
/*  98 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/*  99 */       for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext(); ) {
/*     */         
/* 101 */         Workspace workspace = (Workspace) listIterator.next();
/* 102 */         WorkspaceState workspaceState = workspace.getState(fileEditorManager);
/* 103 */         if (isAutoPinImplicitly && workspaceState.isOpen())
/*     */         {
/* 105 */           workspace.setPinned(true);
/*     */         }
/* 107 */         if (isAutoUnpinImplicitly && workspaceState.isClosed())
/*     */         {
/* 109 */           workspace.setPinned(false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void showHideToolWindow(boolean show) {
/* 117 */     ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(this.project);
/* 118 */     if (show) {
/*     */       
/* 120 */       registerToolWindow(toolWindowManager);
/*     */     }
/*     */     else {
/*     */       
/* 124 */       unregisterToolWindow(toolWindowManager);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerToolWindow(ToolWindowManager toolWindowManager) {
/* 130 */     if (toolWindowManager.getToolWindow("Workspaces") == null) {
/*     */       
/* 132 */       ToolWindow workspacesToolWindow = toolWindowManager.registerToolWindow("Workspaces", new WorkspacesToolWindow(this.project, this.workspacesConfiguration, this.jList), ToolWindowAnchor.LEFT);
/* 133 */       workspacesToolWindow.setIcon(Icons.WORKSPACES);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void unregisterToolWindow(ToolWindowManager toolWindowManager) {
/* 139 */     if (toolWindowManager.getToolWindow("Workspaces") != null)
/*     */     {
/* 141 */       toolWindowManager.unregisterToolWindow("Workspaces");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Set getBoundFileUrls() {
/* 147 */     Set fileUrls = new HashSet();
/* 148 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext(); ) {
/*     */       
/* 150 */       Workspace workspace = (Workspace) listIterator.next();
/* 151 */       fileUrls.addAll(workspace.getFileUrls());
/*     */     } 
/* 153 */     return Collections.unmodifiableSet(fileUrls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readExternal(Element parentElement) throws InvalidDataException {
/* 158 */     DefaultJDOMExternalizer.readExternal(this, parentElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeExternal(Element parentElement) throws WriteExternalException {
/* 163 */     DefaultJDOMExternalizer.writeExternal(this, parentElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWorkspaceCount() {
/* 168 */     return this.workspacesModel.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void createWorkspace(String name, List urls) {
/* 173 */     if (name != null && !urls.isEmpty()) {
/*     */       
/* 175 */       this.workspacesModel.add(new Workspace(name, urls, (this.workspacesConfiguration.isAutoPin() && this.workspacesConfiguration.isAutoPinUponCreateWorkspace())));
/* 176 */       buildActionsForMutableActionGroups();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeWorkspaces(int[] indicesToRemove) {
/* 182 */     int[] selectedIndices = this.jList.getSelectedIndices();
/*     */ 
/*     */     
/* 185 */     for (int i = indicesToRemove.length - 1; i >= 0; i--)
/*     */     {
/* 187 */       this.workspacesModel.remove(indicesToRemove[i]);
/*     */     }
/*     */     
/* 190 */     buildActionsForMutableActionGroups();
/*     */ 
/*     */     
/* 193 */     if (this.workspacesModel.isEmpty()) {
/*     */       
/* 195 */       this.jList.getSelectionModel().clearSelection();
/*     */ 
/*     */     
/*     */     }
/* 199 */     else if (selectedIndices != null && selectedIndices.length > 0) {
/*     */ 
/*     */ 
/*     */       
/* 203 */       if (selectedIndices.equals(indicesToRemove)) {
/*     */         
/* 205 */         int firstSelectedIndex = selectedIndices[0];
/* 206 */         if (firstSelectedIndex == this.workspacesModel.size())
/*     */         {
/* 208 */           firstSelectedIndex--;
/*     */         }
/* 210 */         this.jList.setSelectedIndex(firstSelectedIndex);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 215 */         int[] indicesToRemoveCopy = new int[indicesToRemove.length];
/* 216 */         System.arraycopy(indicesToRemove, 0, indicesToRemoveCopy, 0, indicesToRemove.length);
/*     */ 
/*     */ 
/*     */         
/* 220 */         Arrays.sort(indicesToRemoveCopy);
/* 221 */         int sizeOfNewSelectedIndicesArray = 0;
/* 222 */         for (int j = 0; j < selectedIndices.length; j++) {
/*     */           
/* 224 */           int selectedIndex = selectedIndices[j];
/* 225 */           int pos = Arrays.binarySearch(indicesToRemoveCopy, selectedIndex);
/* 226 */           if (pos >= 0) {
/*     */             
/* 228 */             selectedIndices[j] = -1;
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 233 */             int numberOfSmallerIndices = -(pos + 1);
/* 234 */             selectedIndices[j] = selectedIndices[j] - numberOfSmallerIndices;
/* 235 */             sizeOfNewSelectedIndicesArray++;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 240 */         if (sizeOfNewSelectedIndicesArray > 0) {
/*     */           
/* 242 */           int[] newSelectedIndeces = new int[sizeOfNewSelectedIndicesArray];
/* 243 */           int currentPos = 0;
/* 244 */           for (int k = 0; k < selectedIndices.length; k++) {
/*     */             
/* 246 */             if (selectedIndices[k] != -1)
/*     */             {
/* 248 */               newSelectedIndeces[currentPos++] = selectedIndices[k];
/*     */             }
/*     */           } 
/* 251 */           this.jList.setSelectedIndices(newSelectedIndeces);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildActionsForMutableActionGroups() {
/* 261 */     updateImplicitAutoPinningAndUnpinning();
/*     */ 
/*     */     
/* 264 */     unregisterActionsForMutableActionGroups();
/*     */ 
/*     */     
/* 267 */     this.toggleWorkspaceOpennessActions.clear();
/* 268 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext(); ) {
/*     */       
/* 270 */       ToggleWorkspaceOpennessAction toggleWorkspaceOpennessAction = new ToggleWorkspaceOpennessAction(this.project, (Workspace) listIterator.next());
/* 271 */       toggleWorkspaceOpennessAction.register();
/* 272 */       this.toggleWorkspaceOpennessActions.add(toggleWorkspaceOpennessAction);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void unregisterActionsForMutableActionGroups() {
/* 278 */     unregisterActions(this.toggleWorkspaceOpennessActions);
/*     */   }
/*     */ 
/*     */   
/*     */   private void unregisterActions(List actions) {
/* 283 */     for (ListIterator listIterator = actions.listIterator(); listIterator.hasNext(); ) {
/*     */       
/* 285 */       RegisterableAction action = (RegisterableAction) listIterator.next();
/* 286 */       action.unregister();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void openWorkspace(Workspace workspace) {
/* 292 */     if (workspace != null) {
/*     */       
/* 294 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/* 295 */       workspace.open(fileEditorManager, true, this.workspacesConfiguration.isAutoPin());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void openWorkspaces(List workspacesToOpen) {
/* 301 */     if (workspacesToOpen != null && workspacesToOpen.size() > 0) {
/*     */       
/* 303 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/* 304 */       int i = 1;
/* 305 */       for (ListIterator listIterator = workspacesToOpen.listIterator(); listIterator.hasNext(); ) {
/*     */         
/* 307 */         Workspace workspace =(Workspace) listIterator.next();
/* 308 */         workspace.open(fileEditorManager, (i == workspacesToOpen.size()), this.workspacesConfiguration.isAutoPin());
/* 309 */         i++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void toggleWorkspacePinnedness(List workspacesToToggle) {
/* 316 */     if (workspacesToToggle != null && workspacesToToggle.size() > 0)
/*     */     {
/* 318 */       for (ListIterator listIterator = workspacesToToggle.listIterator(); listIterator.hasNext();)
/*     */       {
/* 320 */         toggleWorkspacePinnedness((Workspace) listIterator.next());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void toggleWorkspacePinnedness(Workspace workspace) {
/* 327 */     if (workspace != null)
/*     */     {
/* 329 */       workspace.setPinned(!workspace.isPinned());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void toggleWorkspaceOpenness(Workspace workspace) {
/* 335 */     if (workspace != null) {
/*     */       
/* 337 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/* 338 */       WorkspaceState workspaceState = workspace.getState(fileEditorManager);
/* 339 */       if (workspaceState.isOpen()) {
/*     */         
/* 341 */         if (workspaceState.isPinned())
/*     */         {
/* 343 */           closeWorkspace(workspace);
/*     */ 
/*     */         
/*     */         }
/* 347 */         else if (this.workspacesConfiguration.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu())
/*     */         {
/* 349 */           workspace.setPinned(true);
/*     */         }
/*     */         else
/*     */         {
/* 353 */           closeWorkspace(workspace);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 359 */         openWorkspace(workspace);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToggleWorkspaceOpennessDescription(Workspace workspace) {
/* 367 */     if (workspace != null) {
/*     */       String actionStr;
/* 369 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/* 370 */       WorkspaceState workspaceState = workspace.getState(fileEditorManager);
/* 371 */       if (workspaceState.isOpen()) {
/*     */         
/* 373 */         if (workspaceState.isPinned())
/*     */         {
/* 375 */           actionStr = "Close";
/*     */ 
/*     */         
/*     */         }
/* 379 */         else if (this.workspacesConfiguration.isPinAnUnpinnedOpenWorkspaceSelectedFromMenu())
/*     */         {
/* 381 */           actionStr = "Pin";
/*     */         }
/*     */         else
/*     */         {
/* 385 */           actionStr = "Close";
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 391 */         actionStr = "Open";
/*     */       } 
/* 393 */       return actionStr + " the '" + workspace.getName() + "' workspace";
/*     */     } 
/* 395 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeWorkspace(Workspace workspace) {
/* 400 */     if (workspace != null) {
/*     */       
/* 402 */       Set workspacesToClose = new HashSet();
/* 403 */       workspacesToClose.add(workspace);
/* 404 */       closeWorkspaces(workspacesToClose);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeWorkspaces(Set workspacesToClose) {
/* 410 */     if (workspacesToClose != null && workspacesToClose.size() > 0) {
/*     */       
/* 412 */       FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/* 413 */       Set urlsNotToBeClosed = getUrlsNotToBeClosed(workspacesToClose);
/* 414 */       for (Iterator iterator = workspacesToClose.iterator(); iterator.hasNext(); ) {
/*     */         
/* 416 */         Workspace workspace =(Workspace) iterator.next();
/* 417 */         workspace.close(fileEditorManager, urlsNotToBeClosed, this.workspacesConfiguration.isAutoUnpin());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeAllWorkspaces() {
/* 424 */     FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/* 425 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext(); ) {
/*     */       
/* 427 */       Workspace workspace = (Workspace) listIterator.next();
/* 428 */       workspace.close(fileEditorManager, null, this.workspacesConfiguration.isAutoUnpin());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeAllButThisWorkspace(Workspace workspace) {
/* 434 */     Set workspacesToBeClosed = new HashSet((Collection)this.workspacesModel);
/* 435 */     if (workspace != null)
/*     */     {
/* 437 */       workspacesToBeClosed.remove(workspace);
/*     */     }
/* 439 */     closeWorkspaces(workspacesToBeClosed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeAllButTheseWorkspaces(List workspacesNotToClose) {
/* 444 */     Set workspacesToBeClosed = new HashSet((Collection)this.workspacesModel);
/* 445 */     if (workspacesNotToClose != null)
/*     */     {
/* 447 */       workspacesToBeClosed.removeAll(workspacesNotToClose);
/*     */     }
/* 449 */     closeWorkspaces(workspacesToBeClosed);
/*     */   }
/*     */ 
/*     */   
/*     */   private Set getUrlsNotToBeClosed(Set workspacesToClose) {
/* 454 */     Set urlsNotToBeClosed = new HashSet();
/*     */     
/* 456 */     FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.project);
/*     */ 
/*     */     
/* 459 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext(); ) {
/*     */       
/* 461 */       Workspace workspace = (Workspace) listIterator.next();
/* 462 */       if (!workspacesToClose.contains(workspace)) {
/*     */         
/* 464 */         WorkspaceState workspaceState = workspace.getState(fileEditorManager);
/*     */         
/* 466 */         if (workspaceState.isPinned())
/*     */         {
/* 468 */           urlsNotToBeClosed.addAll(workspaceState.getOpenUrls());
/*     */         }
/*     */       } 
/*     */     } 
/* 472 */     return urlsNotToBeClosed;
/*     */   }
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
/*     */   public List getToggleWorkspaceOpennessActions() {
/* 488 */     return this.toggleWorkspaceOpennessActions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCloseAllWorkspacesExceptThisActions() {
/* 494 */     List actions = new ArrayList();
/* 495 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext();)
/*     */     {
/* 497 */       actions.add(new CloseAllWorkspacesExceptThisAction((Workspace) listIterator.next()));
/*     */     }
/*     */     
/* 500 */     return actions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getToggleWorkspacePinActions() {
/* 506 */     List actions = new ArrayList();
/* 507 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext();)
/*     */     {
/* 509 */       actions.add(new ToggleWorkspacePinAction((Workspace) listIterator.next()));
/*     */     }
/*     */     
/* 512 */     return actions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getConfigureWorkspaceActions() {
/* 518 */     List actions = new ArrayList();
/* 519 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext();)
/*     */     {
/* 521 */       actions.add(new ConfigureWorkspaceAction((Workspace) listIterator.next()));
/*     */     }
/*     */     
/* 524 */     return actions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRemoveWorkspaceActions() {
/* 530 */     List actions = new ArrayList();
/* 531 */     for (ListIterator listIterator = this.workspacesModel.listIterator(); listIterator.hasNext();)
/*     */     {
/* 533 */       actions.add(new RemoveWorkspaceAction((Workspace) listIterator.next(), true));
/*     */     }
/*     */     
/* 536 */     return actions;
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Workspace workspace) {
/* 541 */     if (workspace != null)
/*     */     {
/* 543 */       return this.workspacesModel.indexOf(workspace);
/*     */     }
/* 545 */     return -1;
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspaceManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */