package algos.pojo.datastructure;

public class DLLNode extends SLLNode {
	
	private static final long serialVersionUID = 100L;
	
	private SLLNode previous;

	public SLLNode getPrevious() {
		return previous;
	}

	public void setPrevious(SLLNode previous) {
		this.previous = previous;
	}

	public DLLNode(int data, SLLNode next, SLLNode previous) {
		super(data, next);
		this.previous = previous;
	}

	public DLLNode() {
		super();
	}

	
	
}
