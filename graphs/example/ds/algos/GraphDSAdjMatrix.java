package example.ds.algos;

public class GraphDSAdjMatrix {

	private boolean adjMatrix[][];
	private int vertexCount;

	public GraphDSAdjMatrix(int vertexCount) {
		super();
		this.vertexCount = vertexCount;
	}

	public boolean[][] getAdjMatrix() {
		return adjMatrix;
	}

	public void setAdjMatrix(boolean[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
	
	
	public void addEdge(int vertex1, int vertex2) {
		if( vertex1 >=0 && vertex1 < vertexCount && vertex2 > 0 && vertex2 < vertexCount) {
			adjMatrix[vertex1][vertex2] = adjMatrix[vertex2][vertex1] = true;
		}
	}
	
	public void removeEdge(int vertex1, int vertex2) {
		if( vertex1 >=0 && vertex1 < vertexCount && vertex2 > 0 && vertex2 < vertexCount) {
			adjMatrix[vertex1][vertex2] = adjMatrix[vertex2][vertex1] = false;
		}
	}
	
	public boolean isEdge(int vertex1, int vertex2) {
		if( vertex1 >=0 && vertex1 < vertexCount && vertex2 > 0 && vertex2 < vertexCount) {
			return adjMatrix[vertex1][vertex2];
		}
		return false;
	}

}
