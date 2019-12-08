/*
 Write a program that computes the length of the longest common 
 subsequence of three given strings. For example, given "epidemiologist", 
 "refrigeration", and "supercalifragilisticexpialodocious", 
 it should return 5, since the longest common subsequence is "eieio".
 
 hard
 5:22hr
 */

import java.util.*;
public class LongSubseq {

	public static void main(String[] args) {
		
		LongSubseq thisClass = new LongSubseq();
		String inA = "epidemiologist";
		String inB = "refrigeration";
		String inC = "supercalifragilisticexpialodocious";//eigiiteiooio
		
		int output = thisClass.findLongestSub(inA,inB,inC);
		
		System.out.println("Of "+inA+", "+inB+", and "+inC+
				" the longest subsequence is length of "+output);			
	}
	/*
	 compare two shortest strings and put them in a list<char, intPos, intPos2>		 
	 compare list and stripped string to get list2<char, int1,int2,int3>
	 then make subsequence tree from list2 
	 finally search all branches for max length and return.		 
	 */
	public int findLongestSub(String inA, String inB, String inC) {
		
		//ideas: strip unused letters from longest string,
		//for tree making: node becomes a child if strictly larger than node but not any of its children, 
		//if node is strictly larger than any child it become a child to those nodes instead (recursive) 
		//then store every subsequence and sort them by length.
		String inS = inA;//smallest
		String inM = inB;
		String inL = inC;//longest
		
		//sorts inputs
		if(inM.length() > inL.length()) {
			inM = inC;
			inL = inB;
		}
		if(inS.length() > inM.length()) {
			String tmp = inS;
			inS = inM;
			inM = tmp;			
			if(inM.length() > inL.length()) {
				tmp = inM;
				inM = inL;
				inL = tmp;	
			}
		}
		
		//make smallest string into list<Node>
		LinkedList<Node> listS = new LinkedList<Node>();
		for (int i = 0; i < inS.length(); i++) {
			listS.add(new Node(inS.charAt(i),i));			
		}
				
		//create node list and usedChar list		
		LinkedList<Node> listM = createList(inM,listS,2);
		
		//create longer node list 
		LinkedList<Node> listL = createList(inL,listM,3);
		/*
		System.out.println(listL.size());
		for(Node n : listL) {
			System.out.println(n.mainVal+" "+n.firVal+" "+n.secVal+" "+n.thiVal);
		} */				
		
		//make subsequence tree //recursive
		Node root = new Node();
		makeTree(listL, root, 0);
		
		//search tree for longest path
		//noting which nodes in list have been searched
		return dynamicDFS(root)-1;		
	}
	
	public LinkedList<Node>  createList(String inStr, LinkedList<Node> nodeList, int itteration) {
		LinkedList<Node> outList = new LinkedList<Node>();
		//boolean[] taken = new boolean[inStr.length()];//all false, used to eliminate used chars//Produces errors
		
			for(Node nde : nodeList) {
				for (int i = 0; i < inStr.length(); i++) {
					if(nde.mainVal == inStr.charAt(i) ) {//&& taken[i]==false) {
						//taken[i] = true;
						Node ndeToAdd = new Node(nde.mainVal, nde.firVal);
						if(itteration == 2) {
							ndeToAdd.secVal =i;
						}
						if(itteration == 3) {
							ndeToAdd.secVal = nde.secVal;
							ndeToAdd.thiVal = i;
						}
						outList.add(ndeToAdd);
						//break;
					}
				}
			}		
		return outList;
	}
	
	public void makeTree(LinkedList<Node> mainLst ,Node prnt ,int listMark) {
		
		for (int i = listMark; i < mainLst.size(); i++) {
			Node current = mainLst.get(i);
			if(current.firVal> prnt.firVal &&
				current.secVal> prnt.secVal && 
				current.thiVal> prnt.thiVal) {
				prnt.addKid(current);
				makeTree(mainLst, current,i+1);
			}
		}		
	}
	
	public int dynamicDFS(Node nde) {
				
		if(nde.kidLeng == 0) {
			for(Node kid : nde.kids) {
				int longestBranch = dynamicDFS(kid);
				if(longestBranch > nde.kidLeng) {
					nde.setKidLength(longestBranch);
				}
			}
			return nde.kidLeng+1;
		}else {
			return nde.kidLeng+1;
		}	
	}
	
	private class Node{
		LinkedList<Node> kids = new LinkedList<Node>();
		char mainVal;
		int firVal;
		int secVal;
		int thiVal;
		int kidLeng = 0;
				
		public Node() {
			firVal = secVal = thiVal = -1;
		}	
		public Node(char setChar, int first) {
			mainVal = setChar;
			firVal = first;
		}				
		public void addKid(Node kid) {
			kids.add(kid);
		}	
		public void setKidLength(int mxLeng) {
			kidLeng = mxLeng;
		}
	}	
}
