package com.chrisbartley.idea.util;

import java.util.Arrays;
import java.util.List;


public class ReorderableListModel<T> extends WrappedListModel<T> {
    public ReorderableListModel(List<T> wrappedList) {
        super(wrappedList);
    }

    public void shiftElements(int[] indicesToShift, boolean isShiftTowardsEndOfList) {
        if (indicesToShift != null && indicesToShift.length > 0) {
            Arrays.sort(indicesToShift);
            int firstIndexToShift = indicesToShift[0];
            int lastIndexToShift = indicesToShift[indicesToShift.length - 1];
            assertIndexIsWithinBounds(firstIndexToShift);
            assertIndexIsWithinBounds(lastIndexToShift);
            if ((isShiftTowardsEndOfList && lastIndexToShift < size() - 1) || (!isShiftTowardsEndOfList && firstIndexToShift > 0)) {
                boolean[] elementsWillBeShifted = new boolean[size()];
                for (int j : indicesToShift) {
                    elementsWillBeShifted[j] = true;
                }
                int offsetToNextElement = isShiftTowardsEndOfList ? -1 : 1;
                int startingIndex = isShiftTowardsEndOfList ? (lastIndexToShift + 1) : (firstIndexToShift - 1);
                int endingIndex = isShiftTowardsEndOfList ? firstIndexToShift : lastIndexToShift;
                int currentIndex = startingIndex;
                while (currentIndex != endingIndex) {
                    int indexOfNextElement = currentIndex + offsetToNextElement;
                    boolean shiftStateOfNextElement = elementsWillBeShifted[indexOfNextElement];
                    if (shiftStateOfNextElement) {
                        swapElements(currentIndex, indexOfNextElement);
                    }
                    if (shiftStateOfNextElement || elementsWillBeShifted[currentIndex]) {
                        fireContentsChanged(this, currentIndex, currentIndex);
                    }
                    currentIndex += offsetToNextElement;
                }
                fireContentsChanged(this, currentIndex, currentIndex);
            }
        }
    }


    protected void swapElements(int index1, int index2) {
        T element1 = getElementAt(index1);
        T element2 = getElementAt(index2);
        set(index2, element1);
        set(index1, element2);
    }
}
