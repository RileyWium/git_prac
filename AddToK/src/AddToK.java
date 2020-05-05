
import java.util.ArrayList;
import java.util.*;

/*
 Given a list of numbers and a number k, return whether any two numbers from the list add up to k.

For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.

Bonus: Can you do this in one pass?
  
  Easy
  Time: 24:14min
 */
public class AddToK {
	
	public static void main(String[] args) {
		AddToK inst = new AddToK();
		int[] numbers = {10,15,3,7};
		int k = 16;
		
		System.out.println(inst.AddsToK(numbers,k));
	}
	
	public boolean AddsToK(int[] numbers, int k) {
		
		int[] checkNums = new int[numbers.length];
		
		for(int i = 0 ; i < numbers.length; i++) {
			
			int tmp =  k-numbers[i];
			checkNums[i] = tmp;
			for(int j =0 ; j <i; j++) {
				if(numbers[i] == checkNums[j]) {
					return true;
				}
			}			
		}
		
		return false;
	}

}
