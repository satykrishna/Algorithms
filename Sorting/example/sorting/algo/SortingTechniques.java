package example.sorting.algo;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author satya
 *
 */
public class SortingTechniques {

	public static int[] bubbleSort(int[] array) {

		for (int pass = array.length; pass > 0; pass--) {

			for (int i = 1; i < pass; i++) {

				if (array[i - 1] > array[i]) {
					int temp = array[i - 1];
					array[i - 1] = array[i];
					array[i] = temp;
				}
			}

		}
		return array;
	}

	public static void bubbleSortRevisited(int[] array) {
		for (int pass = array.length; pass > 0; pass--) {
			for (int i = 1; i < pass; i++) {
				if (array[i - 1] > array[i]) {
					int temp = array[i - 1];
					array[i - 1] = array[i];
					array[i] = temp;
				}
			}
		}
	}

	/*
	 * Best Case complexity : O(n) WorstCase complexity : O(n^2) average
	 * complexity : O(n2)
	 */

	public static void bubbleSortEnhanced(int[] array) {

		boolean swapRequired = true;

		for (int pass = array.length; pass > 0 && swapRequired; pass--) {

			for (int i = 1; i < pass; i++) {

				if (array[i - 1] > array[i]) {
					swapRequired = true;
					int temp = array[i];
					array[i] = array[i - 1];
					array[i - 1] = temp;
				}
				else {
					swapRequired = false;
				}
			}
		}
	}

	/*
	 * 1. Find the min element in the array 2. Swap it with the current position
	 * in the array Repeat this process untill current elements in the array are
	 * swapped
	 */

