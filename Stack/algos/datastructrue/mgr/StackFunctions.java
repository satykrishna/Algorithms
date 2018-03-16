package algos.datastructrue.mgr;

import java.util.Stack;

import algos.datastructrue.pojo.ArrayStack;

public interface StackFunctions {

	public abstract ArrayStack createStack();
	
	public abstract boolean isStackEmpty(ArrayStack stack);
	
	public abstract boolean isStackFull(ArrayStack stack);
	
	public abstract ArrayStack push(ArrayStack stack);
	
	public boolean isExpressionValid(String str) throws Exception;
	
	public boolean isStringPalindrome(String input);
	
	public void reverse(Stack stack);
}
