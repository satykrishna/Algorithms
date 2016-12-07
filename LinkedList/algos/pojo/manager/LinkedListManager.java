package algos.pojo.manager;

import java.util.Hashtable;
import java.util.logging.Logger;

import algos.pojo.datastructure.CLLNode;
import algos.pojo.datastructure.DLLNode;
import algos.pojo.datastructure.RLLNode;
import algos.pojo.datastructure.SLLNode;
import algos.pojo.exceptions.LinkedListException;
import algos.pojo.utils.LinkedListFunctions;

public class LinkedListManager implements LinkedListFunctions {

	private static Logger logger = Logger.getLogger(LinkedListManager.class.getName());

	
	/*
	 * (non-Javadoc)
	 * @see algos.pojo.utils.LinkedListFunctions#length(algos.pojo.datastructure.SLLNode)
	 * Find the length of the list O(n)
	 */
	@Override
	public int length(SLLNode list) {

		SLLNode endofList = null;

		if (list instanceof CLLNode) {
			endofList = list;
		}

		SLLNode current = list;
		int count = 0;
		for (; current != endofList; current = current.getNext()) {
			count++;
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * @see algos.pojo.utils.LinkedListFunctions#insert(algos.pojo.datastructure.SLLNode, int, int)
	 * insert : O(1)
	 */
	@Override
	public void insert(SLLNode list, int data, int position) {
		SLLNode newNode = new SLLNode(data, null);
		if (position == 1 || list == null) {
			newNode.setNext(list);
			list = newNode;
		} else {
			int index = 1;
			SLLNode current = list;
			SLLNode previous = current;
			// Traverse till position-1;
			while (index < position - 1) {
				previous = current;
				current = current.getNext();
				index++;
			}

			if (current.getNext() == null) {
				logger.info("reached end of list");
				previous.setNext(newNode);
			} else {
				newNode.setNext(current);
				previous.setNext(newNode);
			}
		}
		logger.info("Insert successful, please traverse");
	}

	//delete O(1)
	@Override
	public SLLNode delete(SLLNode list, int position) throws LinkedListException {
		SLLNode current = list;

		if (list == null) {
			return list;
		}

		if (position == 1) {
			current = list.getNext();
			list.setNext(null);
			list = null;
			System.gc();
			return current;
		}

		else {
			int index = 1;
			SLLNode previous = list;

			while (index < position - 1) {
				previous = current;
				current = current.getNext();
				index++;
			}

			if (current == null) {
				throw new LinkedListException(
						"Unable to delete the node at given position " + position + " please reverfiy");
			} else {
				previous.setNext(current.getNext());
				current.setNext(null);
				current = null;
				System.gc();
			}

			return list;
		}
	}

	@Override
	public void insert(DLLNode list, int data, int position) {
		/* insert((SLLNode)list, data, position); */

		DLLNode current = list;
		DLLNode newNode = new DLLNode(data, null, null);

		if (list == null) {
			list = newNode;
			return;
		}

		if (position == 1) {
			newNode.setNext(list);
			list.setPrevious(newNode);
			list = newNode;
		}

		else {
			int index = 1;

			while (index < position - 1) {
				current = (DLLNode) current.getNext();
				index++;
			}

			if (current.getNext() == null) {
				current.setNext(newNode);
				newNode.setPrevious(current);
			} else {
				newNode.setNext(current.getNext());
				newNode.setPrevious(current);
				DLLNode previous = (DLLNode) current.getPrevious();
				previous.setNext(current.getNext());
			}
		}
	}

	@Override
	public DLLNode delete(DLLNode list, int position) throws LinkedListException {
		/* return (DLLNode) delete((SLLNode)list, position); */
		int length = length((SLLNode) list);

		if (length == 1 || length == 0) {
			list = null;
			return list;
		}

		DLLNode current = list;

		if (position == 1) {
			current = (DLLNode) list.getNext();
			current.setPrevious(null);
			list.setNext(null);
			list = null;
			System.gc();
			return current;
		}

		else {
			int index = 1;

			while (index < position - 1) {
				current = (DLLNode) current.getNext();
				index++;
			}

			if (current.getNext() == null) {
				current.getPrevious().setNext(null);
				current.setPrevious(null);
				current = null;
				System.gc();
			}

			else {
				DLLNode previous = (DLLNode) current.getPrevious();
				previous.setNext(current.getNext());
				DLLNode next = (DLLNode) current.getNext();
				next.setPrevious(previous);
				current = null;
				System.gc();
			}

			return list;
		}
	}

	@Override
	public CLLNode insert(CLLNode list, int position, int data) {
		int length = length(list);
		CLLNode newNode = new CLLNode(data, null);
		newNode.setNext(newNode);
		if (position == 1) {
			return insertatBeginning(list, newNode);
		}
		if (position == length) {
			return insertatEnd(list, newNode);
		}

		else {
			return insert(list, data, position);
		}

	}

	private CLLNode insertatEnd(CLLNode list, CLLNode newNode) {
		CLLNode current = list;
		if (list == null) {
			return newNode;
		}

		newNode.setNext(list);

		while (current.getNext() != current) {
			current = (CLLNode) current.getNext();
		}

		current.setNext(newNode);

		return list;
	}

	private CLLNode insertatBeginning(CLLNode list, CLLNode newNode) {
		CLLNode current = list;
		if (list == null) {
			return newNode;
		}

		newNode.setNext(list);
		while (current.getNext() != current) {
			current = (CLLNode) current.getNext();
		}
		current.setNext(newNode);
		return newNode;
	}

	@Override
	public void traverse(SLLNode list) {
		SLLNode current = list;
		SLLNode endofList = null;
		if (list instanceof CLLNode) {
			endofList = list;
		}

		for (; current != endofList; current.getNext()) {
			logger.info(" " + current.getData());
		}
	}

	@Override
	public CLLNode deleteAtBeginning(CLLNode list) {
		CLLNode current = list;

		while (current.getNext() != list) {
			current = (CLLNode) current.getNext();
		}

		current.setNext(list.getNext());

		list.setNext(null);

		return (CLLNode) current.getNext();
	}

	@Override
	public CLLNode deleteAtEnd(CLLNode list) {

		CLLNode current = list;

		while (current.getNext().getNext() != list) {
			current = (CLLNode) current.getNext();
		}

		CLLNode deleteNode = (CLLNode) current.getNext();

		deleteNode.setNext(null);

		deleteNode = null;

		System.gc();

		current.setNext(list);

		return list;
	}

	//NTH NODE FROM END : no of traversals : 1. Time complexity : O(n)
	@Override
	public SLLNode findNthNodeFromEnd(SLLNode list, int n) {

		int count = 0;
		SLLNode nthNode = list, currentNode = list;
		for (currentNode = list; currentNode != null; currentNode = currentNode.getNext()) {
			if (n - count == 0) {
				return nthNode;
			} else if (n - count > 0) {
				nthNode = nthNode.getNext();
			}
		}
		return nthNode;
	}

	@Override
	public boolean isListCyclic(SLLNode list) {
		boolean cyclic = true;
		SLLNode fastNode = list, slowNode = list;
		while (true) {
			fastNode = fastNode.getNext();
			if (fastNode == null) {
				cyclic = false;
				break;
			}
			else if (fastNode == slowNode) {
				cyclic = true;
				break;
			}
			fastNode = fastNode.getNext();
			if (fastNode == slowNode) {
				cyclic = true;
				break;
			}
			slowNode = slowNode.getNext();
		}
		return cyclic;
	}

	@Override
	public SLLNode findBeginingofLoop(SLLNode list) {
		
		boolean exists = false;
		SLLNode slowNode = list, fastNode = list;
		
		while(true) {
			
			fastNode = fastNode.getNext();
			
			if(fastNode == null) {
				exists = false;
				break;
			}
			
			else if(fastNode == slowNode) {
				exists = true;
				break;
			}
			
			fastNode = fastNode.getNext();
			
			if(fastNode == slowNode) {
				exists = true;
				break;
			}
			
			slowNode = slowNode.getNext();
		}
		
		if(exists) {
			slowNode = list;
			
			while(slowNode != fastNode) {
				slowNode = slowNode.getNext();
				fastNode = fastNode.getNext();
			}
			
			return slowNode;
			
		}
		
		return null;
	}

	@Override
	public int findLengthofCycle(SLLNode list) {
		
		SLLNode fastNode = list, slowNode = list;
		boolean exists = true;
		
		
		while(true) {
			fastNode = fastNode.getNext();
			if(fastNode == null) {
				exists = false;
				break;
			}
			
			if(fastNode == slowNode) {
				exists = true;
				break;
			}
			
			fastNode = fastNode.getNext();
			
			if(fastNode == slowNode) {
				exists = true;
				break;
			}
		}
		
		if(exists) {
			int count = 0;
			while(fastNode != slowNode) {
				fastNode = fastNode.getNext();
				slowNode = slowNode.getNext();
				count++;
			}
			return count;
		}
		return 0;
	}

	@Override
	public SLLNode insertNodeinSortedList(SLLNode list, int data) {
		
		SLLNode newNode = new SLLNode(data, null);
		
		if(list == null) {
			return newNode;
		}
		
		SLLNode previousNode = list, currentNode = list;
		
		while(currentNode.getData() < data) {
			previousNode = currentNode;
			currentNode = currentNode.getNext();
		}
		
		newNode.setNext(currentNode);
		previousNode.setNext(newNode);
		
		return list;
	}

	@Override
	public SLLNode reverse(SLLNode list) {
		SLLNode previousNode = null, nextNode = list;
		while(list != null ) {
			nextNode = list.getNext();
			list.setNext(previousNode);
			previousNode = list;
			list = nextNode;
		}
		return list;
	}

	@Override
	public SLLNode findMerge(SLLNode list1, SLLNode list2) {
		int length1 = length(list1);
		int length2 = length(list2);
		
		int diff = length1-length2;
		
		if(diff < 0 ) {
			SLLNode temp = list2;
			list2 = list1;
			list1 = temp;
		}
		
		for(int i = 0; i < diff; i++) {
			list1 = list1.getNext();
		}
		
		while(true) {
			if(list1 == list2) {
				return list1;
			}
			
			if(list1 == null || list2 == null) {
				break;
			}
			
			list1=list1.getNext();
			list2=list2.getNext();
		}
		
		return null;
	}

	@Override
	public SLLNode findMiddle(SLLNode list) {
		
		SLLNode currentNode = list, middleNode = list;
		
		boolean moveMiddlePointer = false;
		
		while(currentNode.getNext() != null) {
			
			if(!moveMiddlePointer) {
				currentNode = currentNode.getNext(); 
				moveMiddlePointer = true;
			}
			else if(moveMiddlePointer) {
				currentNode = currentNode.getNext();
				middleNode = middleNode.getNext();
				moveMiddlePointer = false;
			}
			
			
		}
		return middleNode;
	}

	@Override
	public void displayListFromEnd(SLLNode list) {
		if(list == null ) {
			return;
		}
		else {
			displayListFromEnd(list.getNext());
			logger.info(""+list.getData());
		}
	}

	@Override
	public boolean isListEven(SLLNode list) {
		SLLNode current = list;
		while(current!= null && current.getNext() != null) {
			current = current.getNext().getNext();
		}
		if(current!=null) {
			return false;
		}
		return true;
	}

	@Override
	public SLLNode merge(SLLNode a, SLLNode b) {
		
		SLLNode result = null;
		
		if(a == null) {
			return b;
		}
		
		if(b == null) {
			return a;
		}
		
	    if(a.getData() < b.getData()) {
			result = a;
			result.setNext(merge(a.getNext(), b));
		}
		
		else if(a.getData() >= b.getData()) {
			result = b;
			result.setNext(merge(a, b.getNext()));
		}
	    
	    return result;
	}
	
	//1->2->3->4->5

	@Override
	public SLLNode reverseListinKPairs(SLLNode list, int k) {
		
		if(list == null ) {
			return null;
		}
		
		SLLNode previousNode = list,  nextNode = list;
		
		for(int i = 0; i < k; ++i) {
			nextNode = list.getNext();
			list.setNext(previousNode);
			previousNode = list;
			list = nextNode;
		}
		
		if(nextNode != null) {
			list.setNext(reverseListinKPairs(nextNode, k));
		}
		
		return previousNode;
	}

	@Override
	public CLLNode[] splitCircularList(CLLNode list) {
		CLLNode[] newList = new CLLNode[2];
		CLLNode fastNode = list, slowNode = list;
		while(fastNode.getNext()!=list || fastNode.getNext().getNext()!= list) {
			fastNode = (CLLNode) fastNode.getNext().getNext();
			slowNode = (CLLNode) slowNode.getNext();
		}
		if(fastNode.getNext().getNext() == list) {
			fastNode = (CLLNode) fastNode.getNext();		
		}
		newList[0] = list;
		newList[1] = (CLLNode) slowNode.getNext();
		fastNode.setNext(newList[1]);
		slowNode.setNext(newList[0]);
		return newList;
	}

	@Override
	public int findWinner(int n, int m) {
		int i = 1;
		SLLNode list =  new SLLNode(1, null);
		SLLNode head = list;
		for(i = 2; i <= n; i++) {
			SLLNode newNode = new SLLNode();
			newNode.setData(i);
			newNode.setNext(null);
			list.setNext(newNode);
			list = newNode;
		}
		
		list.setNext(head);
		
		for(i = n; i >=1; i--) {
			for(int j = m; j >=1; j--) {
				list = list.getNext();
			}
			list.setNext(list.getNext().getNext());
		}
		return list.getData();
	}

	@Override
	public RLLNode clone(RLLNode list) {
		
		Hashtable<RLLNode, RLLNode> table = new Hashtable<RLLNode, RLLNode>();
		
		RLLNode currentNode = list, newList;
		
		while(currentNode != null ) {
			newList = new RLLNode(currentNode.getData(), null, null);
			newList.setData(currentNode.getData());
			table.put(currentNode, newList);
		}
		
		currentNode = list;
		
		while(currentNode != null ) {
			newList = table.get(currentNode);
			newList.setNext(table.get(currentNode.getNext()));
			newList.setRandom(table.get(currentNode.getRandom()));
			currentNode = (RLLNode) currentNode.getNext();
		}
		
		return table.get(list);
	}
	
	
	
	
	

}
