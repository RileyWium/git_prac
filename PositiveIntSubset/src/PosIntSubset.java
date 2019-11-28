/*
 Good morning! Here's your coding interview problem for today.

This problem was asked by Google.

Given a set of distinct positive integers,
find the largest subset such that every pair
 of elements in the subset (i, j) satisfies
  either i % j = 0 or j % i = 0.

For example, given the set [3, 5, 10, 20, 21],
 you should return [5, 10, 20]. Given [1, 3, 6, 24],
  return [1, 3, 6, 24].
 */

import java.util.*;

public class PosIntSubset {

	public static void main(String[] args) {
		
		//inputs
		int[] posIntArray = //{1, 3, 6, 24};
							//{2,3,3,4,23524,53,53,554,4355,240,232,2212,232423423,6,3,12};
							{3,7,11,13};	
		
		PosIntSubset pis = new PosIntSubset();
		//read outputs
		int[] toPrintOutput =pis.subsetMaker(posIntArray);
		for (int i = 0; i < toPrintOutput.length; i++) {
			System.out.print(toPrintOutput[i]+", ");
		}
		
		System.out.println();		
	}
	
	public int[] subsetMaker(int[] input){
		//edge case
		if(input.length == 0){
			return new int[0];
		}		
		
		Arrays.sort(input);		
		
		TreeNode[] tArray = new TreeNode[input.length];
		
		//turn int[] into treeNode[] and assign children, sort so we only need i%j==0
		for (int i = 0; i < input.length; i++) {
			//turn current input into tree node
			tArray[i]  = new TreeNode(input[i]);
			for (int j = 0; j < i; j++) {
				//check current against all tree nodes if i % j = 0
				if(input[i] %tArray[j].mainValue   == 0){
					tArray[i].addChild(tArray[j]);
				}
			}
		}		
		//depth first search of highest maxLength
		//add base root and add children
		TreeNode rootNode = tArray[0];
		int currentMaxL =0;
		for (int i = 0; i < tArray.length; i++) {
			if(tArray[i].maxLength > currentMaxL){
				rootNode = tArray[i];
				currentMaxL = tArray[i].maxLength;
			}
		}
		//edge case
		if(rootNode.maxLength == 1){
			int[] noChildrenArray = {rootNode.mainValue};
			return noChildrenArray;
		}
		
		int[] tnOutput = new int[rootNode.maxLength];
		tnOutput[0] = rootNode.mainValue;
		maxLengthSearch(rootNode.validChildren,tnOutput,1);
						
		return tnOutput;
	}
	
	public int[] maxLengthSearch(List<TreeNode> input, int[] outputArray, int itterator){
		
		int currentMaxL = 0;
		TreeNode currentTN = input.get(0); // poses problem if input empty
		for (int i = 0; i < input.size(); i++) {
			if(input.get(i).maxLength > currentMaxL){
				currentTN = input.get(i);
				currentMaxL = input.get(i).maxLength;
			}
		}
		outputArray[itterator] = currentTN.mainValue;
		itterator++;
		
		if(currentTN.validChildren.size() > 0){
			maxLengthSearch(currentTN.validChildren, outputArray, itterator);
		}
		return outputArray;
	}
	
	private class TreeNode{
		public int mainValue;
		public List<TreeNode> validChildren = new ArrayList<TreeNode>();
		public int maxLength =1;
		

		public TreeNode(int mainvalue){
		
			mainValue = mainvalue;
		}
		
		public void addChild(TreeNode tn){
			validChildren.add(tn);
			if(tn.maxLength + 1 > maxLength){
				maxLength = tn.maxLength + 1;
			}			
		}		
	}
}

