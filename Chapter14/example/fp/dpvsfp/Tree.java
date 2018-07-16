package example.fp.dpvsfp;

import java.util.Objects;

public class Tree {

	private String key;
	
	private int value;
	
	private Tree left, right;

	public Tree(String key, int value, Tree left, Tree right) {
		super();
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	
	public int lookup(String key, int defaultVal, Tree t) {
		
		if(t == null) return defaultVal;
		
		if(key.equals(this.key)) return t.value;
		
		return lookup(key, defaultVal, key.compareTo(this.key) < 0 ? t.left:t.right);
	}
	
	public void update(String key, int newVal, Tree t) {
		
		if(t == null ) {
			//add a new node
		}
		
		else if(key.equals(this.key)) {
			t.value = newVal;
		}
		
		else if( key.compareTo(this.key) < 0)
			update(key, newVal, t.left);
		
		else
			update(key, newVal, t.right);
	}
	
	public Tree updateAndAdd(String key, int newVal, Tree t) {
		
		if(t == null) {
			t = new Tree(key, newVal, null, null);
			return t;
		}
		
		if(Objects.equals(key, t.key)) {
			t.value = newVal;
		}
		
		else if(key.compareTo(this.key) <  0) {
			t.left = updateAndAdd(key, newVal, t.left);
		}
		
		else {
			t.right = updateAndAdd(key, newVal, t.right);
		}
		
		return t;
	}
	
	public Tree fnUpdateAndAdd(String key, int newVal, Tree t) {
		
		if(t == null) {
			return new Tree(key, newVal,null, null);
		}
		
		if(key.equals(this.key)) {
			return new Tree(key, t.value, t.left, t.right);
		}
		
		else if(key.compareTo(this.key) < 0) {
			Tree newLeft = fnUpdateAndAdd(key, newVal, t.left);
			return new Tree(key, t.value, newLeft, t.right);
		}
		
		else {
			Tree newRight = fnUpdateAndAdd(key, newVal, t.right);
			return new Tree(key, t.value, t.left, newRight);
		}
	}
}
