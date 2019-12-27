/*
 In front of you is a row of N coins, with values v1, v1, ..., vn.

You are asked to play the following game. You and an opponent take 
turns choosing either the first or last coin from the row, removing 
it from the row, and receiving the value of the coin.

Write a program that returns the maximum amount of money you can win 
with certainty, if you move first, assuming your opponent plays optimally.

1:45hr
medium
 */

import java.util.*;
public class CoinGame {

	
	//there is a dynamic way to solve this
	//but I find the brute force is far more memory and space efficient
	//it is a bit ugly though
	public static void main(String[] args) {
		CoinGame thisClass = new CoinGame();
		int[] coinRow = //{1,100,25,25,10,5,200,5};//236
						//{ 20, 30, 2, 2, 2, 10 };//42
						//{ 8, 15, 3, 7 }; //22
						//{ 2, 2, 2, 2 };//4
						{1,100,25,25,10,5,200};//236
		System.out.println(thisClass.optimalPlay(coinRow));
		
 	}
	
	public int optimalPlay(int[] input) {
		//edge case
		if(input.length ==1)return input[0];
		
		if(input.length % 2 == 1) {//odd
			//choose largest value then opponent dictates what is chosen
			int firstVal;
			if(input[0] > input[input.length-1]) {
				firstVal = input[0];
				int sum =0;
				int oddSum =0;
				for (int i = 1; i < input.length; i++) {
					sum += input[i];
					if(i % 2 ==1) {
						oddSum += input[i];
					}
				}
				int evenSum = sum - oddSum;
				return (evenSum>oddSum)? oddSum+firstVal: evenSum+firstVal;
			}else {
				firstVal = input[input.length-1];
				int sum =0;
				int oddSum =0;
				for (int i = 0; i < input.length-1; i++) {
					sum += input[i];
					if(i % 2 ==1) {
						oddSum += input[i];
					}
				}
				int evenSum = sum - oddSum;
				return (evenSum>oddSum)? oddSum+firstVal: evenSum+firstVal;
			}
		}else {
			int sum =0;
			int oddSum =0;
			for (int i = 0; i < input.length; i++) {
				sum += input[i];
				if(i % 2 ==1) {
					oddSum += input[i];
				}
			}
			int evenSum = sum - oddSum;
			return (evenSum>oddSum)? evenSum: oddSum;
		}	
	}
	
}
