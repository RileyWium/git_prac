/*
 Given an integer, find the next permutation of it in absolute order.
  For example, given 48975, the next permutation would be 49578.
 
 1:52 hr
 */
import java.util.*;
public class NextPerm {
	
	public static void main(String[] args) {
		NextPerm thisClass = new NextPerm();
		int input = //48975;
					//-2345663;
					//324363463;
					//1;
					-1111111;
		int[] output = thisClass.nextAbsPerm(Math.abs(input));
		int compiledOutput = 0;
		for(int i=0; i < output.length; i++) {
			compiledOutput += output[i] * Math.pow(10,output.length-i-1);
		}
		
		System.out.println("After "+input+" comes "+compiledOutput);
	}
	
	public int[] nextAbsPerm(int input) {
		if(input <10) {
			int[] edgeCase = {input};
			System.out.println("No larger permutation"); return edgeCase;//edge case
		}
		
		int inLength =0;
		int inTmp = input;
		
		while(inTmp > 0) {
			inLength++;
			inTmp /= 10;
		}
		inTmp = input;
		int[] elements = new int[inLength];
		for(int i=elements.length-1; i > -1; i--) {
			elements[i] = inTmp % 10;
			inTmp /=10;
		}
		//search for most efficient swap
		int firstSwapPos = -1;//produces null error in case it is not set
		
		for (int i = elements.length-2; i >=0; i--) {
			for (int j = elements.length-1; j >i; j--) {
				if(elements[i] < elements[j]) {
					firstSwapPos = i;
					int tmp = elements[i];
					elements[i] = elements[j];
					elements[j] = tmp;
					break;
				}
			}
			if(firstSwapPos != -1) {
				break;
			}
			if(i == 0) {//input already solved, edge case
				return elements;
			}
		}
		//sort ascending after firstSwapPos
		Arrays.sort(elements, firstSwapPos+1, elements.length);
				
		return elements;
		
	}

}
