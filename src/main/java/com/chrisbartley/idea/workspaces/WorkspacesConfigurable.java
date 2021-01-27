/*     */ package com.chrisbartley.idea.workspaces;
/*     */ 
/*     */ import com.chrisbartley.idea.actions.MutableActionGroup;
/*     */ import com.chrisbartley.idea.actions.MutableActionGroupStrategy;
/*     */ import com.chrisbartley.idea.actions.RegisterableAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.CloseAllNonWorkspaceFilesAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.CloseAllWorkspacesAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.CreateWorkspaceAction;
/*     */ import com.chrisbartley.idea.workspaces.actions.WorkspaceMutableActionGroupStrategy;
/*     */ import com.intellij.openapi.actionSystem.ActionManager;
/*     */ import com.intellij.openapi.actionSystem.AnAction;
/*     */ import com.intellij.openapi.actionSystem.AnActionEvent;
/*     */ import com.intellij.openapi.actionSystem.Anchor;
/*     */ import com.intellij.openapi.actionSystem.Constraints;
/*     */ import com.intellij.openapi.actionSystem.DefaultActionGroup;
/*     */ import com.intellij.openapi.application.ApplicationManager;
/*     */ import com.intellij.openapi.components.ApplicationComponent;
/*     */ import com.intellij.openapi.options.Configurable;
/*     */ import com.intellij.openapi.options.ConfigurationException;
/*     */ import com.intellij.openapi.project.Project;
/*     */ import com.intellij.openapi.project.ProjectManager;
/*     */ import java.util.List;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorkspacesConfigurable
/*     */   implements ApplicationComponent, Configurable
/*     */ {
/*     */   private static final String COMPONENT_NAME = "WorkspacesConfigurable";
/*     */   private static final String DISPLAY_NAME = "Workspaces";
/*  33 */   private static final Constraints WORKSPACES_MENU_PLACEMENT = new Constraints(Anchor.BEFORE, "HelpMenu");
/*     */   
/*     */   private WorkspacesConfiguration workspacesConfiguration;
/*     */   
/*     */   private WorkspacesConfigurationPanel configurationPanel;
/*  38 */   private DefaultActionGroup workspacesMenu = new DefaultActionGroup("Wor_kspaces", false);
/*  39 */   private final MutableActionGroup toggleWorkspaceOpennessActionGroup = new MutableActionGroup((MutableActionGroupStrategy)new ToggleWorkspaceOpennessActionGroup());
/*  40 */   private final MutableActionGroup closeAllWorkspacesExceptThisActionGroup = new MutableActionGroup((MutableActionGroupStrategy)new CloseAllWorkspacesExceptThisActionGroup(), "Close All Workspaces Except", true);
/*     */   
/*  42 */   private final MutableActionGroup togglePinActionGroup = new MutableActionGroup((MutableActionGroupStrategy)new ToggleWorkspacePinActionGroup(), "Toggle Pin", true, Icons.PINNED);
/*  43 */   private final MutableActionGroup configureActionGroup = new MutableActionGroup((MutableActionGroupStrategy)new ConfigureWorkspaceActionGroup(), "Properties", true, Icons.CONFIGURE_WORKSPACE);
/*  44 */   private final MutableActionGroup removeActionGroup = new MutableActionGroup((MutableActionGroupStrategy)new RemoveWorkspaceActionGroup(), "Remove", true);
/*     */   
/*  46 */   private final RegisterableAction createWorkspaceAction = (RegisterableAction)new CreateWorkspaceAction();
/*  47 */   private final RegisterableAction closeAllWorkspacesAction = (RegisterableAction)new CloseAllWorkspacesAction();
/*  48 */   private final RegisterableAction closeAllNonWorkspaceFilesAction = (RegisterableAction)new CloseAllNonWorkspaceFilesAction();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getComponentName() {
/*  57 */     return "WorkspacesConfigurable";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initComponent() {
/*  63 */     this.workspacesConfiguration = ApplicationManager.getApplication().getComponent(WorkspacesConfiguration.class);
/*     */ 
/*     */     
/*  66 */     this.workspacesMenu.add(this.toggleWorkspaceOpennessActionGroup);
/*  67 */     this.workspacesMenu.addSeparator();
/*  68 */     this.workspacesMenu.add(this.closeAllWorkspacesExceptThisActionGroup);
/*  69 */     this.workspacesMenu.add(this.closeAllWorkspacesAction);
/*  70 */     this.workspacesMenu.add(this.closeAllNonWorkspaceFilesAction);
/*  71 */     this.workspacesMenu.addSeparator();
/*  72 */     this.workspacesMenu.add(this.togglePinActionGroup);
/*  73 */     this.workspacesMenu.add(this.configureActionGroup);
/*  74 */     this.workspacesMenu.addSeparator();
/*  75 */     this.workspacesMenu.add(this.removeActionGroup);
/*  76 */     this.workspacesMenu.add(this.createWorkspaceAction);
/*     */ 
/*     */     
/*  79 */     this.createWorkspaceAction.register();
/*  80 */     this.closeAllWorkspacesAction.register();
/*  81 */     this.closeAllNonWorkspaceFilesAction.register();
/*     */ 
/*     */     
/*  84 */     if (this.workspacesConfiguration.isDisplayMainMenuUI())
/*     */     {
/*  86 */       showHideWorkspacesMenu(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void disposeComponent() {
/*  93 */     this.createWorkspaceAction.unregister();
/*  94 */     this.closeAllWorkspacesAction.unregister();
/*  95 */     this.closeAllNonWorkspaceFilesAction.unregister();
/*     */ 
/*     */     
/*  98 */     this.toggleWorkspaceOpennessActionGroup.removeAll();
/*  99 */     this.closeAllWorkspacesExceptThisActionGroup.removeAll();
/* 100 */     this.togglePinActionGroup.removeAll();
/* 101 */     this.configureActionGroup.removeAll();
/* 102 */     this.removeActionGroup.removeAll();
/*     */ 
/*     */     
/* 105 */     this.workspacesMenu.removeAll();
/*     */ 
/*     */     
/* 108 */     if (this.workspacesConfiguration.isDisplayMainMenuUI())
/*     */     {
/* 110 */       showHideWorkspacesMenu(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/* 116 */     return "Workspaces";
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon() {
/* 121 */     return Icons.WORKSPACES_CONFIGURABLE;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpTopic() {
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent createComponent() {
/* 131 */     this.configurationPanel = new WorkspacesConfigurationPanel();
/* 132 */     return this.configurationPanel;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isModified() {
/* 137 */     WorkspacesConfiguration configuration = new WorkspacesConfiguration();
/* 138 */     saveToConfiguration(configuration);
/* 139 */     return !configuration.equals(this.workspacesConfiguration);
/*     */   }
/*     */ 
/*     */   
/*     */   public void apply() throws ConfigurationException {
/* 144 */     saveToConfiguration(this.workspacesConfiguration);
/* 145 */     showHideWorkspacesMenu(this.workspacesConfiguration.isDisplayMainMenuUI());
/* 146 */     showHideToolWindow(this.workspacesConfiguration.isDisplayToolWindowUI());
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveToConfiguration(WorkspacesConfiguration configuration) {
/* 151 */     this.configurationPanel.copyConfigurationTo(configuration);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 156 */     this.configurationPanel.copyConfigurationFrom(this.workspacesConfiguration);
/*     */   }
/*     */ 
/*     */   
/*     */   public void disposeUIResources() {
/* 161 */     this.configurationPanel = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void showHideWorkspacesMenu(boolean show) {
/* 166 */     DefaultActionGroup mainMenu = (DefaultActionGroup)ActionManager.getInstance().getAction("MainMenu");
/*     */ 
/*     */     
/* 169 */     mainMenu.remove((AnAction)this.workspacesMenu);
/*     */     
/* 171 */     if (show)
/*     */     {
/*     */       
/* 174 */       mainMenu.add((AnAction)this.workspacesMenu, WORKSPACES_MENU_PLACEMENT);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void showHideToolWindow(boolean show) {
/* 180 */     Project[] projects = ProjectManager.getInstance().getOpenProjects();
/* 181 */     for (int i = 0; i < projects.length; i++) {
/*     */       
/* 183 */       Project project = projects[i];
/* 184 */       WorkspaceManager workspaceManager = (WorkspaceManager)project.getComponent(WorkspaceManager.class);
/* 185 */       workspaceManager.showHideToolWindow(show);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public RegisterableAction getCloseAllWorkspacesAction() {
/* 191 */     return this.closeAllWorkspacesAction;
/*     */   }
/*     */ 
/*     */   
/*     */   public RegisterableAction getCloseAllNonWorkspaceFilesAction() {
/* 196 */     return this.closeAllNonWorkspaceFilesAction;
/*     */   }
/*     */   
/*     */   private static final class ToggleWorkspaceOpennessActionGroup extends WorkspaceMutableActionGroupStrategy {
/*     */     private ToggleWorkspaceOpennessActionGroup() {}
/*     */     
/*     */     public List getActions(AnActionEvent event) {
/* 203 */       return getWorkspaceManager(event).getToggleWorkspaceOpennessActions();
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class CloseAllWorkspacesExceptThisActionGroup extends WorkspaceMutableActionGroupStrategy {
/*     */     private CloseAllWorkspacesExceptThisActionGroup() {}
/*     */     
/*     */     public List getActions(AnActionEvent event) {
/* 211 */       return getWorkspaceManager(event).getCloseAllWorkspacesExceptThisActions();
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class ToggleWorkspacePinActionGroup extends WorkspaceMutableActionGroupStrategy {
/*     */     private ToggleWorkspacePinActionGroup() {}
/*     */     
/*     */     public List getActions(AnActionEvent event) {
/* 219 */       return getWorkspaceManager(event).getToggleWorkspacePinActions();
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class ConfigureWorkspaceActionGroup extends WorkspaceMutableActionGroupStrategy {
/*     */     private ConfigureWorkspaceActionGroup() {}
/*     */     
/*     */     public List getActions(AnActionEvent event) {
/* 227 */       return getWorkspaceManager(event).getConfigureWorkspaceActions();
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class RemoveWorkspaceActionGroup extends WorkspaceMutableActionGroupStrategy {
/*     */     private RemoveWorkspaceActionGroup() {}
/*     */     
/*     */     public List getActions(AnActionEvent event) {
/* 235 */       return getWorkspaceManager(event).getRemoveWorkspaceActions();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/workspaces/WorkspacesConfigurable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */