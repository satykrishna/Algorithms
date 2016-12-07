package algos.pojo.utils;

import algos.pojo.datastructure.CLLNode;
import algos.pojo.datastructure.DLLNode;
import algos.pojo.datastructure.RLLNode;
import algos.pojo.datastructure.SLLNode;
import algos.pojo.exceptions.LinkedListException;

public interface LinkedListFunctions {

	public abstract int length(SLLNode list);
	
	public abstract void traverse(SLLNode list);
	
	public abstract void insert(SLLNode list, int data, int position);
	
	public abstract SLLNode delete(SLLNode list, int position) throws LinkedListException;
	
	public abstract void insert(DLLNode list, int data, int position);
	
	public abstract DLLNode delete(DLLNode list, int position) throws LinkedListException;
	
	public abstract CLLNode insert(CLLNode list, int position, int data);
	
	public abstract CLLNode deleteAtBeginning(CLLNode list);
	
	public abstract CLLNode deleteAtEnd(CLLNode list);
	
	public abstract SLLNode findNthNodeFromEnd(SLLNode list, int n);
	
	public abstract boolean isListCyclic(SLLNode list);
	
	public abstract SLLNode findBeginingofLoop(SLLNode list);
	
	public abstract int findLengthofCycle(SLLNode list);
	
	public abstract SLLNode insertNodeinSortedList(SLLNode list, int data);
	
	public abstract SLLNode reverse(SLLNode list);
	
	public abstract SLLNode findMerge(SLLNode list1, SLLNode list2);
	
	public abstract SLLNode findMiddle(SLLNode list);
	
	public abstract void displayListFromEnd(SLLNode list);
	
	public abstract boolean isListEven(SLLNode list);
	
	public abstract SLLNode merge(SLLNode a, SLLNode b);
	
	public abstract SLLNode reverseListinKPairs(SLLNode list, int k);
	
	public abstract CLLNode[] splitCircularList(CLLNode list);
	
	public abstract int findWinner(int n, int m);
	
	public abstract RLLNode clone(RLLNode list);

}