	public static void selectionSort(int[] array, int n) {
		for (int i = 0; i < array.length; ++i) {
			int min = i;
			for (int j = i + 1; j < array.length; ++j) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			int temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
	}

	public void selectionSortRevisited(int[] array) {

		for (int currentIndex = 0; currentIndex < array.length; currentIndex++) {
			int minIndex = currentIndex;
			for (int j = currentIndex + 1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			int temp = array[minIndex];
			array[minIndex] = array[currentIndex];
			array[currentIndex] = temp;
		}

	}

	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; ++i) {
			int key = array[i];
			int j = 0;
			for (j = i - 1; j >= 0 && array[j] > key; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = key;
		}
	}

	public static void insertSortRevisited(int[] array) {

		for (int i = 1; i < array.length; ++i) {

			int key = array[i];
			int j = i - 1;

			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}

	public static void bubbleSortRevisited3(int[] arr) {

		for (int i = arr.length; i > 0; i--) {
			for (int j = 1; j < i; ++j) {
				if (arr[j] > arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
	}

	public static void selectionSortRevisited2(int[] array) {

		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {

				if (array[j] < array[min]) {
					j = min;
				}
			}
			int temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
	}

	public static void insertSort2(int[] array) {

		for (int i = 1; i < array.length; ++i) {

			int temp = array[i];
			int j = i;

			while (j > 0 && array[j - 1] >= temp) {
				array[j] = array[j - 1];
				j--;
			}

			array[j] = temp;

		}
	}

	public static void shellSort(int[] arr) {
		int n = arr.length;

		// Start with a big gap, then reduce the gap
		for (int gap = n / 2; gap > 0; gap /= 2) {
			// Do a gapped insertion sort for this gap size.
			// The first gap elements a[0..gap-1] are already
			// in gapped order keep adding one more element
			// until the entire array is gap sorted
			for (int i = gap; i < n; i += 1) {
				// add a[i] to the elements that have been gap
				// sorted save a[i] in temp and make a hole at
				// position i
				int temp = arr[i];

				// shift earlier gap-sorted elements up until
				// the correct location for a[i] is found
				int j;
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
					arr[j] = arr[j - gap];

				// put temp (the original a[i]) in its correct
				// location
				arr[j] = temp;
			}
		}
	}

	public static void shellSortRevisited(int[] array) {

		for (int gap = array.length / 3; gap > 0; gap /= 3) {

			for (int i = gap; i < array.length; ++i) {

				int j;

				int temp = array[i];

				for (j = i; j >= gap && array[j - gap] >= temp; j -= gap) {
					array[j] = array[j - gap];
				}

				array[j] = temp;

			}
		}

	}

	public static void shellSortRevisited2(int[] array, int shellSortFactor) {

		for (int gap = (array.length / shellSortFactor); gap > 0; gap = gap / shellSortFactor) {

			for (int i = gap; i < array.length; ++i) {

				int temp = array[i];
				int j = 0;

				for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
					array[j] = array[j - gap];
				}

				array[j] = temp;
			}
		}
	}

	private static void mergeSort(int[] array) {

		int[] temp = new int[array.length]; // temporary Array

		for (int i = 0; i < temp.length; ++i)
			temp[i] = Integer.MIN_VALUE;

		mergeSort(array, temp, 0, array.length - 1);
	}

	private static void mergeSort(int[] array, int[] temp, int left, int right) {

		if (right > left) {

			int middle = (right + left) / 2;
			mergeSort(array, temp, left, middle);
			mergeSort(array, temp, middle + 1, right);
			merge(array, temp, left, middle + 1, right);
		}

	}

	private static void merge(int[] array, int[] temp, int left, int middle, int right) {

		int i;
		int leftEnd = middle - 1;
		;
		int size = right - left + 1;
		int tempPos = left;

		while (left <= leftEnd && middle <= right) {

			if (array[left] <= array[middle]) {
				temp[tempPos++] = array[left++];
			}
			else {
				temp[tempPos++] = array[middle++];
			}

		}

		if (left != leftEnd) {
			for (i = left; i <= leftEnd; i++) {
				temp[tempPos++] = array[i];
			}
		}

		for (i = middle; i <= right; i++) {
			temp[tempPos++] = array[i];
		}

		for (i = 0; i < size; i++) {
			array[right] = temp[right];
			right--;
		}

	}

	public void mergeSortRevisited(int[] array) {
		int temp[] = new int[array.length];
		mergeSortRevisited(array, temp, 0, array.length - 1);
	}

	private void mergeSortRevisited(int[] array, int[] temp, int start, int end) {

		if (start < end) {

			int middle = (start + end) / 2;
			mergeSortRevisited(array, temp, start, middle);
			mergeSortRevisited(array, temp, middle + 1, end);
			mergeRevisited(array, temp, start, middle + 1, end);
		}
	}

	private void mergeRevisited(int[] array, int[] temp, int start, int middle, int end) {

		int leftEnd = middle - 1;
		int leftPtr = start;
		int tempPos = start;
		int size = (end - start) + 1;

		while (leftPtr <= leftEnd && middle <= end) {

			if (array[leftPtr] <= array[middle]) {
				temp[tempPos++] = array[leftPtr++];
			}

			else {
				temp[tempPos++] = array[middle++];
			}
		}

		if (leftPtr != leftEnd) {
			for (int i = leftPtr; i <= leftEnd; i++) {
				temp[i] = array[i];
			}
		}

		if (middle != end) {
			for (int i = middle; i <= end; i++) {
				temp[i] = array[i];
			}
		}

		for (int i = 0; i <= size; i++) {
			array[end] = temp[end--];
		}

	}
	
	public static void quickSort(int array[]) {
		
		quickSort(array, 0, array.length-1);
		
	}

	private static void quickSort(int[] array, int low, int high) {
		
		int pivotIndex;
		
		if(high > low ) {
			
			pivotIndex = partition(array, low, high);
			
			quickSort(array, low, pivotIndex-1);
			quickSort(array, pivotIndex+1, high);
		}
	}

	private static int partition(int[] array, int low, int high) {
		
		int left = low;
		int right = high;
		int pivotalValue = array[low];
		
		while(left < right ) {
			
			while(array[left] <= pivotalValue)
				left++;
			
			while(array[right] > pivotalValue)
				right--;
			
			if(left < right ) {
				
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;
			}
			
		}
		array[left] = array[right];
		array[right] = pivotalValue;
		return right;
	}
	
	
	public  void quickSortRevisited(int []array) {
		
		quickSortRevisited(array, 0, array.length-1);
		
	}
	

	private void quickSortRevisited(int[] array, int start, int end) {
		
		if(start < end ) {
			
			int pivotalIndex = calculatePivotalIndex(array, start, end);
			
			quickSortRevisited(array, 0, pivotalIndex-1);
			quickSortRevisited(array, pivotalIndex+1, end);
		}
		
	}

	private int calculatePivotalIndex(int[] array, int start, int end) {
		
		int pivotalElement = array[start];
		int left = start;
		int right = end;
		while(left <= right ) {
			
			while(array[left] <= pivotalElement ) 
				left++;
			while(array[right] > pivotalElement)
				right--;
			if(left < right ) {
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;
			}
		}
		int temp = array[right];
		array[right] = pivotalElement;
		array[start] = temp;
		return right;
	}
	
	
	public void bubbleSort_R(int []arr) {
		
		for(int pass = arr.length; pass > 0; pass--){
			for(int i = 0; i < arr.length; i++) {
				if(arr[i+1] > arr[i]) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}
	
	public void selectionSort_R(int[] arr) {
		
		for(int i = 0; i < arr.length; ++i){
			
			int min = i;
			
			for(int j = i+1; j < arr.length; j++) {
				if(arr[j] < arr[min])
					min=j;
			}
			
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
			
		}
	}
	
	public void insertSort_R(int []array) {
		
		for(int i =1; i  < array.length; i++) {
			
			int temp = array[i];
			int j = i;
			
			while( j >=1 && array[j] > temp) {
				array[j-1] = array[j];
				j--;
			}
			
			array[j] = temp;
			
		}
		
	}
	
	public void shellsort_R(int []arr) {
		
		for(int partition = arr.length/2; partition >=0; partition/=2) {
			
			for(int i = partition; i <arr.length; ++i) {

				int temp = arr[i];
				
				int j;
				
				for(j = i; j >= partition && arr[j] > temp; j-=partition) {
					arr[j] = arr[j-partition];
				}
				
				arr[j] = temp;
				
			}
		}
	}
	

	public static void countSort(int []array) {
		
		int max = Arrays.stream(array).max().getAsInt();
		
		int output[] = new int[array.length];
		
		int []count = new int[max];
		
		Arrays.fill(count, 0);
		
		for(int i = 0; i < array.length; ++i) {
			count[array[i]]+=1;
		}
		
		for(int i = 0; i < array.length-1; ++i) {
			count[i+1]+=count[i];
		}
		
		for(int i = array.length-1; i >=0; i-- ) {
			output[count[array[i]]] = array[i];
			count[array[i]]--;
		}
	}
	
	
	public void countSortRevisited(int []array) {
		
		int[] output = new int[array.length];
		
		int max = Arrays.stream(array).max().getAsInt();
		
		int count[] = new int[max];
		
		Arrays.fill(count, 0);
		
		for(int i = 0; i < array.length; ++i) {
			count[array[i]]++;
		}
		
		for(int i = 0; i < array.length-1; ++i) {
			count[i+1]+=count[i];
		}
		
		for(int i =0 ; i < array.length; i++) {
			output[count[array[i]]] = array[i];
			count[array[i]]--;
		}
	}
	
	
	
	
	
	public static void radixSort(int[] arr) {
		
		int max = Arrays.stream(arr).max().getAsInt();
		
		for(int factor = 1; max/factor > 0; factor*=10)
			countStableSort(arr, factor);
	}
	

	private static void countStableSort(int[] arr, int factor) {
		
		int[] output = new int[arr.length];
		
		int[] count = new int[9];
		
		Arrays.fill(count, 0);
		
		for(int i = 0; i < arr.length; ++i) {
			count[(arr[i]/factor)%10]++;
		}
		
		for(int i = 1; i < count.length; i++){
			count[i] += count[i-1];
		}
	
		for(int i = arr.length-1; i >=-0; i--) {
			output[count[(arr[i]/factor)%10]] = arr[i];
			count[(arr[i]/factor)%10]--;
		}
		
        arr = Arrays.copyOf(output, output.length);		
		
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, -2, 0, 1, 0, 100, 101, 100, 200, 201, -94, -943, -904 };
		
		quickSort(array);

		Arrays.stream(array).forEach(element -> System.out.println(element));

	}

}
