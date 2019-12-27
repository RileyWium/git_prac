/*
 A permutation can be specified by an array P, where P[i]
 represents the location of the element at i in the permutation. 
 For example, [2, 1, 0] represents the permutation where elements at the index 0 and 2 are swapped.

Given an array and a permutation, apply the permutation to 
the array. For example, given the array ["a", "b", "c"] and 
the permutation [2, 1, 0], return ["c", "b", "a"].

27:12min
easy
 */
import java.util.*;
public class PermArray {

	
	public static void main(String[] args) {
		PermArray thisClass = new PermArray();
		
		String[] genIn = {"a","b","c","d","e"};
		int[] permIn = {2,1,0,4,3};
		
		System.out.println("Used permutation: ");
		for (int i = 0; i < permIn.length; i++) {
			System.out.print(permIn[i]);
		}
		System.out.println(" to turn input into ");
		
		String[] output = thisClass.permutateGenericArray(permIn,genIn);
		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i]);
		}
		System.out.println();		
	}
	
	public <T extends Comparable<T>> T[] permutateGenericArray(int[] permIn, T[] genIn){
		
		//just sort the permIn and genIn at the same time
		
		for (int i = 0; i < permIn.length-1; i++) {
			int swapI = i;
			int swapJ = i+1;
			
			for (int j = i+1; j < permIn.length; j++) {
			
				if(permIn[i] > permIn[j]) {
					int tmp = permIn[j];
					permIn[j] = permIn[i];
					permIn[i] = tmp;
					T tmpT = genIn[j];
					genIn[j] = genIn[i];
					genIn[i] = tmpT;
				}
			}
			
			
		}
		
		
		return genIn;
	}
}
