package example.ds.algos;

public class Vertex {

	private char label;

	private boolean visited;
	
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Vertex(char label) {
		this.label = label;
		this.visited = false;
	}

	public char getLabel() {
		return label;
	}

	public void setLabel(char label) {
		this.label = label;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return "Vertex [label=" + label + ", visited=" + visited + "]";
	}

	
}
