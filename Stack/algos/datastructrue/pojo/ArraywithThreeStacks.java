package algos.datastructrue.pojo;

import java.util.EmptyStackException;
import java.util.Stack;

public class ArraywithThreeStacks<T> extends Stack<T> {

	private static final long serialVersionUID = 1L;
	
	private T[] data;
	
	private int size;
	
	private int topOne;

	private int topTwo;
	
	private int baseThree;
	
	private int topThree;

	@SuppressWarnings("unchecked")
	public ArraywithThreeStacks(int size) {
		if(size < 3) {
			throw new IllegalStateException("size must be atleast 3");
		}
		data = (T[])new Object[size];
		this.size= size;
		topOne = -1;
		topTwo = size;
		baseThree = size/2;
		topThree = size/2;
	}
	
	public void push(int stackId, T item) {
		
		if(stackId == 1) {
			//Stack1 has reached capacity
			if(topOne+1 == baseThree) {
				if(isStack3RightShiftable()) {
					shiftStack3ToRight();
					data[topOne++]=item;
				}else {
					throw new StackOverflowError("Stack1 has reached capacity limit");
				}
			}
			//stack 1 is some capacity left
			else {
				data[topOne++] = item;
			}
		}
		
		else if(stackId == 2) {
			if(topTwo-1 == topThree) {
				if(isStack3LeftShiftable()) {
					shiftStack3ToLeft();
					data[--topTwo] = item;
				}else {
					throw new StackOverflowError("Stack2 is full");
				}
			}
			else {
				data[--topTwo] = item;
			}
		}
		
		else if(stackId == 3) {
			if(topTwo-1 == topThree) {
				if(isStack3LeftShiftable()) {
					shiftStack3ToLeft();
					data[topThree++] = item;
				}
				else {
					throw new StackOverflowError("Stack 3 is full");
				}
			}
			else {
				data[topThree++] = item;
			}
		}
	}
	
	public T pop(int stackId) {
		if(stackId == 1) {
			if(topOne == -1) {
				throw new StackOverflowError("First Stack is empty");
			}
			T toPop = data[topOne];
			data[topOne--] = null;
			return toPop;
		}
		else if(stackId == 2) {
			if(topTwo == size) {
				throw new StackOverflowError("Second stack is Empty");
			}
			
			T toPop = data[topTwo];
			data[topTwo++] = null;
			return toPop;
		}
		else if(stackId == 3) {
			if(topThree == this.size && data[topThree]==null) {
				throw new EmptyStackException();
			}
			T toPop = data[topThree];
			if(topThree > baseThree) {
				data[topThree--] = null;
			}
			if(topThree == baseThree) {
				data[topThree] = null;
			}
			return toPop;
		}
		else {
			return null;
		}
	}
	
	public T top(int stackId) {
		if(stackId == 1) {
			if(topOne == -1) {
				throw new EmptyStackException();
			}
			return data[topOne];
		}
		else if(stackId==2) {
			if(topTwo == this.size){
				throw new EmptyStackException();
			}
			return data[topTwo];
		}
		else if(stackId == 3) {
			if(baseThree==topThree && data[baseThree]== null) {
				throw new EmptyStackException();
			}
			return data[topThree];
		}
		else {
			return null;
		}
	}
	
	public boolean isEmpty(int stackId) {
		if(stackId == 1) {
			return topOne == -1;
		}
		else if(stackId == 2) {
			return topTwo == this.size;
		}
		else if(stackId == 3) {
			return baseThree == topThree && data[baseThree] == null;
		}
		else {
			return true;
		}
	}

	private void shiftStack3ToLeft() {
		for(int i = baseThree-1; i < topThree; i++) {
			data[i] = data[i+1];
		}
		data[topThree--] = null;
		baseThree--;
	}

	private boolean isStack3LeftShiftable() {
		return (topOne+1) < baseThree;
	}

	private void shiftStack3ToRight() {
		for(int i = baseThree+1; i < topThree+1; i++) {
			data[i] = data[i-1];
		}
		data[baseThree] = null;
		baseThree++;
	}

	private boolean isStack3RightShiftable() {
		return (topThree+1)<topTwo;
	}
	
	
	
}
