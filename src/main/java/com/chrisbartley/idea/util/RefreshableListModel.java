package com.chrisbartley.idea.util;

import javax.swing.*;
import java.util.Arrays;


public abstract class RefreshableListModel<T> extends AbstractListModel<T> {
    public final void refreshAll() {
        fireContentsChanged(this, 0, getSize() - 1);
    }

    public final void refresh(int[] indices) {
        if (indices != null) {
            for (int index : indices) {
                refresh(index);
            }
        }
    }


    public final void refresh(int index) {
        if (index >= 0 && index < getSize()) {
            fireContentsChanged(this, index, index);
        }
    }


    public void refreshAllButThese(int[] indicesToIgnore) {
        if (indicesToIgnore != null) {
            if (indicesToIgnore.length == 0) {
                refreshAll();
            } else {
                int[] indicesNotToRefresh = new int[indicesToIgnore.length];
                System.arraycopy(indicesToIgnore, 0, indicesNotToRefresh, 0, indicesToIgnore.length);
                for (int i = 0; i < getSize(); i++) {
                    if (Arrays.binarySearch(indicesNotToRefresh, i) < 0) {
                        refresh(i);
                    }
                }
            }
        }
    }
}


