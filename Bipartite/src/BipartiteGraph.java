/*
 Given an undirected graph G, check whether it is bipartite. 
 Recall that a graph is bipartite if its vertices can be divided 
 into two independent sets, U and V, such that no edge connects vertices of the same set.
 
 1:03.53hr
 medium
 */

import java.util.*;
public class BipartiteGraph {

	public static void main(String[] args) {
		BipartiteGraph thisClass = new BipartiteGraph();
		
		//create undirected graph
		UndirVert input = new UndirVert();
		input.setSet(1);
		// v4 makes the graph not bipartite
		UndirVert v1 =new UndirVert(),
				v2 = new UndirVert(),
				v3 = new UndirVert(),
				v4 = new UndirVert(),
				v5 = new UndirVert(),
				v6 = new UndirVert();
		UndirVert[] inArr = {v1,v2},
				v1Arr = {input,v3,v4},
				v2Arr = {input,v6},
				v3Arr = {v1,v4,v5},
				v4Arr = {v1,v3},
				v5Arr = {v3,v6},
				v6Arr = {v2,v5};
		input.addEdges(inArr);
		v1.addEdges(v1Arr);
		v2.addEdges(v2Arr);
		v3.addEdges(v3Arr);
		v4.addEdges(v4Arr);
		v5.addEdges(v5Arr);
		v6.addEdges(v6Arr);
		
		System.out.println("This graph is bipartite: "+ thisClass.checkBipartite(input));		
	}
	
	public boolean checkBipartite(UndirVert input) {
		
		if(input.edges.size() == 0) return false;//edge case one vert
		
		//breadth first search
		Queue<UndirVert> queue = new LinkedList<UndirVert>();
		queue.add(input);		
				
		return bFSearch(queue);
	}
	
	public boolean bFSearch(Queue<UndirVert> queue) {
		//check other verts assign set and add to queue
		UndirVert input = queue.poll();
		
		for (int i = 0; i < input.edges.size(); i++) {
			if(input.edges.get(i).set == 0) {
				//set to opposite of current
				if(input.set == 1) {
					input.edges.get(i).set =2;
					queue.add(input.edges.get(i));
				}
				if(input.set == 2) {
					input.edges.get(i).set =1;
					queue.add(input.edges.get(i));
				}
			}else if(input.set == input.edges.get(i).set) {
				return false;
			}
		}
		while(queue.isEmpty() != true) {
			if(bFSearch(queue) == false) {
				return false;
			}
		}
		//if queue is empty return true
		return true;
	}
	
	private static class UndirVert{
		
		int set = 0;//0 = default, 1 = set U, 2 = set V
		ArrayList<UndirVert> edges = new ArrayList<UndirVert>();
			
		public void setSet(int i) {
			set = i;
		}
		public void addEdge(UndirVert newEdge) {
			edges.add(newEdge);
		}
		public void addEdges(UndirVert[] newEdges) {
			for (int i = 0; i < newEdges.length; i++) {
				edges.add(newEdges[i]);
			}
		}
		
	}
}
