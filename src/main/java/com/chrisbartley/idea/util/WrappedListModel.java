package com.chrisbartley.idea.util;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class WrappedListModel<T> extends RefreshableListModel<T> implements List<T> {
    private final List<T> wrappedList;

    public WrappedListModel(List<T> wrappedList) {
        this.wrappedList = wrappedList;
    }


    public int getSize() {
        return this.wrappedList.size();
    }


    public T getElementAt(int index) {
        return this.wrappedList.get(index);
    }


    public int size() {
        return this.wrappedList.size();
    }


    public boolean isEmpty() {
        return this.wrappedList.isEmpty();
    }


    public boolean contains(Object o) {
        return this.wrappedList.contains(o);
    }


    public Iterator<T> iterator() {
        return this.wrappedList.iterator();
    }


    public Object[] toArray() {
        return this.wrappedList.toArray();
    }


    @NotNull
    @Override
    public <T1> T1 @NotNull [] toArray(@NotNull T1 @NotNull [] a) {
        return this.wrappedList.toArray(a);
    }

    public boolean add(T o) {
        int index = this.wrappedList.size();
        boolean wasAdded = this.wrappedList.add(o);
        fireIntervalAdded(this, index, index);
        return wasAdded;
    }


    public boolean remove(Object o) {
        int index = this.wrappedList.indexOf(o);
        boolean wasRemoved = this.wrappedList.remove(o);
        if (index >= 0) {
            fireIntervalRemoved(this, index, index);
        }
        return wasRemoved;
    }


    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return this.wrappedList.containsAll(c);
    }

    public boolean addAll(@NotNull Collection c) {
        return addAll(size(), c);
    }


    public boolean addAll(int index, @NotNull Collection c) {
        boolean wasModified = this.wrappedList.addAll(index, c);
        if (wasModified) {
            fireIntervalAdded(this, index, index + c.size());
        }
        return wasModified;
    }


    public boolean removeAll(@NotNull Collection c) {
        return this.wrappedList.removeAll(c);
    }


    public boolean retainAll(@NotNull Collection c) {
        return this.wrappedList.retainAll(c);
    }


    public void clear() {
        int index1 = this.wrappedList.size() - 1;
        this.wrappedList.clear();
        if (index1 >= 0) {
            fireIntervalRemoved(this, 0, index1);
        }
    }


    public boolean equals(Object o) {
        return this.wrappedList.equals(o);
    }


    public int hashCode() {
        return this.wrappedList.hashCode();
    }


    public T get(int index) {
        return this.wrappedList.get(index);
    }


    public T set(int index, T element) {
        T oldElement = this.wrappedList.set(index, element);
        fireContentsChanged(this, index, index);
        return oldElement;
    }


    public void add(int index, T element) {
        this.wrappedList.add(index, element);
        fireIntervalAdded(this, index, index);
    }


    public T remove(int index) {
        T oldElement = this.wrappedList.remove(index);
        fireIntervalRemoved(this, index, index);
        return oldElement;
    }


    public int indexOf(Object o) {
        return this.wrappedList.indexOf(o);
    }


    public int lastIndexOf(Object o) {
        return this.wrappedList.lastIndexOf(o);
    }


    public ListIterator<T> listIterator() {
        return this.wrappedList.listIterator();
    }


    public ListIterator<T> listIterator(int index) {
        return this.wrappedList.listIterator(index);
    }


    public List<T> subList(int fromIndex, int toIndex) {
        return this.wrappedList.subList(fromIndex, toIndex);
    }


    public boolean isIndexWithinBounds(int index) {
        return (index >= 0 && index < size());
    }


    protected void assertIndexIsWithinBounds(int index) throws ArrayIndexOutOfBoundsException {
        if (!isIndexWithinBounds(index)) {
            throw new ArrayIndexOutOfBoundsException("The index '" + index + "' is out of bounds.");
        }
    }

}
