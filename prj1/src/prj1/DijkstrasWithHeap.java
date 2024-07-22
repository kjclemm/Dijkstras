package prj1;

import java.util.ArrayList;

/**
 * 
 * The implementation of Dijkstras shortest path algorithm by using min-heaps
 * 
 * @author Keenan Clemmitt Andrew Hill
 * 
 */

public class DijkstrasWithHeap {

    private int n;
    private ArrayList<EdgeNode>[] adjacent;
    int[] distance;

    /**
     * 
     * Constructor of the class
     * 
     * @param n:     number of nodes of the graph
     * @param edges: the set of edges of the graph. Each row of 'edges' is in the
     *               form of [u, v, w], which means that there is an edge between u
     *               and v with weight w. So edges[i][0] and edges[i][1] are the
     *               end-points of the i-th edge and edges[i][2] is its weight
     * 
     */

    @SuppressWarnings("unchecked")

    public DijkstrasWithHeap(int n, int[][] edges) {
	this.n = n;
	adjacent = new ArrayList[n];
	distance = new int[n];
	for (int i = 0; i < edges.length; i++) { // loops through each given edge in the 2D array
	    int src = edges[i][0];
	    int dest = edges[i][1];
	    int weight = edges[i][2];
	    if (adjacent[src - 1] == null) { // node has not yet been initialized
		adjacent[src - 1] = new ArrayList<EdgeNode>();
	    }
	    if (adjacent[dest - 1] == null) { // node has not yet been initialized
		adjacent[dest - 1] = new ArrayList<EdgeNode>();
	    }
	    adjacent[src - 1].add(new EdgeNode(weight, dest)); // sets the values of the node in the adjacent list to
							       // the connected edges with their weight and connected
							       // node
	    adjacent[dest - 1].add(new EdgeNode(weight, src));
	}
	for (int i = 0; i < distance.length; i++) { // sets the values of the distances array to max integer
	    distance[i] = Integer.MAX_VALUE;

	}

    }

    /**
     * 
     * This method computes and returns the distances of all nodes of the graph from
     * the source node
     * 
     * @param source: the starting point of the graph
     * @return an array containing the distances of the nodes of the graph from
     *         source. Element i of the returned array represents the distance of
     *         node i from the source
     * 
     */

    public int[] run(int source) {
	boolean[] visited = new boolean[n]; // creates an array to keep track of the visited nodes
	boolean[] inHeap = new boolean[n]; // keeps track of which nodes are in the heap
	visited[source - 1] = true; // sets the source node to visited
	inHeap[source - 1] = true; // sets the source node as in heap
	int count = 1; // number of visited nodes
	distance[source - 1] = 0; // sets distance of source node to 0
	MinHeap mh = new MinHeap(n, 2);
	int currentNode = source - 1;
	while (count < n && currentNode != -1) { // loops until all nodes have been visited or a node that does not
						 // exist in the graph gets found
	    if (adjacent[currentNode] != null) { // checks to make sure the current node is not empty
		for (int i = 0; i < adjacent[currentNode].size(); i++) { // loops through all connected components
		    int weight = adjacent[currentNode].get(i).getWeight(); // gets weight of edge
		    int connected = adjacent[currentNode].get(i).getConnectedNode(); // gets connected node of edge
		    if (!visited[connected - 1]) { // checks if the node at the other side of an edge is visited
			if (inHeap[connected - 1]) { // if not visited and in heap then decrease value in heap and then
						     // reheap
			    mh.decreaseKey(connected, distance[currentNode] + weight);
			} else { // node is not in heap so insert value into heap
			    mh.insert(connected, findMinDistance(currentNode, connected - 1, weight, visited));
			    inHeap[connected - 1] = true;
			}
		    }
		}
		int[] extracted = new int[2];
		extracted = mh.extractMin();
		adjacent[currentNode] = null; // saves memory
		currentNode = extracted[0] - 1;

		if (currentNode != -1) { // checks if current node is in the graph lowest node id should be zero
		    inHeap[currentNode] = false;
		    distance[currentNode] = extracted[1]; // update the distance of the extracted node
		    visited[currentNode] = true;
		}
	    }
	    count++;
	}
	for (int i = 0; i < n; i++) { // if the node does not exist in the graph update value to -1
	    if (!visited[i]) {
		distance[i] = -1;
	    }
	}
	adjacent = null;
	return distance;
    }

    /**
     * 
     * Updates the distance of the unexplored node if the distance is less and then
     * returns the value of this node then stores in a heap
     * 
     * @param exploredNode:   the current node in the graph
     * @param unexploredNode: One of the connected nodes to the current node
     * @param weight:         The weight of the edge connecting the nodes
     * @param visited:        The array that indicates if a node has been visited or
     *                        not
     * @return the old distance of the unexplored node or the updated distance
     *         depending on which one is the minimum
     * 
     */

    private int findMinDistance(int exploredNode, int unexploredNode, int weight, boolean[] visited) {
	if (!visited[unexploredNode] || distance[unexploredNode] > distance[exploredNode] + weight) {
	    return distance[exploredNode] + weight;
	}
	return distance[unexploredNode];

    }

}