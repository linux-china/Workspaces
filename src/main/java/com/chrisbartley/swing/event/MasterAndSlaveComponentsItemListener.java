package com.chrisbartley.swing.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MasterAndSlaveComponentsItemListener implements ItemListener {
    private final AbstractButton masterComponent;
    private final Component[] slaveComponents;

    public MasterAndSlaveComponentsItemListener(AbstractButton masterComponent, Component[] slaveComponents) {
        this.masterComponent = masterComponent;
        this.slaveComponents = slaveComponents;
    }

    public void itemStateChanged(ItemEvent e) {
        for (Component slaveComponent : this.slaveComponents) {
            slaveComponent.setEnabled(this.masterComponent.isSelected());
        }
    }
}

