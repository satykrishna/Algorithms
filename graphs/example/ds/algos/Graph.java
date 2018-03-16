package example.ds.algos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

	private Vertex[] vertexList;
	
	private int adjMatrix[][];
	
	private int vertexCount;
	
	private Stack<Integer> stack;
	
	private Queue<Integer> queue;
	
	public Graph(int maxVertices) {
		
		vertexList = new Vertex[maxVertices];
		
		adjMatrix = new int[maxVertices][maxVertices];
		
		vertexCount = 0;
		
		for(int y = 0; y < maxVertices; ++y) {
			for(int x = 0; x < maxVertices; ++x) {
				adjMatrix[x][y] = 0;
			}
		}
		
		stack = new Stack<Integer>();
		
		queue = new LinkedList<Integer>();
	}
	
	public void addVertex(char label) {
		vertexList[vertexCount++] = new Vertex(label);
	}
	
	public void addEdge(int start, int end ) {
		adjMatrix[start][end] = adjMatrix[end][start] = 1;
	}
	
	public void displayVertex(int vertex) {
		System.out.print(vertexList[vertex].getLabel()+"\t");
	}
	
	public int getAdjUnvisitedVertex(int vertex) {
		for(int j = 0; j < vertexCount; ++j) {
			if(adjMatrix[vertex][j] == 1 && !vertexList[j].isVisited())
				return j;
		}
		return -1;
	}
	
	public void depthFirstSearch() {
		vertexList[0].setVisited(true);
		displayVertex(0);
		stack.push(0);
		
		while(!stack.isEmpty()) {
			int univistedVertex = getAdjUnvisitedVertex(stack.peek());
			if(univistedVertex == -1) {
				stack.pop();
			}
			
			else {
				vertexList[univistedVertex].setVisited(true);
				displayVertex(univistedVertex);
				stack.push(univistedVertex);
			}
		}
		
		clearFlags();
		
	}
	
	public void clearFlags() {
		for(int j = 0; j < vertexCount; ++j) {
			vertexList[j].setVisited(false);
		}
	}
	
	public static void main(String[] args) {
		
		Graph g = new Graph(5);
		
		g.addVertex('A');
		g.addVertex('C');
		g.addVertex('B');
		g.addVertex('D');
		g.addVertex('E');
		
		
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		
     	g.depthFirstSearch();
		
		System.out.println("\n--------");
		
		g.breadthFirstSearch();
	}
	
	public void depthFirstSearchAlgorithm() {
		
		vertexList[0].setVisited(true);
		stack.push(0);
		displayVertex(0);
		
		while(!stack.isEmpty()) {
			int unvisitedVertex = getAdjUnvisitedVertex(stack.peek());
			
			if(unvisitedVertex == -1) {
				stack.pop();
			}
			
			vertexList[unvisitedVertex].setVisited(true);
			displayVertex(unvisitedVertex);
			stack.push(unvisitedVertex);
		}
		
		clearFlags();
	}
	
	public void breadthFirstSearch() {
		vertexList[0].setVisited(true);
		displayVertex(0);
		queue.add(0);
		
		int vertex2 = 0;
		
		while(!queue.isEmpty()) {
			int vertex1 = queue.remove();
			
			while((vertex2 = getAdjUnvisitedVertex(vertex1))!=-1) {
				vertexList[vertex2].setVisited(true);
				displayVertex(vertex2);
				queue.add(vertex2);
			}
		}
		
		clearFlags();
	}
}
