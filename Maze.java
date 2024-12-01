import java.io.*;
import java.util.*;

public class Maze {
    private Graph graph;
    private GraphNode entranceNode;
    private GraphNode exitNode;
    private int numCoins;

    // Constructor
    public Maze(String inputFile) throws MazeException {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			int scaleFactor = Integer.parseInt(br.readLine().trim()); // S (ignored)
			int width = Integer.parseInt(br.readLine().trim()); // A
			int length = Integer.parseInt(br.readLine().trim()); // L
			numCoins = Integer.parseInt(br.readLine().trim()); // k
	
			// Create graph with total nodes
			graph = new Graph(width * length);
	
			String line;
			int row = 0;
	
			// Process maze rows
			while ((line = br.readLine()) != null) {
				char[] chars = line.toCharArray();
	
				if (chars.length != width) {
					throw new MazeException("Invalid input format: Row width mismatch.");
				}
	
				for (int col = 0; col < chars.length; col++) {
					int currentIndex = row * width + col;
					GraphNode currentNode = graph.getNode(currentIndex);
	
					char c = chars[col];
					if (c == 's') entranceNode = currentNode;
					if (c == 'x') exitNode = currentNode;
	
					// Process horizontal edges (left to right)
					if (col > 0) {
						char prevChar = chars[col - 1];
						processEdge(currentIndex, currentIndex - 1, prevChar);
					}
	
					// Process vertical edges (top to bottom)
					if (row > 0) {
						String prevRow = br.readLine();
						char verticalChar = prevRow.charAt(col);
						processEdge(currentIndex, currentIndex - width, verticalChar);
					}
				}
				row++;
			}
	
			if (entranceNode == null || exitNode == null) {
				throw new MazeException("Invalid input format: Missing entrance or exit.");
			}
		} catch (IOException | NumberFormatException | GraphException e) {
			throw new MazeException("Invalid input file format.");
		}
	}
    // Processes and adds edges to the graph
    private void processEdge(int currentIndex, int neighborIndex, char edgeChar) throws GraphException {
        GraphNode current = graph.getNode(currentIndex);
        GraphNode neighbor = graph.getNode(neighborIndex);

        if (edgeChar == 'c') {
            graph.insertEdge(current, neighbor, 0, "corridor");
        } else if (Character.isDigit(edgeChar)) {
            graph.insertEdge(current, neighbor, Character.getNumericValue(edgeChar), "door");
        }
    }

    // Returns the graph
    public Graph getGraph() throws MazeException {
        if (graph == null) throw new MazeException("Graph is null.");
        return graph;
    }

    // Finds a solution from entrance to exit
    public Iterator<Integer> solve() {
        List<Integer> path = new ArrayList<>();
        if (dfs(entranceNode, path, numCoins)) {
            return path.iterator();
        }
        return null; // No solution found
    }

    // DFS helper with coin tracking
    private boolean dfs(GraphNode current, List<Integer> path, int remainingCoins) {
        current.mark(true);
        path.add(current.getName());

        if (current.equals(exitNode)) return true;

        try {
            Iterator<GraphEdge> edges = graph.incidentEdges(current);
            while (edges != null && edges.hasNext()) {
                GraphEdge edge = edges.next();
                GraphNode neighbor = edge.firstEndpoint().equals(current)
                        ? edge.secondEndpoint()
                        : edge.firstEndpoint();

                int cost = edge.getType();
                if (!neighbor.isMarked() && remainingCoins >= cost) {
                    if (dfs(neighbor, path, remainingCoins - cost)) {
                        return true;
                    }
                }
            }
        } catch (GraphException e) {
            e.printStackTrace();
        }

        path.remove(path.size() - 1);
        current.mark(false);
        return false;
    }
}