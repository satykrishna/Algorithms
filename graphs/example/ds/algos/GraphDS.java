package example.ds.algos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraphDS {

	private static final Logger LOGGER = Logger.getLogger(GraphDS.class.getName());

	private int vertexCount;

	private int[][] adjMatrix;

	private Vertex[] vertices;

	// initialize Graph with given number of vertices
	public GraphDS(int numberofVertices) {
		super();
		vertices = new Vertex[numberofVertices];
		for (int v1 = 0; v1 < numberofVertices; ++v1) {
			for (int v2 = 0; v2 < numberofVertices; ++v2) {
				adjMatrix[v1][v2] = adjMatrix[v2][v1] = 0;
			}
		}
		vertexCount = 0;
	}

	public void addVertex(char label) {
		vertices[vertexCount++] = new Vertex(label);
	}

	public void addEdge(int v1, int v2) {
		if (v1 > 0 && v1 < vertices.length && v2 > 0 && v2 < vertices.length) {
			adjMatrix[v1][v2] = adjMatrix[v2][v1] = 1;
		}
	}

	public void displayVertex(int vertexNo) {
		LOGGER.log(Level.INFO, () -> "" + vertices[vertexNo].getLabel());
	}

	public int getUnivistedAdjacentVertex(int vertexNo) {
		for (int j = 0; j < vertexCount; j++) {
			if (adjMatrix[vertexNo][j] == 1 && !vertices[j].isVisited())
				return j;
		}
		return -1;
	}

	private void clearVerticesVisitedFlags() {
		for (int i = 0; i < vertexCount; ++i) {
			vertices[i].setVisited(false);
		}
	}

	public void depthFirstSearch() {
		Stack<Integer> stack = new Stack<Integer>();
		vertices[0].setVisited(true);
		displayVertex(0);
		stack.push(0);
		while (!stack.isEmpty()) {
			int unvistiedVertexNo = getUnivistedAdjacentVertex(stack.peek());
			if (unvistiedVertexNo == -1) {
				stack.pop();
			}
			vertices[unvistiedVertexNo].setVisited(true);
			displayVertex(unvistiedVertexNo);
			stack.push(unvistiedVertexNo);
		}
		clearVerticesVisitedFlags();
		stack = null;
	}

	public void breadthFirstSearch() {
		Queue<Integer> queue = new LinkedList<Integer>();
		vertices[0].setVisited(true);
		displayVertex(0);
		queue.add(0);
		int unvisitedVertex = 0;
		while(!queue.isEmpty()) {
			int vertex1 = queue.poll();
			while((unvisitedVertex = getUnivistedAdjacentVertex(vertex1))!= -1) {
				vertices[unvisitedVertex].setVisited(true);
				displayVertex(unvisitedVertex);
				queue.add(unvisitedVertex);
			}
		}
		clearVerticesVisitedFlags();
	}
	
	public void unWeightedShortestPath(int source) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] distance = new int[vertexCount];
		int v, w;
		for(int i = 0; i < vertexCount; ++i) {
			distance[i] = -1;
		}
		
		distance[source] = 0;
		
		while(!queue.isEmpty()) {
			v = queue.poll();
			while((w = getUnivistedAdjacentVertex(v))!=-1) {
				if(distance[w] == -1) {
				}
			}
		}
		
		
	}
 	
}
