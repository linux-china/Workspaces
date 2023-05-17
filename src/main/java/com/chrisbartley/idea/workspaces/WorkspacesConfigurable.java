package com.chrisbartley.idea.workspaces;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;

import javax.swing.*;

public final class WorkspacesConfigurable implements Configurable {

    private WorkspacesConfigurationPanel configurationPanel;

    public String getDisplayName() {
        return "Workspaces";
    }


    public Icon getIcon() {
        return Icons.WORKSPACES_CONFIGURABLE;
    }


    public String getHelpTopic() {
        return null;
    }


    public JComponent createComponent() {
        this.configurationPanel = new WorkspacesConfigurationPanel();
        WorkspacesConfiguration workspacesConfiguration = ApplicationManager.getApplication().getService(WorkspacesConfiguration.class);
        this.configurationPanel.copyConfigurationFrom(workspacesConfiguration);
        return this.configurationPanel;
    }


    public boolean isModified() {
        return true;
    }


    public void apply() throws ConfigurationException {
        WorkspacesConfiguration workspacesConfiguration = ApplicationManager.getApplication().getService(WorkspacesConfiguration.class);
        saveToConfiguration(workspacesConfiguration);
    }


    private void saveToConfiguration(WorkspacesConfiguration configuration) {
        this.configurationPanel.copyConfigurationTo(configuration);
    }


    public void reset() {
        WorkspacesConfiguration workspacesConfiguration = ApplicationManager.getApplication().getService(WorkspacesConfiguration.class);
        this.configurationPanel.copyConfigurationFrom(workspacesConfiguration);
    }


    public void disposeUIResources() {
        this.configurationPanel = null;
    }


}
