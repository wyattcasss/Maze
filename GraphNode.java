//Author Wyatt Cassiotis 251369984
//In this file we create, modify and get nodes
public class GraphNode {
    private int name;        
    private boolean marked; 

    /**
     * Constructor method that initializes the node with the given name
     * @param name The name of the node
     */
    public GraphNode(int name) {
        this.name = name;  // Assign the name
        this.marked = false;  // Default the mark to false
    }

    /**
     * Sets the value of the node
     * @param mark The value to set either true or false
     */
    public void mark(boolean mark) {
        this.marked = mark;
    }

    /**
     * Checks wether marked or not
     * @return The marked value true or false
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * Getter method that gets the name of the node
     * @return The name of the node
     */
    public int getName() {
        return this.name;
    }
}