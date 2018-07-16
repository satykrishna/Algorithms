package example.searching.algos;

import static example.sorting.algo.SortingTechniques.mergeSortAlgorithm;
import static example.sorting.algo.SortingTechniques.quickSortAlgorithm;

import org.apache.log4j.Logger;

public class SearchingTechniques {

	private static final Logger logger = Logger.getLogger(SearchingTechniques.class);

	public static int findElementPosition(int[] array, int valueToBeSearched) {

		quickSortAlgorithm(array);

		int low = 0;

		int high = array.length - 1;

		while (low <= high) {
			int middle = (low + high) / 2;

			if (array[middle] == valueToBeSearched) {
				return middle;
			}

			else if (array[middle] < valueToBeSearched) {
				low = middle + 1;
			}

			else if (array[middle] > valueToBeSearched) {
				middle = high - 1;
			}
		}

		return Integer.MIN_VALUE;
	}

	public static int findElementThatRepeatedTwice(int[] array) {

		int[] count = new int[array.length];

		for (int i = 0; i < array.length; i++) {

			if (count[array[i]] == 2) {
				return i;
			}

			count[array[i]]++;
		}

		return Integer.MIN_VALUE;
	}

	public static boolean searchPairsinArrayWhoseSumisK(int[] array, int k) {

		boolean isFound = false;
		mergeSortAlgorithm(array);
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int val = array[left] + array[right];
			if (val == k) {
				logger.info("Index1 " + left + " Index2 " + right);
				isFound = true;
			}
			if (val < k) {
				left++;
			}
			else if (val > k) {
				right--;
			}
		}
		return isFound;
	}

	public static void searchForPythogoreanTriplets(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			array[i] *= array[i];
		}
		mergeSortAlgorithm(array);
		for (int i = array.length - 1; i > 0; --i) {
			int key = array[i];
			int right = array[i - 1];
			int left = 0;
			while (left <= right) {
				int val = array[left] + array[right];
				if (val == key) {
					logger.info("Index1 " + left + " Index2 " + right);
				}
				if (val < key) {
					left++;
				}
				else if (val > key) {
					right--;
				}
			}
		}
	}

	public static void findTwoIndexesWhoseSumisClosestToZero(int[] array) {

		mergeSortAlgorithm(array);

		int minNegativeSum = Integer.MIN_VALUE;
		int minPositiveSum = Integer.MAX_VALUE;

		int left = 0;

		int right = array.length - 1;

		while (left < right) {

			int sum = array[left] + array[right];

			if (sum > 0) {
				if (sum < minPositiveSum) {
					minPositiveSum = sum;
				}
				right--;
			}

			else if (sum < 0) {
				if (sum > minNegativeSum) {
					minNegativeSum = sum;
				}
				left++;
			}
		}

		logger.info("Minimum Positive sum is " + minPositiveSum + " Min Negative Sum is " + minNegativeSum);
	}

	public static void find3ElementsWhoseSumisK(int[] array, int k) {

		mergeSortAlgorithm(array);

		for (int i = 0; i < array.length; i++) {

			int left = i + 1;
			int right = array.length - 1;

			while (left < right) {

				int val = array[i] + array[left] + array[right];

				if (val == k) {
					logger.info(" index1 " + i + " index2 " + left + " index3 " + right);
				}

				if (val > k) {
					right--;
				}

				else if (val < k) {
					left++;
				}
			}
		}
	}
	
	public static int findIndexWhichAltersTheSequence(int []array) {
		
		int left = 0;
		
		int right = array.length-1;
		
		while(left <= right) {
			
			if(left == right) {
				return array[left]; 
			}
			
			else if(left == right-1) {
				return Integer.max(array[left], array[right]);
			}
			
			else {
				
				int middle = (left + right)/2;

				if(array[middle-1] < array[middle] && array[middle] > array[middle+1]) {
					return array[middle];
				}
				
				else if(array[middle-1] < array[middle] && array[middle] < array[middle+1] ){
					left = middle+1;
				}
				
				else if(array[middle-1] > array[middle] && array[middle] > array[middle+1]) {
					right = middle-1;
				}
			}
		}
		
		return Integer.MIN_VALUE;
	}
	
	
	public static int findElementPositioninArrayWhichisRotated(int []array, int data) {
		return findElementPositioninArrayWhichisRotated(array, 0, array.length-1, data);
	}

	private static int findElementPositioninArrayWhichisRotated(int[] array, int start, int end, int data) {
		
		if(end > start) 
			return -1;
		
		int middle = (end + start)/2;
		
		if(array[middle] == data) {
			return middle;
		}
		
		else if (array[start] < array[middle]) {
			
			if(array[start] <= data && data < array[middle]) {
				return findElementPositioninArrayWhichisRotated(array, start, middle-1, data);
			}
			
			else {
				return findElementPositioninArrayWhichisRotated(array, middle+1, end, data);
			}
		}
		
		else if(array[middle] < array[end]) {
			
			if(array[middle] <= data && data < array[end]) {
				return findElementPositioninArrayWhichisRotated(array, middle+1, end, data);
			}
			else {
				return findElementPositioninArrayWhichisRotated(array, start, middle-1, data);
			}
		}
		
		return Integer.MIN_VALUE;
	}
	
	
	public static int findFirstRepeatedElementinSortedArray(int []array, int data) {
		return findFirstRepeatedElementinSortedArray(array, 0, array.length-1, data);
	}

	private static int findFirstRepeatedElementinSortedArray(int[] array, int start, int end, int data) {
		if(start == end) {
			return array[start] == data ? start : Integer.MIN_VALUE;
		}
		
		if(start == end-1) {
			
			if(array[start] == data) {
				return start;
			}
			
			else if(array[end] == data) {
				return end;
			}
		}
		
		else {
			
			int middle = (start+end)/2;
			
			if(array[middle-1]!= data && array[middle] == data && array[middle+1] == data ) {
				return middle;
			}
			
			else if (array[middle] > data) {
				return findFirstRepeatedElementinSortedArray(array, start, middle-1, data);
			}
			
			else {
				return findFirstRepeatedElementinSortedArray(array, middle+1, end, data);
			}
			
		}
		
		return 0;
	}
	

}
