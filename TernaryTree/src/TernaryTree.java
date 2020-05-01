/*
A ternary search tree is a trie-like data structure where each
 node may have up to three children. Here is an example which 
 represents the words code, cob, be, ax, war, and we.

      c
    /  |  \
   b   o   w
 / |   |   |
a  e   d   a
|    / |   | \ 
x   b  e   r  e  

The tree is structured according to the following rules:

left child nodes link to words lexicographically earlier than the parent prefix
right child nodes link to words lexicographically later than the parent prefix
middle child nodes continue the current word
For instance, since code is the first word inserted in the tree, and cob lexicographically
precedes cod, cob is represented as a left child extending from cod.

Implement insertion and search functions for a ternary search tree.

time: 
easy
 */

import java.util.*;

public class TernaryTree {

	public static void main(String[] args) {
		//not tested for uppercase or for the universal character set
		TernaryTreeNode head = new TernaryTreeNode();
		
		TernaryTree tT = new TernaryTree();
		
		String[] insertStrings = {"code","cob","be","ax","war","we"};
		tT.TTInsertAll(insertStrings, head);
		
		boolean output1 = tT.TTSearch(head, "code" ,0);
		boolean output2 = tT.TTSearch(head, "be" ,0);
		boolean output3 = tT.TTSearch(head, "lol" ,0);
		boolean output4 = tT.TTSearch(head, "cob" ,0);
		boolean output5 = tT.TTSearch(head, "code " ,0);
		boolean output6 = tT.TTSearch(head, "war" ,0);
		
		System.out.println("out1: "+output1+", out2: "+output2+", out3: "+output3+", out4: "+output4+
				", out5: "+output5+", out6: "+output6);		
	}
	
	public void TTInsertAll(String[] inputStr, TernaryTreeNode mainTree) {
		for(String s: inputStr) {
			TTInsert(s,mainTree,0);
		}
	}
	
	public void TTInsert(String inputStr, TernaryTreeNode mainTree, int readCnt) {

		if(inputStr.length() == readCnt) return;
		//if newchar = char then move on with current midChild, if newchar < char make set as left child unless left child is taken
		//at which point move on with leftchild don't increment readCnt. same with rightchild
		System.out.println(inputStr.charAt(readCnt));
		TernaryTreeNode newLeaf = new TernaryTreeNode();
		if(mainTree.nodeVal == '\u0000') {
			System.out.println("null found");
			mainTree.nodeVal = inputStr.charAt(readCnt);
			mainTree.midChild = newLeaf;
			TTInsert(inputStr,mainTree.midChild,readCnt+1);
			
		} else if(mainTree.nodeVal == inputStr.charAt(readCnt)) {
			TTInsert(inputStr,mainTree.midChild,readCnt+1);
			
		} else if(mainTree.nodeVal > inputStr.charAt(readCnt)) {
			if(mainTree.midChild == null) {
				TTInsert(inputStr,mainTree.midChild,readCnt+1);
			}
			else if(mainTree.leftChild == null) {
				newLeaf.nodeVal = inputStr.charAt(readCnt);
				mainTree.leftChild = newLeaf;
				TTInsert(inputStr,mainTree.leftChild,readCnt+1);
			}else {
				TTInsert(inputStr,mainTree.leftChild,readCnt);
			}
		}else if(mainTree.nodeVal < inputStr.charAt(readCnt)){
			if(mainTree.midChild == null) {
				TTInsert(inputStr,mainTree.midChild,readCnt+1);
			}
			else if(mainTree.rightChild == null) {
				newLeaf.nodeVal = inputStr.charAt(readCnt);
				mainTree.rightChild = newLeaf;
				TTInsert(inputStr,mainTree.rightChild,readCnt+1);
			}else {
				TTInsert(inputStr,mainTree.rightChild,readCnt);
			}
		}		//cnt =1, e , at A node
	}
	
	public boolean TTSearch(TernaryTreeNode mainTree, String searchStr, int readCnt) {
		//returns true if term in tree otherwise false
		if(readCnt == searchStr.length()) {
			return true;
		}
		boolean output = false;
		if(mainTree.nodeVal == searchStr.charAt(readCnt)) {
			output = TTSearch(mainTree.midChild,searchStr,readCnt+1);
		}else if(mainTree.nodeVal > searchStr.charAt(readCnt)) {//left
			output = TTSearch(mainTree.leftChild,searchStr,readCnt);
		}else {//right
			output = TTSearch(mainTree.rightChild,searchStr,readCnt);
		}
		
		return output;
	}
	
	public static class TernaryTreeNode{
		public char nodeVal;
		public TernaryTreeNode leftChild;
		public TernaryTreeNode midChild;
		public TernaryTreeNode rightChild;
	}
}

