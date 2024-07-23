# Dijkstras

## Overview
This is an implementation of Dijkstras shortest path algorithm. It is implemented in two ways first with a self created binary min-heap which stores the distance estimates from the source to the unexplored vertices. The other method is by picking the smallest distance estminate (from the source) by simply scanning the unexplored vertices (without using any min-heaps or other data structures).

## Min-heap Implementation
The min-heap data structure supports the following operation (a) insert, (b) decrease key, and (c) extract min. Each element in the min-heap has an id and a value associated with it, where id of each node is a unique number between 1 and n and value is an integer. The following classes are present:<br><br>
Public MinHeap(int n, int d): This is the construcotr of the class. Given the maximum number of elements in  the min-heap n and the parameter d of the d-ary structure, this method initializes the data structure.<br><br>
public void insert(int id, int value): this method inserts a new element with id id and value value into the data strucutre. Inserted elements will have unique ids between 1 and n.<br>
public void decreaseKey(int id, int newValue): Given the id of one of hte existing nodes of the data strucutre and a value newValue, where newValue is less than the current value of node, this method updates the data structure accordingly.<br><br>
public int[] extractMin(): This method extracts the value of the minimum element in the data structure and removes it. If the minimum element has an id id and a value value, then tis method returns an array of two integers in the form of [id, value].<br><br>
public int[] getHeap(): This method should return the array represenation of the min-heap; i.e, an array of integers where the element at index 0 contains the value of the root, the elements at indices 2 to d+1 contain the vlaues of the children of the root, and so on.<br><br>
HeapNode: Each object of this class represents a single node in the heap. The objects of this class store an int value and an int id, where both will get values in the constructor. The id can be used to identify the node when we want to implement the decrease key operation.<br>

## Dijkstra's Algorithm with/without min-heap
public DijkstrasWithHeap (int n, int[][] edges) and public DijkstrasWithoutHeap (int n, int[][] edges): These are the constructors of the classes. n is the number of nodes of the graph, where the nodes are numbered from 1 to n,  and edges is a 2-dimensional array, where each row of it has the form [u, v, w], where<br>
u: one end-point of the edge,<br>
v: another end-point of the edge, and<br>
w: the weight of the edge.<br>
      So the i-th edge of the graph is an edge between edges[i][0] and edges[i][1] and has a weight equal to edges[i][2].<br>

public int[] run(int source): This method runs the Dijkstra's shortest path algorithm from the source vertex and returns an array of integers containing the distances of the nodes from the source vertex; i.e, the i-th element of the returned array is the distance of node i from the source. If a node i is not reachable from the source vertex, then the i-th element of the returned array should be -1.


