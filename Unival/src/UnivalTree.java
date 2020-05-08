import java.util.ArrayList;

/*
A unival tree (which stands for "universal value") is a tree where all nodes have the same value.

Given the root to a binary tree, count the number of unival subtrees.

For example, the following tree has 5 unival subtrees:

   0
  / \
 1   0
    / \
   1   0
  / \
 1   1


time: 1:54hr
easy
 */

public class UnivalTree {

	public static void main(String[] args) {
		
		UnivalTree inst = new UnivalTree();
		BinaryTreeNode tn = inst.createTree();
		
		int output = inst.countSubTrees(tn).subTreeNum;
		System.out.println("The following Tree has "+output+" unival subtrees");
	}
	
	public BinaryTreeNode createTree() {
		BinaryTreeNode tn = new BinaryTreeNode(0);
		BinaryTreeNode t1 = new BinaryTreeNode(1);
		BinaryTreeNode t2 = new BinaryTreeNode(0);
		BinaryTreeNode t3 = new BinaryTreeNode(1);
		BinaryTreeNode t4 = new BinaryTreeNode(0);
		BinaryTreeNode t5 = new BinaryTreeNode(1);
		BinaryTreeNode t6 = new BinaryTreeNode(1);
		
		tn.addChildren(t1,t2);
		t2.addChildren(t3, t4);
		t3.addChildren(t5, t6);
		return tn;
	}
	
	public UnivalReturnType countSubTrees(BinaryTreeNode tn) {
		UnivalReturnType output = new UnivalReturnType(0, false);
		
		if(tn.btLeft == null && tn.btRight == null) {
			output.subTreeNum++;
			output.isUnival = true;
		}else {
			boolean childrenUnival = true;
			if(tn.btLeft != null) {
				UnivalReturnType left =countSubTrees(tn.btLeft);
				output.subTreeNum+=left.subTreeNum;
				if(left.isUnival) {					
					if(tn.val != tn.btLeft.val) {
						childrenUnival = false;
					}
				}else {
					childrenUnival =false;
				}
			}
			if(tn.btRight != null) {
				UnivalReturnType right =countSubTrees(tn.btRight);
				output.subTreeNum+=right.subTreeNum;
				if(right.isUnival) {
					if(tn.val != tn.btRight.val) {
						childrenUnival = false;
					}
				}else {
					childrenUnival =false;
				}
			}
			if(childrenUnival) {
				output.subTreeNum++;
				output.isUnival = true;
			}
		}		
		
		return output;
	}
	
	private class UnivalReturnType{
		public int subTreeNum;
		public boolean isUnival;
		
		public UnivalReturnType(int x, boolean y) {
			subTreeNum = x;
			isUnival = y;
		}
	}
	
	private class BinaryTreeNode{
		
		public int val = 0;
		public BinaryTreeNode btLeft;
		public BinaryTreeNode btRight;
		
		public BinaryTreeNode(int x) {
			val = x;
		}
		
		public void addChildren(BinaryTreeNode t1, BinaryTreeNode t2) {
			btLeft = (t1);
			btRight = (t2);
		}		
	}
}
