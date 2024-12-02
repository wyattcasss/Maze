//Author Wyatt Cassiotis 251369984
//In this file we read maze input files then solve the given maze given the input file
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class Maze {
    private Graph graph;
    private GraphNode entrance;
    private GraphNode exit;
    private int coins;
	/**
	 * creates a maze object from the specified input file
	 * @param inputFile Given file that contains the config of the maze
	 * @throws MazeException If there is an error reading the file or making the maze
	 */
    public Maze(String inputFile) throws MazeException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            readInput(reader);
        } catch (IOException | GraphException e) {
            throw new MazeException("Error reading input file: " + e.getMessage());
        }
    }
	/**
	 * Getter method to retrive the graph
	 * @return The graph that repersents the maze
	 * @throws MazeException If the graph hasnt been made yet
	 */
    public Graph getGraph() throws MazeException {
        if (graph == null) {
            throw new MazeException("Graph is not initialized.");
        }
        return graph;
    }
	
	/**
	 * Solves the maze from the entrance node and attempts to reach the coin node.
	 */
    public Iterator<GraphNode> solve() {
        try {
            return DFS(coins, entrance, new Stack<>());
        } catch (GraphException e) {
            return null;
        }
    }
	/**
	 * Helper function to help solve the maze from the entrance node and attempts to reach the coin node.
	 */
    private Iterator<GraphNode> DFS(int remainingCoins, GraphNode current, Stack<GraphNode> path) throws GraphException {
        path.push(current);
        current.mark(true);

        if (current.equals(exit)) {
            return path.iterator(); // Found the exit
        }

        Iterator<GraphEdge> edges = graph.incidentEdges(current);
        if (edges != null) {
            while (edges.hasNext()) {
                GraphEdge edge = edges.next();
                GraphNode nextNode = edge.secondEndpoint().equals(current) ? edge.firstEndpoint() : edge.secondEndpoint();

                if (!nextNode.isMarked()) {
                    int coinsNeeded = edge.getType();
                    if (coinsNeeded <= remainingCoins) {
                        Iterator<GraphNode> result = DFS(remainingCoins - coinsNeeded, nextNode, path);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }

        path.pop(); // Backtrack
        current.mark(false);
        return null;
    }
    //Helper method that helps read inputs
    private void readInput(BufferedReader reader) throws IOException, GraphException {
        int scaleFactor = Integer.parseInt(reader.readLine().trim());
        int width = Integer.parseInt(reader.readLine().trim());
        int length = Integer.parseInt(reader.readLine().trim());
        coins = Integer.parseInt(reader.readLine().trim());

        graph = new Graph(width * length);

        String[] horizontal = new String[length * 2 - 1];
        for (int i = 0; i < length * 2 - 1; i++) {
            horizontal[i] = reader.readLine().trim();
        }

        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                int currentNode = row * width + col;
                char currentChar = horizontal[row * 2].charAt(col * 2);

                if (currentChar == 's') {
                    entrance = graph.getNode(currentNode);
                } else if (currentChar == 'x') {
                    exit = graph.getNode(currentNode);
                }

                if (col < width - 1) {
                    char rightChar = horizontal[row * 2].charAt(col * 2 + 1);
                    insertEdge(currentNode, currentNode + 1, rightChar);
                }
                if (row < length - 1) {
                    char downChar = horizontal[row * 2 + 1].charAt(col * 2);
                    insertEdge(currentNode, currentNode + width, downChar);
                }
            }
        }
    }
	//Helper method to help insert edges
    private void insertEdge(int node1, int node2, char typeChar) throws GraphException {
        String label;
        int type;

        switch (typeChar) {
            case 'c':
                label = "corridor";
                type = 0;
                break;
            case 'w':
                return; // No edge for walls
            default:
                label = "door";
                type = Character.getNumericValue(typeChar);
                break;
        }

        graph.insertEdge(graph.getNode(node1), graph.getNode(node2), type, label);
    }
}