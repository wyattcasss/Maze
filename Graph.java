//Author Wyatt Cassiotis 251369984
//In this file it creates a graph that repersents the maze and performs certain action on the graph
import java.util.*;
public class Graph implements GraphADT {
    private Map<GraphNode, List<GraphEdge>> adjacencyList; // Maps nodes to their edges
    private Map<Integer, GraphNode> nodes; // Maps node names to GraphNode objects

	/**
     * Makes a graph with a certain amount of nodes with no edges
	 * @param n number of nodes
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
     * Adds an edge connecting nodes u and v with the type and label prvoided
	 * @param nodeu The first point of the edge
	 * @param nodev The second point of the edge
	 * @param type The type of the edge
	 * @param label A label for the edge
	 * @throws GraphException If one or both nodes do not exist in the graph or an edge already exists
	 */
	@Override
    public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
        if (!adjacencyList.containsKey(nodeu) || !adjacencyList.containsKey(nodev)) {
            throw new GraphException("One or both nodes do not exist in the graph.");
        }

        //Checks if a certain edge is already checked
        for (GraphEdge edge : adjacencyList.get(nodeu)) {
            if (edge.secondEndpoint().equals(nodev)) {
                throw new GraphException("An edge already exists between these nodes.");
            }
        }

        //Add the edges
        GraphEdge edge = new GraphEdge(nodeu, nodev, type, label);
        adjacencyList.get(nodeu).add(edge);
        adjacencyList.get(nodev).add(edge); 
    }


    
	/**
	 * Getter method that gets a node with a certain name
	 * @param name The name of the node that needs to be recieved 
	 * @return The node with the name that we would like to find
	 * @throws GraphException If there isnt a node with the name we are searching for
	 */
	@Override
    public GraphNode getNode(int name) throws GraphException {
        if (!nodes.containsKey(name)) {
            throw new GraphException("Node with name " + name + " does not exist.");
        }
        return nodes.get(name);
    }

	/**
	 * Gives an iterator over the edges to the node
	 * @param u The node we'd like to retrive the info on
	 * @return NULL or the iterator at the edge
	 * @throws GraphException If a node doesnt exist in the graph
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
	 * Gives the edge that connects to the given node
	 * @param u The first node of the edge
	 * @param v The second node of the edge
	 * @return The edge that is connected to the given node 
	 * @throws GraphException If any node edge doesnt exist
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
	 * Checks if two nodes are adjacent in the graph
	 * @param u The first node
	 * @param v The second node
	 * @return true if there is an edge and false otherwise
	 * @throws GraphException If either node doesnt exist on the graph
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