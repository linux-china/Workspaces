package com.chrisbartley.idea.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;


public final class MutableActionGroup extends BaseActionGroup {
    private final MutableActionGroupStrategy strategy;

    public MutableActionGroup(MutableActionGroupStrategy strategy) {
        this(strategy, null, false, (Icon) null);
    }


    public MutableActionGroup(MutableActionGroupStrategy strategy, String shortName, boolean popup) {
        this(strategy, shortName, popup, (Icon) null);
    }


    public MutableActionGroup(MutableActionGroupStrategy strategy, String shortName, boolean popup, Icon icon) {
        super(shortName, popup);
        this.strategy = strategy;
        getTemplatePresentation().setIcon(icon);
    }


    public void update(@NotNull AnActionEvent event) {
        removeAll();
        if (event.getProject() != null) {
            List<RegisterableAction> actions = this.strategy.getActions(event);
            for (RegisterableAction action : actions) {
                add(action);
            }
            event.getPresentation().setEnabled(!actions.isEmpty());
        } else {
            event.getPresentation().setEnabled(false);
        }
        this.strategy.preparePresentation(event);
    }
}

