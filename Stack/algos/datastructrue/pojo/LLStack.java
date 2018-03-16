package algos.datastructrue.pojo;

import algos.pojo.datastructure.SLLNode;

public class LLStack {
	
	private SLLNode head;
	
	public LLStack() {
		this.head = new SLLNode();
	}
	
	public void push(int data) {
		if(this.head == null) {
			this.head = new SLLNode(data, null);
		}
		else {
			SLLNode newNode = new SLLNode(data, head);
			head = newNode;
		}
	}
	
	public int top() {
		if(head == null) {
			throw new StackOverflowError("No element exists in the Stack");
		}
		else {
			return head.getData();
		}
	}
	
	public int pop() {
		if(head == null) {
			throw new StackOverflowError("No Element exists in the stack");
		}
		else {
			SLLNode nextNode = head.getNext();
			head.setNext(null);
			int data = head.getData();
			head = nextNode;
			return data;
		}
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void delete() {
		head = null;
	}
	
	/* Time complexity : 
	 * 
	 *   create - O(1)
	 *   push - O(1) average
	 *   pop - O(1) average
	 *   top - O(1)
	 *   isEmpty - O(1)
	 *   delete - O(n)
	 * 
	 */

}
