
/*
 Given a complete binary tree, count the number of nodes
  in faster than O(n) time. Recall that a complete binary tree
   has every level filled except the last, and the nodes in the
    last level are filled starting from the left.
    
    3:10 hr
 */

import java.util.*;
public class CountBinTree {

	public static void main(String[] args) {
		CountBinTree thisClass = new CountBinTree();
		//create binary tree
		BinaryTree input = thisClass.createTree(230);
		System.out.println("Number of nodes in this tree: "+thisClass.countNodes(input));		
	}
	
	//Creates a binary tree with x nodes 
	public BinaryTree createTree(int nodes) {
		
		Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
		
		BinaryTree root = new BinaryTree(-1);
		
		BinaryTree toAddTo = root;
		
		for (int i = 0; i < nodes-1; i++) {//9
			BinaryTree child = new BinaryTree(i);
			if(toAddTo.leftChild == null) {
				//addchild				
				toAddTo.addChild(child);
				//add child to queue
				queue.add(child);
			}
			else if(toAddTo.rightChild == null) {
				//addchild
				toAddTo.addChild(child);
				//add child to queue
				queue.add(child);

			}else {
				//pop from queue and set as toAddTo
				toAddTo = queue.poll();
				//addchild
				toAddTo.addChild(child);
				//add child to queue
				queue.add(child);				
			}			
		}		
		return root;
	}
	
	public double countNodes(BinaryTree root) {
		
		//must be less than O(n) so we can improve efficiency by depth searching
		//and knowing bottom row is filled left to right. So scan until you run out of leaf nodes
		if(root == null) return 0;//edge case
		
		//find max depth
		BinaryTree checkDepth = root;
		
		int depth =0;
		while(checkDepth.leftChild !=null) {
			depth++;
			checkDepth = checkDepth.leftChild;
		}
		if(depth == 0) return 1;//edge case
		Stack<BinaryTree> treeStack = new Stack<BinaryTree>();
		treeStack.push(root);
		
		//calculate non leaf nodes
		double nonLeafNodes = 0;
		for(int i = 0; i < depth; i++) {
			nonLeafNodes += Math.pow(2, i);
		}
		
		return nonLeafNodes+countLeaves(treeStack,0,0,depth-1);//full leaves
	}	
	
	public int countLeaves(Stack<BinaryTree> stack, int leafCount, int depth, int hoverLeafDepth) {
		
		BinaryTree bt = stack.pop();
		if(depth == hoverLeafDepth) {
			if(bt.leftChild != null) {
				leafCount++;
			}else {
				return leafCount;
			}
			if(bt.rightChild != null) {
				leafCount++;
			}else {
				return leafCount;
			}			
			return leafCount;
		}
		//because we aren't on hoverLeafDepth there will always be 2 children
		
		//if errors appear here, worry about stack
		stack.push(bt.rightChild);
		stack.push(bt.leftChild);
		
		int leftCnt = countLeaves(stack, leafCount, depth+1, hoverLeafDepth);
		if(leftCnt < Math.pow(2,(hoverLeafDepth -depth))) {
			return leftCnt;
		}
		int rightCnt = countLeaves(stack, leafCount, depth+1, hoverLeafDepth);
		if(rightCnt < Math.pow(2,(hoverLeafDepth -depth))) {
			return leftCnt + rightCnt;
		}
		
		return leftCnt + rightCnt;
	}
	
	
	private class BinaryTree{
		
		public int value;
		public BinaryTree leftChild, rightChild;
		//public BinaryTree parent;
		
		public BinaryTree(int mainValue){
			value = mainValue;
		}
		public void addChild(BinaryTree child) {
			if(leftChild == null) {
				leftChild = child;
			}else if(rightChild == null) {
				rightChild = child;
			}else {
				System.out.println("Tried to fill a full node.");
			}
		}
		
	}
	
}
