/*     */ package com.chrisbartley.idea.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WrappedListModel
/*     */   extends RefreshableListModel
/*     */   implements List
/*     */ {
/*     */   private final List wrappedList;
/*     */   
/*     */   public WrappedListModel(List wrappedList) {
/*  17 */     this.wrappedList = wrappedList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  22 */     return this.wrappedList.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getElementAt(int index) {
/*  27 */     return this.wrappedList.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  32 */     return this.wrappedList.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  37 */     return this.wrappedList.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/*  42 */     return this.wrappedList.contains(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  47 */     return this.wrappedList.iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  52 */     return this.wrappedList.toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/*  57 */     return this.wrappedList.toArray(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object o) {
/*  62 */     int index = this.wrappedList.size();
/*  63 */     boolean wasAdded = this.wrappedList.add(o);
/*  64 */     fireIntervalAdded(this, index, index);
/*  65 */     return wasAdded;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/*  70 */     int index = this.wrappedList.indexOf(o);
/*  71 */     boolean wasRemoved = this.wrappedList.remove(o);
/*  72 */     if (index >= 0)
/*     */     {
/*  74 */       fireIntervalRemoved(this, index, index);
/*     */     }
/*  76 */     return wasRemoved;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  81 */     return this.wrappedList.containsAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  86 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/*  91 */     boolean wasModified = this.wrappedList.addAll(index, c);
/*  92 */     if (wasModified)
/*     */     {
/*  94 */       fireIntervalAdded(this, index, index + c.size());
/*     */     }
/*  96 */     return wasModified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection c) {
/* 105 */     return this.wrappedList.removeAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection c) {
/* 114 */     return this.wrappedList.retainAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 119 */     int index1 = this.wrappedList.size() - 1;
/* 120 */     this.wrappedList.clear();
/* 121 */     if (index1 >= 0)
/*     */     {
/* 123 */       fireIntervalRemoved(this, 0, index1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 129 */     return this.wrappedList.equals(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 134 */     return this.wrappedList.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(int index) {
/* 139 */     return this.wrappedList.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object set(int index, Object element) {
/* 144 */     Object oldElement = this.wrappedList.set(index, element);
/* 145 */     fireContentsChanged(this, index, index);
/* 146 */     return oldElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Object element) {
/* 151 */     this.wrappedList.add(index, element);
/* 152 */     fireIntervalAdded(this, index, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object remove(int index) {
/* 157 */     Object oldElement = this.wrappedList.remove(index);
/* 158 */     fireIntervalRemoved(this, index, index);
/* 159 */     return oldElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Object o) {
/* 164 */     return this.wrappedList.indexOf(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int lastIndexOf(Object o) {
/* 169 */     return this.wrappedList.lastIndexOf(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public ListIterator listIterator() {
/* 174 */     return this.wrappedList.listIterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 179 */     return this.wrappedList.listIterator(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 184 */     return this.wrappedList.subList(fromIndex, toIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIndexWithinBounds(int index) {
/* 189 */     return (index >= 0 && index < size());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void assertIndexIsWithinBounds(int index) throws ArrayIndexOutOfBoundsException {
/* 194 */     if (!isIndexWithinBounds(index))
/*     */     {
/* 196 */       throw new ArrayIndexOutOfBoundsException("The index '" + index + "' is out of bounds.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/util/WrappedListModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */