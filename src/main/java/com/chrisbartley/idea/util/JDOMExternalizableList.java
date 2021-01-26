/*     */ package com.chrisbartley.idea.util;
/*     */ 
/*     */ import com.intellij.openapi.util.DefaultJDOMExternalizer;
/*     */ import com.intellij.openapi.util.InvalidDataException;
/*     */ import com.intellij.openapi.util.JDOMExternalizable;
/*     */ import com.intellij.openapi.util.WriteExternalException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.jdom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDOMExternalizableList
/*     */   implements List, JDOMExternalizable
/*     */ {
/*     */   private static final String ATTR_LIST = "list";
/*     */   private static final String ATTR_LISTSIZE = "size";
/*     */   private static final String ATTR_ITEM = "item";
/*     */   private static final String ATTR_INDEX = "index";
/*     */   private static final String ATTR_CLASS = "class";
/*     */   private static final String ATTR_VALUE = "itemvalue";
/*  30 */   private ArrayList myList = new ArrayList();
/*     */   
/*     */   public int size() {
/*  33 */     return this.myList.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  37 */     return this.myList.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/*  41 */     return this.myList.contains(o);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  45 */     return this.myList.iterator();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  49 */     return this.myList.toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/*  53 */     return this.myList.toArray(a);
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/*  57 */     return this.myList.add(o);
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/*  61 */     return this.myList.remove(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection c) {
/*  65 */     return this.myList.containsAll(c);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/*  69 */     return this.myList.addAll(c);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection c) {
/*  73 */     return this.myList.addAll(index, c);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/*  77 */     return this.myList.removeAll(c);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/*  81 */     return this.myList.retainAll(c);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  85 */     this.myList.clear();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  89 */     return this.myList.equals(o);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  93 */     return this.myList.hashCode();
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  97 */     return this.myList.get(index);
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/* 101 */     return this.myList.set(index, element);
/*     */   }
/*     */   
/*     */   public void add(int index, Object element) {
/* 105 */     this.myList.add(index, element);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 109 */     return this.myList.remove(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object o) {
/* 113 */     return this.myList.indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object o) {
/* 117 */     return this.myList.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 121 */     return this.myList.listIterator();
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 125 */     return this.myList.listIterator(index);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 129 */     return this.myList.subList(fromIndex, toIndex);
/*     */   }
/*     */   
/*     */   public void readExternal(Element element) throws InvalidDataException {
/* 133 */     this.myList = new ArrayList();
/* 134 */     ArrayList resultList = null;
/* 135 */     for (Iterator i = element.getChildren().iterator(); i.hasNext(); ) {
/* 136 */       Element e = (Element) i.next();
/* 137 */       if ("list".equals(e.getName())) {
/* 138 */         int listSize; Element listElement = e;
/* 139 */         String sizeString = listElement.getAttributeValue("size");
/*     */         
/*     */         try {
/* 142 */           listSize = Integer.parseInt(sizeString);
/* 143 */         } catch (NumberFormatException ex) {
/* 144 */           throw new InvalidDataException("Size " + sizeString + " found. Must be integer!");
/*     */         } 
/* 146 */         resultList = new ArrayList(listSize);
/* 147 */         for (int j = 0; j < listSize; j++) {
/* 148 */           resultList.add((Object)null);
/*     */         }
/* 150 */         for (Iterator listIterator = listElement.getChildren().iterator(); listIterator.hasNext(); ) {
/* 151 */           Object listItem; Class itemClass; Element listItemElement = (Element) listIterator.next();
/* 152 */           if (!"item".equals(listItemElement.getName())) {
/* 153 */             throw new InvalidDataException("Unable to read list item. Unknown element found: " + listItemElement.getName());
/*     */           }
/*     */           
/* 156 */           String itemIndexString = listItemElement.getAttributeValue("index");
/* 157 */           String itemClassString = listItemElement.getAttributeValue("class");
/*     */           
/*     */           try {
/* 160 */             itemClass = Class.forName(itemClassString);
/* 161 */           } catch (ClassNotFoundException ex) {
/* 162 */             throw new InvalidDataException("Unable to read list item: unable to load class: " + itemClassString + " \n" + ex.getMessage());
/*     */           } 
/* 164 */           if (String.class.equals(itemClass)) {
/* 165 */             listItem = listItemElement.getAttributeValue("itemvalue");
/*     */           } else {
/*     */             try {
/* 168 */               listItem = itemClass.newInstance();
/* 169 */             } catch (Exception ex) {
/* 170 */               throw new InvalidDataException("Unable to create list item from class: " + itemClassString + " \n" + ex.getMessage());
/*     */             } 
/*     */           } 
/* 173 */           for (Iterator itemIterator = listItemElement.getChildren().iterator(); itemIterator.hasNext(); ) {
/* 174 */             Element valueElement = (Element) itemIterator.next();
/* 175 */             if (!"itemvalue".equals(valueElement.getName())) {
/* 176 */               throw new InvalidDataException("Unable to read list item value. Unknown element found: " + listItemElement.getName());
/*     */             }
/* 178 */             if (listItem instanceof JDOMExternalizable) {
/* 179 */               ((JDOMExternalizable)listItem).readExternal(valueElement); continue;
/*     */             } 
/* 181 */             DefaultJDOMExternalizer.readExternal(listItem, valueElement);
/*     */           } 
/*     */           
/* 184 */           resultList.set(Integer.parseInt(itemIndexString), listItem);
/*     */         } 
/*     */       } 
/*     */     } 
/* 188 */     if (resultList == null) {
/* 189 */       throw new InvalidDataException("Unable to read list. List element not found!");
/*     */     }
/* 191 */     this.myList = resultList;
/*     */   }
/*     */   
/*     */   public void writeExternal(Element element) throws WriteExternalException {
/* 195 */     int listSize = (this.myList == null) ? 0 : this.myList.size();
/* 196 */     Element listElement = new Element("list");
/* 197 */     listElement.setAttribute("size", Integer.toString(listSize));
/* 198 */     element.addContent(listElement);
/* 199 */     for (int i = 0; i < listSize; i++) {
/* 200 */       Object listItem = this.myList.get(i);
/* 201 */       if (listItem != null) {
/* 202 */         Element itemElement = new Element("item");
/* 203 */         itemElement.setAttribute("index", Integer.toString(i));
/* 204 */         itemElement.setAttribute("class", listItem.getClass().getName());
/* 205 */         if (listItem instanceof String) {
/* 206 */           itemElement.setAttribute("itemvalue", (String)listItem);
/*     */         } else {
/* 208 */           Element objectElement = new Element("itemvalue");
/* 209 */           if (listItem instanceof JDOMExternalizable) {
/* 210 */             ((JDOMExternalizable)listItem).writeExternal(objectElement);
/*     */           } else {
/* 212 */             DefaultJDOMExternalizer.writeExternal(listItem, objectElement);
/*     */           } 
/* 214 */           itemElement.addContent(objectElement);
/*     */         } 
/* 216 */         listElement.addContent(itemElement);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/util/JDOMExternalizableList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */