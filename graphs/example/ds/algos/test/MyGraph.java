package example.ds.algos.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MyGraph {

	private Vertex[] vertices;

	private int[][] adjMatrix;

	private int vertexCount;

	private int[][] weightMatrix;

	private int[][] reverseMatrix;

	private int[][] reverseWeightMatrix;

	public MyGraph(int maxVertexCount) {
		this.vertices = new Vertex[maxVertexCount];
		adjMatrix = new int[maxVertexCount][maxVertexCount];
		vertexCount = 0;
		weightMatrix = new int[maxVertexCount][maxVertexCount];
		reverseMatrix = new int[maxVertexCount][maxVertexCount];
		reverseWeightMatrix = new int[maxVertexCount][maxVertexCount];
	}

	public void addVertex(char label) {
		vertices[vertexCount] = new Vertex(label, vertexCount);
		vertexCount++;
	}

	public void addVertex(Vertex vertex) {
		vertices[vertexCount++] = vertex;
	}

	public void addVertices(Vertex[] vertices) {
		this.vertices = vertices;
		vertexCount = vertices.length;
	}

	public void addEdge(Vertex vertex1, Vertex vertex2) {
		int label1 = vertex1.getIndex();
		int label2 = vertex2.getIndex();

		if (label1 <= vertices.length && label2 <= vertices.length) {
			adjMatrix[label1][label2] = 1;
			// undirected edge
			// adjMatrix[label2][label1] = 1;
		}
	}

	public void addEdgeWithWeight(Vertex vertex1, Vertex vertex2, int weight) {
		int label1 = vertex1.getIndex();
		int label2 = vertex2.getIndex();
		if (label1 <= vertices.length && label2 <= vertices.length) {
			addEdge(vertex1, vertex2);
			weightMatrix[label1][label2] = weight;
		}
	}

	public Vertex findAdjacentUnvisitedVertex(Vertex vertex) {
		int label = vertex.getIndex();

		for (int j = 0; j < vertexCount; j++) {
			if (adjMatrix[label][j] != 0 && !vertices[j].isVisited())
				return vertices[j];
		}

		return null;
	}

	public void print(Vertex vertex) {
		System.out.println(vertex);
	}

	public void clearVisitedFlags() {
		for (int i = 0; i < vertexCount; ++i)
			vertices[i].setVisited(false);
	}

	public void dfs() {
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.push(vertices[0]);
		vertices[0].setVisited(true);
		print(vertices[0]);
		while (!stack.isEmpty()) {
			Vertex unvisited = findAdjacentUnvisitedVertex(stack.peek());
			if (unvisited == null) {
				stack.pop();
			} else {
				unvisited.setVisited(true);
				stack.push(unvisited);
				print(unvisited);
			}
		}
		clearVisitedFlags();
		stack = null;
	}

	public void bfs() {
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(vertices[0]);
		vertices[0].setVisited(true);
		print(vertices[0]);
		Vertex temp = null;
		while (!queue.isEmpty()) {
			Vertex vertex = queue.poll();
			while ((temp = findAdjacentUnvisitedVertex(vertex)) != null) {
				temp.setVisited(true);
				print(temp);
				queue.add(temp);
			}
		}
		clearVisitedFlags();
		queue = null;
	}

	public void shortestPathinUnWeightedGraph(Vertex source) {

		int[] distance = new int[vertexCount];

		for (Vertex v : vertices) {
			distance[v.getIndex()] = Integer.MIN_VALUE;
		}

		distance[source.getIndex()] = 0;

		char[] previousVertex = new char[vertices.length];

		previousVertex[source.getIndex()] = '&';

		Queue<Vertex> queue = new LinkedList<Vertex>();

		queue.add(source);

		Vertex temp = null;

		while (!queue.isEmpty()) {
			Vertex v = (queue.poll());
			while ((temp = findAdjacentUnvisitedVertex(v)) != null) {
				if (distance[temp.getIndex()] == Integer.MIN_VALUE) {
					distance[temp.getIndex()] = distance[v.getIndex()] + 1;
				}
				previousVertex[temp.getIndex()] = v.getLabel();
				temp.setVisited(true);
				queue.add(temp);
			}
		}

		System.out.println("Distance To other nodes from " + source.getLabel() + " is \n");

		for (int i = 0; i < distance.length; i++) {
			System.out.println(vertices[i].getLabel() + " " + distance[i]);
		}

		queue = null;

		clearVisitedFlags();
	}

	public void dijkstraAlgos(Vertex source) {

		int[] distance = new int[vertexCount];

		for (int i = 0; i < distance.length; i++) {
			distance[i] = Integer.MIN_VALUE;
		}

		distance[source.getIndex()] = 0;

		char[] path = new char[vertexCount];

		path[source.getIndex()] = '&';

		Comparator<Vertex> comparator = new Comparator<Vertex>() {

			public int compare(Vertex v1, Vertex v2) {
				if (distance[v1.getIndex()] < distance[v2.getIndex()])
					return -1;
				else if (distance[v1.getIndex()] == distance[v2.getIndex()])
					return 0;
				else
					return 1;
			}
		};

		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(comparator);

		queue.add(source);

		Vertex temp = null;

		while (!queue.isEmpty()) {

			Vertex v = queue.poll();

			clearFlagsOtherThanVisited(v);

			while ((temp = findDjAdjacentVertexSearch(v)) != null) {
				int newDistance = distance[v.getIndex()] + weightMatrix[v.getIndex()][temp.getIndex()];
				temp.setVisited(true);
				if (distance[temp.getIndex()] == Integer.MIN_VALUE) {
					distance[temp.getIndex()] = newDistance;
					queue.add(temp);
					path[temp.getIndex()] = v.getLabel();
				}
				if (distance[temp.getIndex()] > newDistance) {
					distance[temp.getIndex()] = newDistance;
					if (queue.contains(temp)) {
						queue.remove(temp);
						queue.add(temp);
					} else {
						queue.add(temp);
					}
					path[temp.getIndex()] = v.getLabel();
				}
			}
		}

		queue = null;
		clearVisitedFlags();
		printUtil(distance, path, source);
	}

	private void printUtil(int[] distance, char[] path, Vertex source) {
		System.out.println("Distance To other nodes from " + source.getLabel() + " is \n");

		for (int i = 0; i < distance.length; i++) {
			System.out.println(vertices[i].getLabel() + " " + distance[i]);
		}

		System.out.println("\n");

		for (int i = 0; i < path.length; i++) {
			System.out.println("previous node to " + vertices[i].getLabel() + ":  " + path[i]);
		}

		System.out.println("___________________\n");
	}

	private void clearFlagsOtherThanVisited(Vertex v) {
		clearVisitedFlags();
		v.setVisited(true);
	}

	public Vertex findDjAdjacentVertexSearch(Vertex vertex) {

		int label = vertex.getIndex();

		for (int j = 0; j < vertexCount; j++) {
			if (weightMatrix[label][j] != 0 && !vertices[j].isVisited()) {
				return vertices[j];
			}
		}
		return null;
	}

	public void bellmanFordAlgorithm(Vertex source) {

		Queue<Vertex> queue = new LinkedList<Vertex>();

		queue.add(source);

		Vertex temp = null;

		int[] distance = new int[vertexCount];

		char[] parentVertex = new char[vertexCount];

		for (Vertex v : vertices) {
			distance[v.getIndex()] = Integer.MAX_VALUE;
		}

		distance[source.getIndex()] = 0;

		parentVertex[source.getIndex()] = '&';

		while (!queue.isEmpty()) {

			Vertex v = queue.poll();

			clearFlagsOtherThanVisited(v);

			while ((temp = findDjAdjacentVertexSearch(v)) != null) {
				temp.setVisited(true);
				int newDistance = distance[v.getIndex()] + weightMatrix[v.getIndex()][temp.getIndex()];
				if (newDistance < distance[temp.getIndex()]) {
					distance[temp.getIndex()] = newDistance;
					queue.add(temp);
					parentVertex[temp.getIndex()] = v.getLabel();
				}

			}
		}

		queue = null;
		clearVisitedFlags();
		printUtil(distance, parentVertex, source);
	}

	public void primsAlgorithm(Vertex source) {

		int[] distance = new int[vertexCount];

		char[] path = new char[vertexCount];

		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(new Comparator<Vertex>() {

			@Override
			public int compare(Vertex v1, Vertex v2) {

				int l1 = distance[v1.getIndex()];

				int l2 = distance[v2.getIndex()];

				if (l1 > l2)
					return 1;

				if (l1 == l2)
					return 0;

				else
					return -1;
			}
		});

		queue.add(source);

		source.setVisited(true);

		Vertex next = null;

		while (!queue.isEmpty()) {

			Vertex current = queue.poll();

			clearFlagsOtherThanVisited(current);

			while ((next = findDjAdjacentVertexSearch(current)) != null) {

				next.setVisited(true);

				int newDistance = distance[current.getIndex()] + weightMatrix[current.getIndex()][next.getIndex()];

				if (distance[next.getIndex()] == -1) {
					distance[next.getIndex()] = weightMatrix[current.getIndex()][next.getIndex()];
					path[next.getIndex()] = current.getLabel();
					queue.add(next);
				}

				if (distance[next.getIndex()] > newDistance) {
					distance[next.getIndex()] = weightMatrix[current.getIndex()][next.getIndex()];
					if (queue.contains(next))
						queue.remove(next);
					queue.add(next);
					path[next.getIndex()] = current.getLabel();
				}
			}
		}

		clearVisitedFlags();
		queue = null;

		printUtil(distance, path, source);

	}

	public void simplePath(Vertex source, Vertex destination) {
		boolean exists = isSimplePath(source, destination);
		clearVisitedFlags();
		if (exists)
			System.out.println("\nSimple path exists between " + source + " ... " + destination);
		else
			System.out.println("No simple path between " + source + " .... " + destination);
		System.out.println("==========================================\n");
	}

	private boolean isSimplePath(Vertex source, Vertex destination) {

		boolean exists = false;

		source.setVisited(true);

		if (source == destination)
			exists = true;

		else {
			for (int i = 0; i < vertices.length; i++) {
				if (adjMatrix[source.getIndex()][i] != 0 && !vertices[i].isVisited()) {
					exists = isSimplePath(vertices[i], destination);
					if (exists)
						break;
				}

			}
		}

		return exists;
	}

	public int countSimplePaths(Vertex source, Vertex destination, int pathLength) {
		source.setVisited(true);

		if ((pathLength == 0) && source == destination) {
			source.setVisited(false);
			return 1;
		}

		else if (pathLength >= 1 && source == destination) {
			source.setVisited(false);
			return 0;
		}

		if (pathLength <= 0)
			return 0;

		int count = 0;

		for (int i = 0; i < vertices.length; ++i) {
			if (adjMatrix[source.getIndex()][i] == 1 && !vertices[i].isVisited()) {
				count += countSimplePaths(vertices[i], destination, pathLength - 1);
				vertices[i].setVisited(false);
			}
		}

		return count;
	}

	public void countSimplePaths(Vertex source, Vertex destination) {
		System.out.println("\nNo of paths between " + source + "..." + destination + "="
				+ countSimplePathsCalc(source, destination));
		System.out.println("==========================================\n");
	}

	private int countSimplePathsCalc(Vertex source, Vertex destination) {

		source.setVisited(true);

		if (source == destination) {
			source.setVisited(false);
			return 1;
		}

		int count = 0;

		for (int i = 0; i < vertices.length; ++i) {
			// CONDITION !vertices[i].isVisited() is to take account of cycles
			// in the graph
			if (adjMatrix[source.getIndex()][i] == 1 && !vertices[i].isVisited()) {
				count += countSimplePathsCalc(vertices[i], destination);
				vertices[i].setVisited(false);
			}

		}

		return count;

	}

	public boolean isConnected(Vertex source, Vertex destination) {
		boolean exists = false;
		source.setVisited(true);
		for (int i = 0; i < vertices.length; ++i) {
			if (adjMatrix[source.getIndex()][i] != 0 && !vertices[i].isVisited()) {
				exists = checkIfPathExists(vertices[i], destination);
				if (exists)
					break;
			}
		}
		return exists;
	}

	private boolean checkIfPathExists(Vertex source, Vertex destination) {

		Stack<Vertex> stack = new Stack<Vertex>();

		stack.push(source);

		source.setVisited(true);

		if (source == destination)
			return true;

		boolean exists = false;

		while (!stack.isEmpty()) {

			Vertex temp = findAdjacentUnvisitedVertex(stack.peek());

			if (temp == null) {
				stack.pop();
			}

			else {

				temp.setVisited(true);
				stack.push(temp);
				if (temp == destination) {
					exists = true;
					break;
				}
			}
		}

		return exists;
	}

	public void dfs2() {

		Stack<Vertex> stack = new Stack<Vertex>();

		stack.push(vertices[0]);

		vertices[0].setVisited(true);

		print(vertices[0]);

		while (!stack.isEmpty()) {
			Vertex unvisitedVertex = findAdjacentUnvisitedVertex(stack.peek());
			if (unvisitedVertex != null) {
				stack.push(unvisitedVertex);
				print(unvisitedVertex);
				unvisitedVertex.setVisited(true);
			}

			else {
				stack.pop();
			}
		}

		clearVisitedFlags();

		stack = null;
	}

	public void bfs2() {
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(vertices[0]);
		vertices[0].setVisited(true);
		print(vertices[0]);
		Vertex temp = null;

		while (!queue.isEmpty()) {
			Vertex v = queue.poll();
			while ((temp = findAdjacentUnvisitedVertex(v)) != null) {
				temp.setVisited(true);
				print(temp);
				queue.add(temp);
			}
		}

		clearVisitedFlags();
		queue = null;
	}

	public void djAlgo(Vertex source) {

		int[] distance = new int[vertexCount];

		char[] path = new char[vertexCount];

		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(new Comparator<Vertex>() {

			@Override
			public int compare(Vertex oldVertex, Vertex newVertex) {
				if (distance[oldVertex.getIndex()] < distance[newVertex.getIndex()])
					return -1;
				if (distance[oldVertex.getIndex()] == distance[newVertex.getIndex()])
					return 0;
				else
					return 1;
			}
		});

		source.setVisited(true);

		for (int i = 0; i < distance.length; i++) {
			distance[i] = Integer.MIN_VALUE;
		}

		distance[source.getIndex()] = 0;

		path[source.getIndex()] = '&';

		Vertex temp = null;

		queue.add(source);

		while (!queue.isEmpty()) {

			Vertex v = queue.poll();

			clearFlagsOtherThanVisited(v);

			while ((temp = findDjAdjacentVertexSearch(v)) != null) {

				temp.setVisited(true);

				int newDistance = distance[v.getIndex()] + weightMatrix[v.getIndex()][temp.getIndex()];

				if (distance[temp.getIndex()] == Integer.MIN_VALUE) {
					distance[temp.getIndex()] = newDistance;
				}

				if (distance[temp.getIndex()] > newDistance) {
					distance[temp.getIndex()] = newDistance;
					if (queue.contains(temp)) {
						queue.remove(temp);
					}
				}

				queue.add(temp);
				path[temp.getIndex()] = v.getLabel();
			}

		}
		clearVisitedFlags();
		queue = null;

	}

	public int countNoofPaths2(Vertex source, Vertex destination, int pathLength) {

		if (pathLength == 0 && source == destination) {
			source.setVisited(false);
			return 1;
		}

		if (pathLength >= 1 && source == destination) {
			source.setVisited(false);
			return 0;
		}

		if (pathLength <= 0)
			return 0;

		int count = 0;

		for (int i = 0; i < vertices.length; ++i) {

			if (adjMatrix[source.getIndex()][i] != 0 && !vertices[i].isVisited()) {
				count += countNoofPaths2(vertices[i], destination, pathLength - 1);
				vertices[i].setVisited(false);
			}

		}

		return count;
	}

	public void findStrongComponents() {

		Stack<Vertex> dfsStack = new Stack<Vertex>();
		
		Vertex vertex = null;
		
		while((vertex = unvisitedVertex())!= null) {
			getVerticesOrderIntheStackByFinishTimes(dfsStack, vertex);	
		}
		
		clearVisitedFlags();
		
		reverseGraph();
		
		ArrayList<ArrayList<Vertex>> sccs = new ArrayList<ArrayList<Vertex>>();
		
		while (!dfsStack.isEmpty()) {
			performDFSforEachNode(dfsStack.pop(), sccs);
		}

		System.out.println(sccs);
		
		System.out.println(sccs.size());
		
		clearVisitedFlags();

	}

	private void performDFSforEachNode(Vertex vertex, ArrayList<ArrayList<Vertex>> sccs) {
		
		if(vertex.isVisited())
			return;

		Stack<Vertex> stack = new Stack<Vertex>();

		vertex.setVisited(true);

		stack.push(vertex);

	    ArrayList<Vertex> list = new ArrayList<Vertex>();

		while (!stack.isEmpty()) {

			Vertex unvisited = findAdjacentVertexinReverseGraph(stack.peek());

			if (unvisited == null) {
				stack.pop();
			}

			else {
				list.add(unvisited);
				stack.push(unvisited);
				unvisited.setVisited(true);
			}
		}

		stack = null;
		
		if(list != null && !list.isEmpty() ) {
			sccs.add(list);		
		}
		
	}

	private Vertex findAdjacentVertexinReverseGraph(Vertex vertex) {

		int vertexLabel = vertex.getIndex();

		for (int i = 0; i < vertices.length; i++) {
			if (reverseMatrix[vertexLabel][i] != 0 && !vertices[i].isVisited()) {
				return vertices[i];
			}
		}

		return null;
	}
	

	private Stack<Vertex> getVerticesOrderIntheStackByFinishTimes(Stack<Vertex> stack, Vertex current) {
	
		current.setVisited(true);
		
		for(int i = 0; i < vertices.length; ++i) {
			if(adjMatrix[current.getIndex()][i]!=0 && !vertices[i].isVisited()) {
				getVerticesOrderIntheStackByFinishTimes(stack, vertices[i]);
			}
		}
		
	
		stack.push(current);

		return stack;
	}


	private void reverseGraph() {
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if (adjMatrix[i][j] != 0) {
					reverseMatrix[j][i] = adjMatrix[i][j];
				}
			}
		}

		for (int i = 0; i < weightMatrix.length; i++) {
			for (int j = 0; j < weightMatrix.length; j++) {
				if (weightMatrix[i][j] != 0) {
					reverseWeightMatrix[j][i] = weightMatrix[i][j];
				}
			}
		}
	}
	
	private Vertex unvisitedVertex() {
		
		for(Vertex vertex : vertices) {
			if(!vertex.isVisited())
				return vertex;
		}
		
		return null;
	}
	
	public void sccsUsingBFS() {
		
		for(int i = 0; i < vertices.length; ++i) {
			if(!vertices[i].isVisited()) 
				sccsUsingBFS(vertices[i]);
		}
		
		clearVisitedFlags();
	}

	private void sccsUsingBFS(Vertex current) {
		Queue<Vertex> queue = new LinkedList<Vertex>();
		current.setVisited(true);
		queue.add(current);
		Vertex temp = null;
		while(!queue.isEmpty()) {
			Vertex v = queue.poll();
			while((temp=findAdjacentUnvisitedVertex(v))!=null) {
				temp.setVisited(true);
				print(temp);
				queue.add(temp);
			}
		}
		
		System.out.println("____________\n");
	}

}
