package prj1;

/**
 * In this class, we implement the d-ary min-heap data structure
 * 
 * @author Keenan Clemmitt, Andrew Hill
 *
 */
public class MinHeap {
    // The parameter d in the d-ary min-heap
    private int d;

    // counts the number of nodes currently in the heap
    private int numNodes;

    // The array representation of your min-heap (It is not required to use this)
    private HeapNode[] nodes;

    private int[] locations;

    /**
     * Constructor
     * 
     * @param n: maximum number of elements in the min-heap at each time
     * @param d: parameter d in the d-ary min-heap
     */
    public MinHeap(int n, int d) {
	this.d = d;
	nodes = new HeapNode[n];
	numNodes = 0;
	locations = new int[n + 1];
    }

    /**
     * This method inserts a new element with "id" and "value" into the min-heap
     * 
     * @param id
     * @param value
     */
    public void insert(int id, int value) {
	HeapNode newNode = new HeapNode(id, value);
	if (numNodes == 0) { // case when there are 0 nodes in the heap
	    nodes[0] = newNode;
	    locations[id] = -1; // value -1 will represent 0 to differentiate in the array
	    numNodes++;
	} else if (numNodes < nodes.length) { // case when heap has space and is nonempty
	    nodes[numNodes] = newNode;
	    locations[id] = numNodes;
	    numNodes++;
	    int i = numNodes;
	    while ((i + d - 2) / d > 0) { // bubbles down through the heap unitl location is found
		if (nodes[((i + d - 2) / d) - 1].getValue() > nodes[i - 1].getValue()) {
		    HeapNode temp = nodes[((i + d - 2) / d) - 1];
		    nodes[((i + d - 2) / d) - 1] = nodes[i - 1];
		    nodes[i - 1] = temp;
		    int tempLocation = locations[nodes[i - 1].getId()];
		    locations[nodes[i - 1].getId()] = locations[nodes[((i + d - 2) / d) - 1].getId()];
		    locations[nodes[((i + d - 2) / d) - 1].getId()] = tempLocation;

		}
		i = ((i + d - 2) / d);
	    }
	} else { // case when the heap is full
	    return;
	}
    }

    /**
     * This method extracts the min value of the heap
     * 
     * @return an array consisting of two integer elements: id of the minimum
     *         element and the value of the minimum element
     * 
     *         So for example, if the minimum element has id = 5 and value = 1, you
     *         should return the array [5, 1]
     */

    public int[] extractMin() {
	int[] result = new int[2];
	if (numNodes == 0) { // case with empty heap
	    return result;
	} else if (numNodes == 1) { // case with only one node
	    result[0] = nodes[0].getId();
	    result[1] = nodes[0].getValue();
	    locations[nodes[0].getId()] = 0;
	    nodes[0] = null;
	    numNodes--;
	    return result;
	} else { // case with two or more nodes
	    result[0] = nodes[0].getId();
	    result[1] = nodes[0].getValue();
	    locations[nodes[0].getId()] = 0;
	    nodes[0] = nodes[numNodes - 1];
	    nodes[numNodes - 1] = null;
	    locations[nodes[0].getId()] = -1;
	    numNodes--;
	    int index = 0;
	    int min = findMinInBranch(index);
	    while (min != index) { // if a new min index was found the values are swapped and the index is updated
		// to the new location
		HeapNode temp = nodes[index];
		nodes[index] = nodes[min];
		nodes[min] = temp;
		int tempLocation = locations[nodes[index].getId()];
		locations[nodes[index].getId()] = locations[nodes[min].getId()];
		locations[nodes[min].getId()] = tempLocation;
		index = min;
		min = findMinInBranch(index);
	    }
	    return result;
	}
    }

    /**
     * takes the current index of the node that was swapped with the extracted value
     * and searches through its children to find the minimum child. Once the minimum
     * child is found that value is compared to the node and if the child is less
     * than the node the index of the child is returned
     * 
     * @param currentIndex
     * @return index of the minmun node
     */
    private int findMinInBranch(int currentIndex) {
	int min = currentIndex;
	int i = currentIndex * d;
	while (i + 1 < ((currentIndex + 1) * d) + d && i + 1 <= numNodes) { // loops through each node in the given
									    // branch
	    if (nodes[i].getValue() < nodes[min].getValue()) {
		min = i;
	    }
	    i++;
	}
	return min;
    }

    /**
     * This method takes an id and a new value newValue for the corresponding node,
     * and updates the data structure accordingly
     * 
     * @param id
     * @param newValue
     */
    public void decreaseKey(int id, int newValue) {
	int i = -1;
	int index = locations[id];
	if (index == 0) { // case when the inserted id is not present in the minHeap
	    return;
	}
	if (index == -1) { // when array is created all values are 0 so -1 will be used to represent index
			   // 0
	    index = 0;
	}
	if (nodes[index].getValue() > newValue) {// finds index of node in the array if node with id exists
	    nodes[index].setValue(newValue);
	    i = index;
	}
	if (i == -1) { // node does not exist or list is empty
	    return;
	}
	i++;
	while ((i + d - 2) / d > 0) { // node exists and must be bubbled up
	    if (nodes[((i + d - 2) / d) - 1].getValue() > nodes[i - 1].getValue()) {
		HeapNode temp = nodes[((i + d - 2) / d) - 1];
		nodes[((i + d - 2) / d) - 1] = nodes[i - 1];
		nodes[i - 1] = temp;
		int tempLocation = locations[nodes[((i + d - 2) / d) - 1].getId()];
		locations[nodes[((i + d - 2) / d) - 1].getId()] = locations[nodes[i - 1].getId()];
		locations[nodes[i - 1].getId()] = tempLocation;
	    }
	    i = ((i + d - 2) / d);
	}
    }

    /**
     * This method returns the array representation of heap
     * 
     * @return the array representation of heap
     */
    public int[] getHeap() {
	int[] result = new int[numNodes];
	for (int i = 0; i < numNodes; i++) {
	    result[i] = nodes[i].getValue();
	}
	return result;
    }

    /**
     * the toString method that returns a string with the values of the heap in the
     * array representation. This method can help you find the issues of your code
     * when you want to debug.
     * 
     * @return string form of the array representation of heap
     */
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < this.nodes.length; i++) {
	    if (nodes[i] != null) {
		sb.append(nodes[i].getValue());
		sb.append(' ');
	    }
	}
	return sb.toString();
    }

}