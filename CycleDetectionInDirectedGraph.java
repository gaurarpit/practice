package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* Detect if a Directed graph has a cycle or not */
/* Use DFS to detect cycle in a Directed Graph. 
 * If a Currently visiting node is visited again, that indicates a cycle */


/* Enum for maintaining State of the Nodes in the Graph */
enum State {Unvisited, Visiting, Visited};


/* Structure of the Nodes of a Graph */
class GNode {
	int data;
	State state;
	
	public GNode(){
		// initially every Node is Unvisited
		this.state=State.Unvisited;
	}
}

class Graph{
	
	List<GNode> nodes = new ArrayList<GNode>();
	HashMap<GNode, LinkedList<GNode>> adjacencyList = new HashMap<GNode, LinkedList<GNode>>();
	
	public LinkedList<GNode> adj( GNode n){
		return adjacencyList.get(n);
	}
}

public class CycleDetectionInDirectedGraph {
	
	public static void main(String[] args) {

		GNode a = new GNode();
		a.data=1;
		GNode b = new GNode();
		b.data=2;
		GNode c = new GNode();
		c.data=3;
		GNode d = new GNode();
		d.data=4;
		
		// Create a Graph
		Graph g = new Graph();
		g.nodes.add(a);
		g.nodes.add(b);
		g.nodes.add(c);
		g.nodes.add(d);
		
		LinkedList<GNode> aL = new LinkedList<GNode>();
		LinkedList<GNode> bL = new LinkedList<GNode>();
		LinkedList<GNode> cL = new LinkedList<GNode>();
		LinkedList<GNode> dL = new LinkedList<GNode>();
		aL.add(b);aL.add(c);
		bL.add(d);
	//	cL.add(a);
		g.adjacencyList.put(a, aL);
		g.adjacencyList.put(b, bL);
		g.adjacencyList.put(c, cL);
		g.adjacencyList.put(d, dL);
		
		doDFS(g,a);
	}
	
	
	
	static void doDFS (Graph g, GNode v){
		
		if (v.state == State.Visited)
			return;
		
		v.state = State.Visiting;
		// Visit a Node
		System.out.print(" "+v.data);

		for (GNode w:g.adj(v)){
			if (w.state == State.Visiting){
				// if a "Visiting" Node is Visited again, it is cycle.
				System.out.println("\nCycle detected at "+v.data+"->"+w.data); return;
			} 
			
			// If a Node is visited for the first time, set the State as Visiting
			if (w.state == State.Unvisited){
				w.state = State.Visiting;
			
				doDFS(g,w);
				
				// After the traversal is complete for one Node, mark as "Visited"
				w.state=State.Visited;
			}
		}
		v.state=State.Visited;
	}

}
