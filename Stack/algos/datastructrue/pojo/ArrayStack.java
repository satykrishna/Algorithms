package algos.datastructrue.pojo;

import java.io.Serializable;

/*
 * Space complexity (for n operations) : O(n)
 * Time complexity 
 *   1. Push = O(1)
 *   2. Pop  = O(1)
 *   3. size = O(1)
 *   4. isEmpty = O(1)
 *   5. isFull = O(1)
 *   6. delete = O(1)
 */



public class ArrayStack implements Serializable {

	private static final long serialVersionUID = 1L;

	private int top;

	private int capacity;

	private int[] data;

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public ArrayStack(int capacity) {
		super();
		this.capacity = capacity;
		top = -1;
		data = new int[capacity];
	}

	public ArrayStack() {
		super();
	}
	
	public boolean isEmpty() {
		return this.top==-1;
	}
	
	public boolean isFull() {
		return capacity==(top+1);
	}
	
	public void push(int newData) {
		if(isFull()) {
			throw new StackOverflowError("Stack is Full.. Unable to insert " +newData);
		}
		data[++top] = newData;
	}
	
	public int pop() {
		if(isEmpty()) {
			throw new StackOverflowError("Stack is Empty, nothing to pop from stack");
		}
		return data[top--];
	}
	
	public void delete() {
		data = null;
		capacity = 0;
		top =-1;
	}
	
	public int size() {
		return top;
	}

}
