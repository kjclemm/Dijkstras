package prj1;

/**
 * 
 * You might want to use this class as the nodes of your heap.
 * You are not required to use this class as a part of your implementation. 
 * 
 * @author Pouyan
 *
 */
public class HeapNode {
    private int id;
    private int value;

    /**
     * Constructor 
     * 
     * @param id
     * @param value
     */
    public HeapNode(int id, int value) {
        this.id = id;
        this.value = value;
    }


    /**
     * @return id
     */
    public int getId() {
        return this.id;
    }


    /**
     * @return value
     */
    public int getValue() {
        return this.value;
    }


    /**
     * 
     * @param value: a new value 
     */
    public void setValue(int value) {
        this.value = value;
    }
}
