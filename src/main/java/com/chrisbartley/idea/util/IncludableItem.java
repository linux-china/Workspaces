package com.chrisbartley.idea.util;

public class IncludableItem<T> {
    private final T item;
    private boolean isIncluded = true;

    public IncludableItem(T item) {
        this.item = item;
    }


    public T getItem() {
        return this.item;
    }


    public boolean isIncluded() {
        return this.isIncluded;
    }

    public void setIncluded(boolean included) {
        this.isIncluded = included;
    }
}

