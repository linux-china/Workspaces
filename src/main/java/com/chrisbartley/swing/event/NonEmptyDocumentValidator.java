package com.chrisbartley.swing.event;

import javax.swing.event.DocumentEvent;

public class NonEmptyDocumentValidator implements DocumentEventValidator {
    public boolean isValid(DocumentEvent event) {
        return (event.getDocument().getLength() > 0);
    }
}

