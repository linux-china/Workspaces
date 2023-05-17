package com.chrisbartley.swing.event;

import javax.swing.event.DocumentEvent;

public interface DocumentEventValidator {
    boolean isValid(DocumentEvent paramDocumentEvent);
}
