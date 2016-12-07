package algos.pojo.datastructure;

import java.io.Serializable;

public class SLLNode implements Serializable {

	private static final long serialVersionUID = 109L;

	public SLLNode(int data, SLLNode next) {
		super();
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public SLLNode getNext() {
		return next;
	}

	public void setNext(SLLNode next) {
		this.next = next;
	}

	protected int data;
	protected SLLNode next;

	public SLLNode() {
		super();
	}

}
