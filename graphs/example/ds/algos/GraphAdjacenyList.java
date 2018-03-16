package example.ds.algos;

import java.util.ArrayList;
import java.util.LinkedList;

@SuppressWarnings("unused")
public class GraphAdjacenyList {

	private ArrayList<Integer> vertices;
	
	@SuppressWarnings("rawtypes")
	private LinkedList[] edges;
	
	private int vertexCount;

	public GraphAdjacenyList(int vertexCount) {
		super();
		this.vertexCount = vertexCount;
		vertices = new ArrayList<Integer>();
		edges = new LinkedList[vertexCount];
		for(int i = 0 ; i < vertexCount; i++) {
			vertices.add(i);
			edges[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int source, int destination ) {
		int sourceIndex = vertices.get(source);
		int destinationIndex = vertices.get(destination);
		
		if(sourceIndex != -1 &&  destinationIndex != -1) {
			edges[sourceIndex].add(0, destinationIndex);
			edges[destinationIndex].add(0, sourceIndex);
		}
	}
	
	
}
