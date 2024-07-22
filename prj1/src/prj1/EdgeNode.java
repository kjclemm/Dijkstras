/**
 * 
 */
package prj1;

/**
 * @author Keenan Clemmitt Andrew Hill
 * 
 *         This class stores weight of a given edge and one of the connected
 *         nodes in an edge
 */
public class EdgeNode {
    private int weight;
    private int connectedNode;

    /**
     * Constructor for the EdgeNode
     * 
     * @param weight:        weight of the edge
     * @param connectedNode: the node that is connected to the edge
     */
    public EdgeNode(int weight, int connectedNode) {
	this.weight = weight;
	this.connectedNode = connectedNode;
    }

    /**
     * 
     * @return the weight of the edge
     */
    public int getWeight() {
	return weight;
    }

    /**
     * 
     * @return the connectedNode of the edge
     */
    public int getConnectedNode() {
	return connectedNode;
    }
}
