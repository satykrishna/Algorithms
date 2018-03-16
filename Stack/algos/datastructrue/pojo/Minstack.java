package algos.datastructrue.pojo;

import java.util.Stack;

public class Minstack {

	private Stack<Integer> minStack = new Stack<Integer>();

	private Stack<Integer> elementStack = new Stack<Integer>();

	public void push(int data) {
		elementStack.push(data);

		if (minStack.isEmpty() || minStack.peek() > data) {
			minStack.push(data);
		}
	}

	public int pop() {
		if (elementStack.isEmpty()) {
			return -1;
		}
		int data = elementStack.pop();
		if (data == minStack.peek()) {
			minStack.pop();
		}
		return data;
	}
	
	public int min() {
		return minStack.peek();
	}
	
	public int top() {
		return elementStack.peek();
	}
	
	public boolean isEmpty() {
		return elementStack.isEmpty();
	}

}
