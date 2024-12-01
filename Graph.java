import java.util.*;

public class Graph implements GraphADT {
    private Map<GraphNode, List<GraphEdge>> adjacencyList; // Maps nodes to their edges
    private Map<Integer, GraphNode> nodes; // Maps node names to GraphNode objects

    /**
     * Creates an empty graph with n nodes and no edges.
     * Node names are 0, 1, ..., n-1.
     */
    public Graph(int n) {
        adjacencyList = new HashMap<>();
        nodes = new HashMap<>();
        
        // Initialize nodes and adjacency list
        for (int i = 0; i < n; i++) {
            GraphNode node = new GraphNode(i);
            nodes.put(i, node);
            adjacencyList.put(node, new ArrayList<>());
        }
    }

    /**
     * Adds an edge connecting nodes u and v with the specified type and label.
     * Throws GraphException if either node does not exist or if an edge already exists.
     */
    @Override
    public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
        if (!adjacencyList.containsKey(nodeu) || !adjacencyList.containsKey(nodev)) {
            throw new GraphException("One or both nodes do not exist in the graph.");
        }

        // Check if an edge already exists
        for (GraphEdge edge : adjacencyList.get(nodeu)) {
            if (edge.secondEndpoint().equals(nodev)) {
                throw new GraphException("An edge already exists between these nodes.");
            }
        }

        // Add the edge to both nodes
        GraphEdge edge = new GraphEdge(nodeu, nodev, type, label);
        adjacencyList.get(nodeu).add(edge);
        adjacencyList.get(nodev).add(edge); // Because this is an undirected graph
    }

    /**
     * Returns the node with the specified name.
     * Throws GraphException if the node does not exist.
     */
    @Override
    public GraphNode getNode(int name) throws GraphException {
        if (!nodes.containsKey(name)) {
            throw new GraphException("Node with name " + name + " does not exist.");
        }
        return nodes.get(name);
    }

    /**
     * Returns an iterator storing all edges incident on the node u.
     * Returns null if the node has no incident edges.
     * Throws GraphException if the node does not exist.
     */
    @Override
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
        if (!adjacencyList.containsKey(u)) {
            throw new GraphException("Node does not exist in the graph.");
        }
        List<GraphEdge> edges = adjacencyList.get(u);
        return edges.isEmpty() ? null : edges.iterator();
    }

    /**
     * Returns the edge connecting nodes u and v.
     * Throws GraphException if no such edge exists or if the nodes do not exist.
     */
    @Override
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            throw new GraphException("One or both nodes do not exist in the graph.");
        }

        for (GraphEdge edge : adjacencyList.get(u)) {
            if (edge.secondEndpoint().equals(v) || edge.firstEndpoint().equals(v)) {
                return edge;
            }
        }
        throw new GraphException("No edge exists between these nodes.");
    }

    /**
     * Returns true if nodes u and v are adjacent; false otherwise.
     * Throws GraphException if one or both nodes do not exist.
     */
    @Override
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            throw new GraphException("One or both nodes do not exist in the graph.");
        }

        for (GraphEdge edge : adjacencyList.get(u)) {
            if (edge.secondEndpoint().equals(v) || edge.firstEndpoint().equals(v)) {
                return true;
            }
        }
        return false;
    }
}