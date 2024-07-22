package prj1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Some unit tests that you can use to test your code Try to further test your
 * code with multiple other examples
 * 
 * Note: You are not required to submit any tests and Web-CAT does not execute
 * your tests for grading
 * 
 * @author Pouyan
 *
 */
public class TestCases {

    @Test
    public void testMinHeapInsert() {
	// Initializing the min-heap
	int n = 5;
	int d = 2;
	int[] values = { 10, 5, 3, 9, 1 };

	MinHeap mh = new MinHeap(n, d);

	// Testing the insert method
	mh.insert(1, values[0]);
	mh.insert(2, values[1]);

	// Here, we expect that the node corresponding to value 5 be the root
	// node of heap
	int[] heap = mh.getHeap();
	assertEquals(heap.length, 2); // checking if the length of heap is 2
	assertEquals(heap[0], 5);
	assertEquals(heap[1], 10);

	mh.insert(3, values[2]);
	// Here, we expect that the node corresponding to value 3 be the root
	// node of heap
	heap = mh.getHeap();
	assertEquals(heap.length, 3);
	assertEquals(heap[0], 3);

	mh.insert(4, values[3]);
	mh.insert(5, values[4]);
	// Here, we expect that the node corresponding to value 1 be the root
	// node of heap
	heap = mh.getHeap();
	assertEquals(heap.length, 5);

	assertEquals(heap[0], 1);
	// The children of node at index 1 are the nodes at indices 3 and 4
	// We want to make sure that the node at index 1 is smaller than its
	// children

	assertTrue(heap[1] < heap[3]);
	assertTrue(heap[1] < heap[4]);
    }

    @Test
    public void testMinHeapExtractMin() {
	// Initializing the min-heap
	int n = 5;
	int extractMinsNum = 5;
	int d = 3;
	int[] values = { 10, 5, 3, 9, 1 };

	MinHeap mh = new MinHeap(n, d);

	// Inserting the elements to the heap
	for (int i = 0; i < n; i++) {
	    mh.insert(i + 1, values[i]);
	}
	// Testing extract min operation
	int[] expectedOrder = { 1, 3, 5, 9, 10, 4, 6, 7, 8, 9 };
	int[] expectedIds = { 5, 3, 2, 4, 1, 6, 7, 8, 9, 10 };
	for (int i = 0; i < extractMinsNum; i++) {
	    int[] extracted = mh.extractMin();
	    assertEquals(expectedOrder[i], extracted[1]);
	    assertEquals(expectedIds[i], extracted[0]);

	    assertEquals(n - i - 1, mh.getHeap().length);
	}
    }

    @Test
    public void testMinHeapDecreaseKey() {
	// Initializing the min-heap
	int n = 5;
	int d = 4;
	int[] values = { 10, 5, 3, 9, 1 };

	MinHeap mh = new MinHeap(n, d);

	// Inserting the elements to the heap
	for (int i = 0; i < n; i++) {
	    mh.insert(i, values[i]);
	}

	// Testing decrease key operation
	mh.decreaseKey(1, 2);
	// The root node should still be 1
	int[] heap = mh.getHeap();
	assertEquals(heap[0], 1);
	mh.decreaseKey(4, 0);
	// The root node now should be 0
	heap = mh.getHeap();
	assertEquals(heap[0], 0);

	mh.decreaseKey(3, -10);
	// The root node now should be 0
	heap = mh.getHeap();
	assertEquals(heap[0], -10);
    }

    @Test
    public void testDijkstra1() {
	// Creating a graph with 5 nodes and 5 edges
	int n = 5;
	int[][] edges = { { 1, 2, 50 }, { 2, 3, 10 }, { 3, 4, 20 }, { 3, 5, 50 }, { 4, 5, 10 } };
	int source = 2;
	// The distance of each node from the source vertex
	int[] expectedDistances = { 50, 0, 10, 30, 40 };

	// Running the Dijkstras implementation that uses the min-heap
	DijkstrasWithHeap dWith = new DijkstrasWithHeap(n, edges);
	int[] distancesWith = dWith.run(source);

	// Checking whether the returned distances match the expected distances
	// or not
	for (int i = 0; i < n; i++) {
	    assertEquals("There seems to be a problem with your dijkstrasWithHeap implementation", distancesWith[i],
		    expectedDistances[i]);
	}

	// Running the Dijkstras implementation that does not use the min-heap
	DijkstrasWithoutHeap dWithout = new DijkstrasWithoutHeap(n, edges);
	int[] distancesWithout = dWithout.run(source);

	// Checking whether the returned distances match the expected distances
	// or not
	for (int i = 0; i < n; i++) {
	    assertEquals("There seems to be a problem with your dijkstrasWithoutHeap implementation",
		    distancesWithout[i], expectedDistances[i]);
	}
    }

    @Test
    public void testDijkstra2() {
	// Creating a graph with 5 nodes and 6 edges
	int n = 5;
	int[][] edges = { { 1, 3, 50 }, { 1, 4, 100 }, { 1, 5, 20 }, { 3, 4, 10 }, { 3, 5, 20 }, { 4, 5, 50 } };
	int source = 1;
	// The distance of each node from the source vertex
	int[] expectedDistances = { 0, -1, 40, 50, 20 };

	// Running the Dijkstras implementation that uses the min-heap
	DijkstrasWithHeap dWith = new DijkstrasWithHeap(n, edges);
	int[] distancesWith = dWith.run(source);

	// Checking whether the returned distances match the expected distances
	// or not
	for (int i = 0; i < n; i++) {
	    assertEquals(distancesWith[i], expectedDistances[i]);
	}

	// Running the Dijkstras implementation that does not use the min-heap
	DijkstrasWithoutHeap dWithout = new DijkstrasWithoutHeap(n, edges);
	int[] distancesWithout = dWithout.run(source);

	// Checking whether the returned distances match the expected distances
	// or not
	for (int i = 0; i < n; i++) {
	    assertEquals(distancesWithout[i], expectedDistances[i]);
	}
    }

}
