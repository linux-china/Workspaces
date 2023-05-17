package com.chrisbartley.swing.event;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ButtonTogglingDocumentListener implements DocumentListener {
    private final Action buttonToToggle;
    private final DocumentEventValidator documentEventValidator;

    public ButtonTogglingDocumentListener(Action buttonToToggle, DocumentEventValidator documentEventValidator) {
        this.buttonToToggle = buttonToToggle;
        this.documentEventValidator = documentEventValidator;
    }


    public void insertUpdate(DocumentEvent event) {
        toggleButton(event);
    }


    public void removeUpdate(DocumentEvent event) {
        toggleButton(event);
    }


    public void changedUpdate(DocumentEvent event) {
        toggleButton(event);
    }


    private void toggleButton(DocumentEvent event) {
        this.buttonToToggle.setEnabled(this.documentEventValidator.isValid(event));
    }
}
