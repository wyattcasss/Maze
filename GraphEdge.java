public class GraphEdge {

    // Instance variables for the endpoints, type, and label
    private GraphNode origin; // First endpoint of the edge
    private GraphNode destination; // Second endpoint of the edge
    private int type; // Type of the edge
    private String label; // Label of the edge

    /**
     * Constructor for the GraphEdge class.
     * @param u The first endpoint of the edge.
     * @param v The second endpoint of the edge.
     * @param type The type of the edge.
     * @param label The label of the edge.
     */
    public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
        this.origin = u;
        this.destination = v;
        this.type = type;
        this.label = label;
    }

    /**
     * Returns the first endpoint of the edge.
     * @return The first endpoint (origin) of the edge.
     */
    public GraphNode firstEndpoint() {
        return this.origin;
    }

    /**
     * Returns the second endpoint of the edge.
     * @return The second endpoint (destination) of the edge.
     */
    public GraphNode secondEndpoint() {
        return this.destination;
    }

    /**
     * Returns the type of the edge.
     * @return The type of the edge.
     */
    public int getType() {
        return this.type;
    }

    /**
     * Sets the type of the edge to the specified value.
     * @param newType The new type of the edge.
     */
    public void setType(int newType) {
        this.type = newType;
    }

    /**
     * Returns the label of the edge.
     * @return The label of the edge.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the label of the edge to the specified value.
     * @param newLabel The new label of the edge.
     */
    public void setLabel(String newLabel) {
        this.label = newLabel;
    }
}