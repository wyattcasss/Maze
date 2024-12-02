//Author Wyatt Cassiotis 251369984
//In this file we interact with everything edges constructing them, getting them, setting them
public class GraphEdge {
    private GraphNode origin; 
    private GraphNode destination; 
    private int type; 
    private String label; 

    /**
     * Constructor method to set the varibles
     * @param u The first point of the edge
     * @param v The second point of the edge
     * @param type The type of the edge
     * @param label The label of the edge
     */
    public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
        this.origin = u;
        this.destination = v;
        this.type = type;
        this.label = label;
    }
    /**
     * Returns the first point of the edge
     * @return The first point of the edge
     */
    public GraphNode firstEndpoint() {
        return this.origin;
    }
    /**
     * Returns the second point of the edge
     * @return The second point of the edge
     */
    public GraphNode secondEndpoint() {
        return this.destination;
    }
    /**
     * Getter method that gets the type of the edge
     * @return The type of the edge
     */
    public int getType() {
        return this.type;
    }
    /**
     * Setter method that sets the type of edge 
     * @param newType The new type of the edge
     */
    public void setType(int newType) {
        this.type = newType;
    }
    /**
     * Getter method that gets the label of the edge
     * @return The label of the edge
     */
    public String getLabel() {
        return this.label;
    }
    /**
     * Setter method that sets the label of the edge 
     * @param newLabel The new label of the edge
     */
    public void setLabel(String newLabel) {
        this.label = newLabel;
    }
}