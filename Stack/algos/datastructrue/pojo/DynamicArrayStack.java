package algos.datastructrue.pojo;

/*
 * This class creates Dynamic Array stack
 *   
 *   Following are the complexities
 *   
 *    	Space Complexity : O(n)
 *    	Time complexity :
 *        1. Double the stack size : O(n) amortized complexity
 *        2. push - O(1)
 *        3. POP  - O(1)
 *        4. isEmpty - O(1)
 *        5. isFull - O(1)
 *        6. deleteStack O(1)
 *   
 */

public class DynamicArrayStack {
	
	private int top;
	private  int arr[];
	private int capacity;
	
	public DynamicArrayStack(int size) {
		top = -1;
		capacity = 0;
		arr = new int[size];
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public boolean isFull() {
		return top == (capacity - 1);
	}
	
	public void push(int data ){
		if(isFull()) {
			doubleStackSize();
		}
		arr[++top] = data;
	}

	private void doubleStackSize() {
		int[] tempArray = arr;
		arr = new int[capacity*2];
		System.arraycopy(tempArray, 0, arr, 0, capacity);
		capacity*=2;
	}
	
	public int pop() {
		if(isEmpty()) {
			throw new StackOverflowError("Stack is empty, cannot pop element");
		}
		return arr[top--];
	}
	
	public void delete() {
		arr=null;
		top = capacity = -1;
	}
	
	public int size() {
		return top;
	}
	

}
