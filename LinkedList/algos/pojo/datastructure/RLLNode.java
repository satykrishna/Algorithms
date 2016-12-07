package algos.pojo.datastructure;

public class RLLNode extends SLLNode {

	private static final long serialVersionUID = 1L;

	private SLLNode random;
	
	
	public RLLNode(int data, SLLNode next, SLLNode random) {
		super(data, next);
		this.random = random;
	}

	public SLLNode getRandom() {
		return random;
	}

	public void setRandom(SLLNode random) {
		this.random = random;
	}

	public RLLNode(int data, SLLNode next) {
		super(data, next);
	}

	public RLLNode() {
	}

}
