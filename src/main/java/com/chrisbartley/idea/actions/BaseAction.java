package com.chrisbartley.idea.actions;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public abstract class BaseAction extends AnAction {
    public BaseAction() {
    }

    public BaseAction(String text) {
        super(text);
    }


    public BaseAction(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }
}

