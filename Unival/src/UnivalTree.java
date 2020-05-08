import java.util.ArrayList;

/*
A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.

Given the root to a binary tree, count the number of unival subtrees.

For example, the following tree has 5 unival subtrees:

   0
  / \
 1   0
    / \
   1   0
  / \
 1   1


time:
easy
 */

public class UnivalTree {

	public static void main(String[] args) {
		
		UnivalTree inst = new UnivalTree();
		TreeNode tn = inst.createTree();
		
		int output = inst.countUnivalSubTrees(0, tn);
		System.out.println("The following Tree has "+output+" unival subtrees");
	}
	
	public TreeNode createTree() {
		TreeNode tn = new TreeNode(0);
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(0);
		TreeNode t3 = new TreeNode(1);
		TreeNode t4 = new TreeNode(0);
		TreeNode t5 = new TreeNode(1);
		TreeNode t6 = new TreeNode(1);
		
		tn.addChildren(t1,t2);
		t2.addChildren(t3, t4);
		t3.addChildren(t5, t6);
		return tn;
	}
	
	public int countUnivalSubTrees(int val, TreeNode tn) {
		
		
		return 0;
	}
		
	private class TreeNode{
		
		public int val = 0;
		public ArrayList<TreeNode> children;
		
		public TreeNode(int x) {
			val = x;
		}
		
		public void addChild(TreeNode tn) {
			children.add(tn);
		}
		
		public void addChildren(TreeNode t1, TreeNode t2) {
			children.add(t1);
			children.add(t2);
		}		
	}
}
