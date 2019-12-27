/*
 Given a linked list of numbers and a pivot k, partition the linked list
 so that all nodes less than k come before nodes greater than or equal to k.

 For example, given the linked list 5 -> 1 -> 8 -> 0 -> 3 
 and k = 3, the solution could be 1 -> 0 -> 5 -> 8 -> 3.
 
 22:26min
 medium
 */

import java.util.*;
public class PartitionPivot {

	public static void main(String[] args) {
		PartitionPivot thisClass = new PartitionPivot();
		LinkedList<Integer> input = new LinkedList<Integer>();
		int[] inArr = {5,1,8,0,2,3};
		for (int i = 0; i < inArr.length; i++) {
			input.add(inArr[i]);
		}
		int pivot = 3;
		LinkedList<Integer> output = thisClass.partiPivot(input, pivot);
		
		for(Integer x:output) {
			System.out.print(x+" -> ");
		}
		System.out.println();
		
	}
	
	public LinkedList<Integer> partiPivot(LinkedList<Integer> input, int pivot){
		
		LinkedList<Integer> lessPivot = new LinkedList<Integer>();
		LinkedList<Integer> equalMorePiv = new LinkedList<Integer>();
		
		for (int i = 0; i < input.size(); i++) {
			Integer item = input.get(i);
			if(item < pivot) {
				lessPivot.add(item);
			}else {
				equalMorePiv.add(item);
			}
		}
		while(lessPivot.size() >0){
			equalMorePiv.addFirst(lessPivot.pollLast());
		}			
		
		return equalMorePiv;
	}
}
