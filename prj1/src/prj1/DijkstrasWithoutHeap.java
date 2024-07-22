package prj1;

import java.util.ArrayList;

/**
 * The implementation of Dijkstras shortest path algorithm by using a simple
 * linear search to find the unvisited node with the minimum distance estimate
 * 
 * @author Andrew Hill and Keenan Clemmitt
 * @version 1.1
 */
public class DijkstrasWithoutHeap {

    // private int[][] graph;
    private ArrayList<EdgeNode>[] adjacent;
    private int number;

    /**
     * Constructor of the class
     * 
     * @param n:     number of nodes of the graph
     * @param edges: the set of edges of the graph. Each row of 'edges' is in the
     *               form of [u, v, w], which means that there is an edge between u
     *               and v with weight w. So edges[i][0] and edges[i][1] are the
     *               end-points of the i-th edge and edges[i][2] is its weight
     */
    public DijkstrasWithoutHeap(int n, int[][] edges) {
	this.number = n;
	// this.graph = new int[n][n];
	adjacent = new ArrayList[n];
	for (int i = 0; i < edges.length; i++) { // creates all of the edges along with their weight in a 2d array
	    int src = edges[i][0];// source edge
	    int dest = edges[i][1];// destination edge
	    int weight = edges[i][2];// weight of the edge

	    if (adjacent[src - 1] == null) {
		adjacent[src - 1] = new ArrayList<EdgeNode>();
	    }
	    if (adjacent[dest - 1] == null) {
		adjacent[dest - 1] = new ArrayList<EdgeNode>();
	    }
	    adjacent[src - 1].add(new EdgeNode(weight, dest));
	    adjacent[dest - 1].add(new EdgeNode(weight, src));
	    // graph[src - 1][dest - 1] = weight;
	    // graph[dest - 1][src - 1] = weight;

	}

    }

    /**
     * This method computes and returns the distances of all nodes of the graph from
     * the source node
     * 
     * @param source
     * 
     * @return an array containing the distances of the nodes of the graph from
     *         source. Element i of the returned array represents the distance of
     *         node i from the source
     */
    public int[] run(int source) {
	int[] distances = new int[number];
	boolean[] visited = new boolean[number];
	for (int i = 0; i < distances.length; i++) { // This populates the distances array with the max integer value
	    distances[i] = Integer.MAX_VALUE;
	}
	distances[source - 1] = 0; // sets the source node to 0
	visited[source - 1] = true;
	int next = source - 1;
	for (int i = 0; i < number - 1; i++) { // iterates over all of the nodes changing the distances as it goes
	    // This changes the distances to the correct values} // If there is a node
	    // that doesn't exist then it's value will /
	    // if (next != 0) {
	    changeDistances(next, distances);
	    next = nextNode(distances, visited, next);
	    if (distances[next] == Integer.MAX_VALUE) {
		break;
	    }
	    visited[next] = true;
	    // }
	}
	for (int i = 0; i < distances.length; i++) { // If there is a node that doesn't exist then it's value will be
						     // the maximum integer value
	    if (distances[i] == Integer.MAX_VALUE) { // This loop changes the max value to -1.
		distances[i] = -1;
	    }
	}
	adjacent = null;
	return distances;
    }

    /**
     * 
     * @param distances
     * @param visited
     * @return the next node in the distances array or 0 if every node has been
     *         visited
     */
    private int nextNode(int[] distances, boolean[] visited, int current) {
	int min = Integer.MAX_VALUE;
	int nextNode = 0;
	if (adjacent[current] != null) {
	    for (int i = 0; i < distances.length; i++) {
		if (!visited[i] && distances[i] < min) {
		    min = distances[i];
		    nextNode = i;
		}
	    }
	}
	return nextNode;
    }

    /**
     * This will change the distances in the distance array to the correct value.
     * 
     * @param node
     * @param distances
     * 
     */
    private void changeDistances(int node, int[] distances) {
	if (adjacent[node] != null) {
	    for (int i = 0; i < adjacent[node].size(); i++) { // Iterates through all of the nodes.
		// if the node actually exists in the nodes array then the distance gets added.
		int newDistance = distances[node] + adjacent[node].get(i).getWeight(); // graph[node][i];
		if (newDistance < distances[adjacent[node].get(i).getConnectedNode() - 1]
			&& newDistance != Integer.MAX_VALUE) {
		    distances[adjacent[node].get(i).getConnectedNode() - 1] = newDistance;
		}
	    }
	}
    }

}