package algos.datastructrue.pojo;

import java.util.EmptyStackException;


public class ArraywithTwoStacks {

	private int data[];

	private int size;

	private int topOne;

	private int topTwo;

	public ArraywithTwoStacks(int size) {
		if (size < 2) {
			throw new IllegalStateException("size should be greater than two");
		}
		data = new int[size];
		topOne = 0;
		topTwo = data.length;
	}

	public void push(int stackIndex, int ele) {
		if (topTwo == topOne + 1) {
			throw new StackOverflowError("Stack is full");
		}

		if (stackIndex == 1) {
			data[topOne++] = ele;
		}

		else if (stackIndex == 2) {
			data[--topTwo] = ele;
		}
	}
	
	public int pop(int stackIndex) {
		if(stackIndex == 1) {
			if(topOne == -1) {
				throw new EmptyStackException();
			}
			int topEle = data[stackIndex--];
			return topEle;
		}
		else if(stackIndex == 2) {
			if(topTwo == data.length) {
				throw new EmptyStackException(); 
			}
			int topEle = data[stackIndex++];
			return topEle;
		}
		else {
			return Integer.MIN_VALUE;
		}
	}
	
	public int top(int stackIndex) {
		if(stackIndex == 1) {
			if(topOne == -1) 
				throw new EmptyStackException();
			return data[topOne];
		}
		else if(stackIndex == 2) {
			if(topTwo == size) {
				throw new EmptyStackException();
			}
			return data[topTwo];
		}
		return Integer.MIN_VALUE;
	}
	
	public boolean isStackEmpty(int stackIndex) {
		if(stackIndex == 1) {
			return topOne == -1;
		}
		else if(stackIndex == 2) {
			return topTwo == size;
		}
		return true;
	}

}
