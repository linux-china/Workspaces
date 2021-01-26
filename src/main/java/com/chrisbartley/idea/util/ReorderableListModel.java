/*    */ package com.chrisbartley.idea.util;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReorderableListModel
/*    */   extends WrappedListModel
/*    */ {
/*    */   public ReorderableListModel(List wrappedList) {
/* 13 */     super(wrappedList);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void shiftElements(int[] indicesToShift, boolean isShiftTowardsEndOfList) {
/* 32 */     if (indicesToShift != null && indicesToShift.length > 0) {
/*    */ 
/*    */       
/* 35 */       Arrays.sort(indicesToShift);
/* 36 */       int firstIndexToShift = indicesToShift[0];
/* 37 */       int lastIndexToShift = indicesToShift[indicesToShift.length - 1];
/* 38 */       assertIndexIsWithinBounds(firstIndexToShift);
/* 39 */       assertIndexIsWithinBounds(lastIndexToShift);
/*    */ 
/*    */ 
/*    */       
/* 43 */       if ((isShiftTowardsEndOfList && lastIndexToShift < size() - 1) || (!isShiftTowardsEndOfList && firstIndexToShift > 0)) {
/*    */ 
/*    */         
/* 46 */         boolean[] elementsWillBeShifted = new boolean[size()];
/* 47 */         for (int i = 0; i < indicesToShift.length; i++)
/*    */         {
/* 49 */           elementsWillBeShifted[indicesToShift[i]] = true;
/*    */         }
/*    */ 
/*    */         
/* 53 */         int offsetToNextElement = isShiftTowardsEndOfList ? -1 : 1;
/* 54 */         int startingIndex = isShiftTowardsEndOfList ? (lastIndexToShift + 1) : (firstIndexToShift - 1);
/* 55 */         int endingIndex = isShiftTowardsEndOfList ? firstIndexToShift : lastIndexToShift;
/*    */ 
/*    */         
/* 58 */         int currentIndex = startingIndex;
/* 59 */         while (currentIndex != endingIndex) {
/*    */           
/* 61 */           int indexOfNextElement = currentIndex + offsetToNextElement;
/* 62 */           boolean shiftStateOfNextElement = elementsWillBeShifted[indexOfNextElement];
/* 63 */           if (shiftStateOfNextElement)
/*    */           {
/* 65 */             swapElements(currentIndex, indexOfNextElement);
/*    */           }
/* 67 */           if (shiftStateOfNextElement || elementsWillBeShifted[currentIndex])
/*    */           {
/* 69 */             fireContentsChanged(this, currentIndex, currentIndex);
/*    */           }
/* 71 */           currentIndex += offsetToNextElement;
/*    */         } 
/* 73 */         fireContentsChanged(this, currentIndex, currentIndex);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void swapElements(int index1, int index2) {
/* 81 */     Object element1 = getElementAt(index1);
/* 82 */     Object element2 = getElementAt(index2);
/* 83 */     set(index2, element1);
/* 84 */     set(index1, element2);
/*    */   }
/*    */ }


/* Location:              /Users/linux_china/Downloads/Workspaces_293.jar!/com/chrisbartley/idea/util/ReorderableListModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */