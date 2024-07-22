package prj1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Pouyan
 *
 */
public class TimeComparator {
    /**
     * This class executes your implementations of the Dijkstras shortest path
     * algorithm on some sample graphs and prints the running time
     * 
     * @param args
     */
    public static void main(String[] args) {
	// reading the input file
	System.out.println("Reading the graph ...");

	// Here, you can choose between graph_1 to graph_6. For graph_1, graph_2
	// and graph_3, we expect to see that the Dijkstra's without heap
	// perform better while for graph_4, graph_5, and graph_6 we expect to
	// find Dijkstra's with heap to be more efficient
	Graph graph = readFile("graphs/graph_3.txt");

	// number of repeating the test
	int n = 30;

	// executing the DijkstrasWithoutHeap method on the graph
	System.out.println("Executing Dijkstras without heap ...");
	DijkstrasWithoutHeap[] dWithouts = new DijkstrasWithoutHeap[n];
	long start = System.currentTimeMillis();
	for (int i = 0; i < n; i++) {
	    dWithouts[i] = new DijkstrasWithoutHeap(graph.n, graph.edges);
	    dWithouts[i].run(graph.source);
	}
	System.out
		.println("Average execution time of Dijkstra's without heap: " + (System.currentTimeMillis() - start));

	// executing the DijkstrasWithHeap method on the graph
	System.out.println("Executing Dijkstras with heap ...");
	DijkstrasWithHeap[] dWiths = new DijkstrasWithHeap[n];
	start = System.currentTimeMillis();
	for (int i = 0; i < n; i++) {
	    dWiths[i] = new DijkstrasWithHeap(graph.n, graph.edges);
	    dWiths[i].run(graph.source);
	}
	System.out.println("Average execution time of Dijkstra's with heap: " + (System.currentTimeMillis() - start));
    }

    /**
     * This method reads a file and returns the data of the graph You do not need to
     * change anything inside this method
     * 
     * @param name The name of the file
     * @return graph
     */
    private static Graph readFile(String name) {
	int n = 0, m, source = 0;
	int count = 0;
	int[][] edges = new int[1][1];
	try {
	    File myObj = new File(name);
	    Scanner myReader = new Scanner(myObj);
	    while (myReader.hasNextLine()) {
		String data = myReader.nextLine();
		String[] splited = data.split(" ", 0);
		if (n == 0) {
		    n = Integer.valueOf(splited[0]);
		    m = Integer.valueOf(splited[1]);
		    source = Integer.valueOf(splited[2]);
		    edges = new int[m][3];
		} else {
		    edges[count][0] = Integer.valueOf(splited[0]);
		    edges[count][1] = Integer.valueOf(splited[1]);
		    edges[count][2] = Integer.valueOf(splited[2]);
		    count += 1;
		}
	    }
	    myReader.close();
	} catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	}
	return new Graph(n, edges, source);
    }
}

/**
 * Just a simple class to store the graph It is used to pass multiple
 * information from one method to another
 * 
 * @author Pouyan
 *
 */
class Graph {
    public int n;
    public int[][] edges;
    public int source;

    public Graph(int n, int[][] edges, int source) {
	this.n = n;
	this.edges = edges;
	this.source = source;
    }
}
