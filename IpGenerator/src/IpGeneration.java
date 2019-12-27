/*
 Given a string of digits, generate all possible valid IP address combinations.

 IP addresses must follow the format A.B.C.D, where A, B, C, and D are numbers
 between 0 and 255. Zero-prefixed numbers, such as 01 and 065, are not allowed, 
 except for 0 itself.

 For example, given "2542540123", you should return ['254.25.40.123', '254.254.0.123'].
 Assuming the entire given sequence must be used.
 
 5:12hr
 */

import java.util.*;
public class IpGeneration {

	public static LinkedList<int[]> output = new LinkedList<int[]>(); 
	
	public static void main(String[] args) {
		IpGeneration thisClass = new IpGeneration();
		String digits = "2542540123";
						//"0";
						//"1111111111111111";
						//"3333";
						//"0000";
		char[] digArry = digits.toCharArray();
		int[] cmb = new int[3];		
		thisClass.ipGener(digArry, 0, 0, cmb);
		
		System.out.print("[");
		for(int[] deci : output) {
			System.out.print("'");
			int cntr = 0;
			int deciNum = deci[0]-1;
			for (int i = 0; i < digArry.length; i++) {
				System.out.print(digArry[i]);
				if(i == deciNum) {					
					if(cntr<2) {
						cntr++;
						deciNum += deci[cntr];
					}					
					System.out.print(".");
				}				
			}
			System.out.print("', ");
		}
		System.out.print("]");
		
	}
	
	public void ipGener(char[] input, int bndStrt, int itter, int[] cmb){
		int inLeng = input.length-bndStrt;
		//if current combination wont fully use input return.
		if( inLeng < 4-itter || inLeng > 3*(4-itter)) {
			return;
		}		

		if(itter == 3) {
			if(ipCheck(input, bndStrt,input.length-1)) {
				int[] toAdd = new int[3];
				for (int i = 0; i < cmb.length; i++) {
					toAdd[i] = cmb[i];//prevent reference problems
				}
				output.add(toAdd);
				
			}
			return;
		}
		
		for(int i = 1; i <=3 ; i++) {
			if(ipCheck(input, bndStrt,bndStrt+i-1)) {
				cmb[itter] = i;				
				ipGener(input,bndStrt +i,itter+1,cmb);
			}
			
		}		
	}
	public boolean ipCheck(char[] inArry, int bndStrt, int bndEnd) {
		//checks if bndStart and End are good
		if(inArry.length-1 < bndEnd || bndStrt < 0) return false;
		
		//checks to see if specific digits start with zero and are between 0-255
		if( bndEnd - bndStrt != 0) {//inappropriate zero check
			for(int i = bndStrt; i <bndEnd; i++) {
				if(inArry[i] == '0') {
					return false;
				}
			}
		}
		//check to see if value is within bounds
		int expo = 0;
		double tmpNum = 0;
		for(int i = bndEnd; i >= bndStrt; i--) {
			tmpNum += Character.getNumericValue(inArry[i]) * (Math.pow(10, expo));
			expo++;
		}
		if(tmpNum <0 || tmpNum>255) {
			return false;
		}
		return true;
	}
	
}
