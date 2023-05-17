package com.chrisbartley.idea.actions;

import com.intellij.openapi.actionSystem.ActionManager;

import javax.swing.*;


public abstract class RegisterableAction extends BaseAction {
    public RegisterableAction() {
    }

    public RegisterableAction(String text) {
        super(text);
    }


    public RegisterableAction(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    public final void register() {
        ActionManager actionManager = ActionManager.getInstance();
        String actionId = getActionRegistrationId();
        if (actionManager.getAction(actionId) == null) {
            actionManager.registerAction(actionId, this);
        }
    }


    public final void unregister() {
        ActionManager actionManager = ActionManager.getInstance();
        String actionId = getActionRegistrationId();
        if (actionManager.getAction(actionId) != null) {
            actionManager.unregisterAction(actionId);
        }
    }


    protected String getActionRegistrationId() {
        return getClass().getName();
    }
}

