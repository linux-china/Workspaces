/*    */ package com.chrisbartley.idea.workspaces;
/*    */ 
/*    */ import com.chrisbartley.idea.actions.MoveDownAction;
/*    */ import com.chrisbartley.idea.actions.MoveUpAction;
/*    */ import com.chrisbartley.idea.util.RefreshableListModel;
/*    */ import com.chrisbartley.idea.workspaces.actions.CloseAllWorkspacesExceptThisAction;
/*    */ import com.chrisbartley.idea.workspaces.actions.CloseWorkspaceAction;
/*    */ import com.chrisbartley.idea.workspaces.actions.ConfigureWorkspaceAction;
/*    */ import com.chrisbartley.idea.workspaces.actions.CreateWorkspaceAction;
/*    */ import com.chrisbartley.idea.workspaces.actions.OpenWorkspaceAction;
/*    */ import com.chrisbartley.idea.workspaces.actions.RefreshListModelActionGroup;
/*    */ import com.chrisbartley.idea.workspaces.actions.RemoveWorkspaceAction;
/*    */ import com.chrisbartley.idea.workspaces.actions.ToggleWorkspacePinAction;
/*    */ import com.intellij.openapi.actionSystem.ActionGroup;
/*    */ import com.intellij.openapi.actionSystem.ActionManager;
/*    */ import com.intellij.openapi.actionSystem.ActionPopupMenu;
/*    */ import com.intellij.openapi.actionSystem.ActionToolbar;
/*    */ import com.intellij.openapi.actionSystem.AnAction;
/*    */ import com.intellij.openapi.actionSystem.DefaultActionGroup;
/*    */ import com.intellij.openapi.actionSystem.Separator;
/*    */ import com.intellij.openapi.application.ApplicationManager;
/*    */ import com.intellij.openapi.project.Project;
/*    */ import java.awt.BorderLayout;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ 
/*    */ 
/*    */ final class WorkspacesToolWindow
/*    */   extends JPanel
/*    */ {
/*    */   public WorkspacesToolWindow(Project project, WorkspacesConfiguration workspacesConfiguration, JList jList) {
/* 33 */     super(new BorderLayout());
/*    */ 
/*    */     
/* 36 */     RefreshListModelActionGroup toolbarGroup = new RefreshListModelActionGroup((RefreshableListModel)jList.getModel());
/* 37 */     OpenWorkspaceAction openWorkspaceAction = new OpenWorkspaceAction(jList);
/* 38 */     CloseWorkspaceAction closeWorkspaceAction = new CloseWorkspaceAction(jList);
/* 39 */     ToggleWorkspacePinAction toggleWorkspacePinnedAction = new ToggleWorkspacePinAction(jList);
/* 40 */     ConfigureWorkspaceAction configureWorkspaceAction = new ConfigureWorkspaceAction(jList);
/* 41 */     RemoveWorkspaceAction removeAction = new RemoveWorkspaceAction(jList, true);
/* 42 */     toolbarGroup.add((AnAction)openWorkspaceAction);
/* 43 */     toolbarGroup.add((AnAction)closeWorkspaceAction);
/* 44 */     toolbarGroup.add((AnAction)toggleWorkspacePinnedAction);
/* 45 */     toolbarGroup.add((AnAction)new CreateWorkspaceAction());
/* 46 */     toolbarGroup.add((AnAction)removeAction);
/* 47 */     toolbarGroup.add((AnAction)new MoveUpAction(jList));
/* 48 */     toolbarGroup.add((AnAction)new MoveDownAction(jList));
/* 49 */     toolbarGroup.add((AnAction)configureWorkspaceAction);
/* 50 */     ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("WORKSPACES_TOOL_WINDOW", (ActionGroup)toolbarGroup, true);
/*    */ 
/*    */     
/* 53 */     DefaultActionGroup popupActionGroup = new DefaultActionGroup("WorkspacesPopup", true);
/* 54 */     WorkspacesConfigurable workspacesConfigurable = (WorkspacesConfigurable)ApplicationManager.getApplication().getComponent(WorkspacesConfigurable.class);
/* 55 */     if (workspacesConfiguration.isDisplayButtonActionsInContextMenu()) {
/*    */       
/* 57 */       popupActionGroup.add((AnAction)openWorkspaceAction);
/* 58 */       popupActionGroup.add((AnAction)closeWorkspaceAction);
/* 59 */       popupActionGroup.add((AnAction)toggleWorkspacePinnedAction);
/* 60 */       popupActionGroup.add((AnAction)Separator.getInstance());
/*    */     } 
/* 62 */     CloseAllWorkspacesExceptThisAction closeAllWorkspacesExceptThisAction = new CloseAllWorkspacesExceptThisAction(jList);
/* 63 */     popupActionGroup.add((AnAction)closeAllWorkspacesExceptThisAction);
/* 64 */     popupActionGroup.add((AnAction)workspacesConfigurable.getCloseAllWorkspacesAction());
/* 65 */     popupActionGroup.add((AnAction)workspacesConfigurable.getCloseAllNonWorkspaceFilesAction());
/* 66 */     if (workspacesConfiguration.isDisplayButtonActionsInContextMenu()) {
/*    */       
/* 68 */       popupActionGroup.add((AnAction)Separator.getInstance());
/* 69 */       popupActionGroup.add((AnAction)removeAction);
/* 70 */       popupActionGroup.add((AnAction)Separator.getInstance());
/* 71 */       popupActionGroup.add((AnAction)configureWorkspaceAction);
/*    */     } 
/* 73 */     ActionPopupMenu popup = ActionManager.getInstance().createActionPopupMenu("WORKSPACES_TOOL_WINDOW_POPUP", (ActionGroup)popupActionGroup);
/*    */     
/* 75 */     jList.addMouseListener(new WorkspacesToolWindowMouseListener(jList, project, popup.getComponent()));
/*    */ 
/*    */     
/* 78 */     add(toolbar.getComponent(), "North");
/* 79 */     add(new JScrollPane(jList), "Center");
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspacesToolWindow.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */