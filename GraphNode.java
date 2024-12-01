public class GraphNode {

    // Variables for the node's name and mark
    private int name;        // The name of the node
    private boolean marked;  // Whether the node is marked or not

    /**
     * Constructor for GraphNode.
     * Initializes the node with the given name.
     *
     * @param name The name of the node.
     */
    public GraphNode(int name) {
        this.name = name;  // Assign the name
        this.marked = false;  // Default the mark to false
    }

    /**
     * Sets the marked value for the node.
     *
     * @param mark The value to set (true or false).
     */
    public void mark(boolean mark) {
        this.marked = mark;
    }

    /**
     * Returns whether the node is marked.
     *
     * @return The marked value (true or false).
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * Returns the name of the node.
     *
     * @return The name of the node.
     */
    public int getName() {
        return this.name;
    }
}