
/*
 Write a program that checks whether an integer is a palindrome.
 For example, 121 is a palindrome, as well as 888. 678 is not a palindrome.
 Do not convert the integer into a string.
 
 42:54.30 min
 */
import java.util.*;

public class PalindromeCheck {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int in =sc.nextInt();	
		
		PalindromeCheck thisClass = new PalindromeCheck();
		
		System.out.println("The number "+ in+ ". Is palindrome? "+ thisClass.palinCheck(in));
	}
	
	public boolean palinCheck(int input){
		//turn int input into int[]
		//read int[] from both sides evenly, don't need to check middle number of odd numbers
		int inputSize =1;
		int inputToDivide = input;//int naturally floors so don't need to worry
		
		if(Math.abs(input) < 10){
			return true;
		}else{
			while(Math.abs(inputToDivide)>= 10){
				inputSize++;
				inputToDivide /= 10;
			}
		}
		int[] inputArray = new int[inputSize];
		inputToDivide = input;
		for (int i = 0; i < inputSize; i++) {
			inputArray[i] = inputToDivide % 10;
			inputToDivide /= 10;
			
		}
		
		double toScan = Math.floor(inputSize/2);
		
		for (int i = 0; i < toScan; i++) {
			//is i and inputSize-i have same value
			if(inputArray[i] != inputArray[inputSize-i-1]){
				return false;
			}
		}
		
		
		return true;
	}
}
