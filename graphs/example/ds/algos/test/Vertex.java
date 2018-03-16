package example.ds.algos.test;

public class Vertex {

	private boolean isVisited;
	
	private char label;
	
	private int index;

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public char getLabel() {
		return label;
	}

	public void setLabel(char label) {
		this.label = label;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Vertex(char label) {
		super();
		this.label = label;
		this.isVisited = false;
	}
	
	public Vertex(char label, int index) {
		this(label);
		this.index = index;
	}

	@Override
	public String toString() {
		return "Vertex [isVisited=" + isVisited + ", label=" + label + ", index=" + index + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + label;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vertex))
			return false;
		Vertex other = (Vertex) obj;
		if (label != other.label)
			return false;
		return true;
	}

	
	
	
	
	
	
	
}
