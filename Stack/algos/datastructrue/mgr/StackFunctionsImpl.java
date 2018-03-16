package algos.datastructrue.mgr;

import java.util.Stack;

import org.apache.log4j.Logger;

import algos.datastructrue.pojo.ArrayStack;

public class StackFunctionsImpl implements StackFunctions {
	
	private static Logger logger = Logger.getLogger(StackFunctionsImpl.class);

	@Override
	public ArrayStack createStack() {
		ArrayStack  arrayStack = new ArrayStack();
		arrayStack.setCapacity(1);
		arrayStack.setTop(-1);
		return arrayStack;
	}

	@Override
	public boolean isStackEmpty(ArrayStack stack) {
		return stack.getTop()==-1;
	}

	@Override
	public boolean isStackFull(ArrayStack stack) {
		return stack.getCapacity() == stack.getTop()+1;
	}

	@Override
	public ArrayStack push(ArrayStack stack) {
		return null;
	}

	@Override
	public boolean isExpressionValid(String str) throws Exception {
		if(str == null || str.length() == 0) {
			logger.error("Invalid String passed");
			return false;
		}
		
		Stack<Character> stack = new Stack<Character>();
		String brackets="(){}[]";
		
		for(int i = 0; i < str.length(); ++i) {
		    if(brackets.indexOf(str.charAt(i))==-1) {
		    	continue;
		    }
		    
		    char ch = str.charAt(i);
		    
		    if(ch == '(' || ch == '{' || ch == '[') {
		    	stack.push(ch);
		    }
		    
		    if(stack.isEmpty()) {
		    	throw new Exception("No matching opening bracket for given symbol");
		    }
		    
		    else if(ch == ')') {
		    	ch = stack.peek();
		    	if(ch != '(') {
		    		throw new Exception("Expression is not correct. Unmatching symbol found for " + ch );
		    	}
		    	stack.pop();
		    }
		    else if(ch == '}') {
		    	ch = stack.peek();
		    	if(ch != '{') {
		    		throw new Exception("Expression is not correct. Unmatching symbol found for " + ch);
		    	}
		    	stack.pop();
		    }
		    else if(ch == ']') {
		    	ch = stack.peek();
		    	if(ch != '[') {
		    		throw new Exception("Expression is not correct. Unmatching symbol found for " + ch);
		    	}
		    	stack.pop();
		    }
		}
		
		return true;
	}

	@Override
	public boolean isStringPalindrome(String input) {
		int i = 0, j = input.length();
		while(i < j && input.charAt(i) == input.charAt(j)) {
			i++;
			j--;
		}
		if( i < j) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void reverse(Stack stack) {
		if(stack.isEmpty()) {
			return;
		}
		
		Object topElement = stack.pop();
		
		reverse(stack);
		
		insertElementAtBottom(stack, topElement);
	}

	private void insertElementAtBottom(Stack stack, Object topElement) {
		if(stack.isEmpty()) {
			stack.push(topElement);
			return;
		}
		
		Object temp = stack.pop();
		insertElementAtBottom(stack, topElement);
		stack.push(temp);
	}
	

	

}
  


