package example.sorting.algo;

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
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1)
            {
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
		
		for(int gap = array.length/3; gap > 0; gap/=3) {

			
			for(int i = gap; i < array.length; ++i) {
				
				int j;
				
				int temp = array[i];
				
				for(j = i; j >=gap && array[j-gap] >=temp; j-=gap) {
					array[j] = array[j-gap];
				}
				
				array[j] = temp;
				
				
			}
		}
		
	}
	
	public static void shellSortRevisited2(int []array, int shellSortFactor) {
		
		for(int gap = (array.length/shellSortFactor); gap >0; gap = gap/shellSortFactor) {
			
			for(int i = gap; i < array.length; ++i) {
			
				int temp = array[i];
				int j = 0;
				
				for(j = i; j >=gap && array[j-gap] > temp; j-=gap) {
					array[j] = array[j-gap];
				}
				
				array[j] = temp;
			}			
		}
	}
	
	

	private static void mergeSort(int[] array) {
		
		int []temp = new int[array.length]; //temporary Array
		mergeSort(array, temp, 0, array.length-1);
	}
	
	
	private static void mergeSort(int[] array, int[] temp, int left, int right) {
		
		if(right > left ) {
			
			int middle = (right+left)/2;
			mergeSort(array, temp, left, middle);
			mergeSort(array, temp, middle+1, right);
			merge(array, temp, left, middle+1, right);
		}
		
	}

	private static void merge(int[] array, int[] temp, int left, int middle, int right) {
		
		int i;
		int leftEnd = middle-1;;
		int size = right -left+1;
		int tempPos = left;
		
		
		while(left <=leftEnd && middle <=right) {
			
			if(array[left] <= array[middle]) {
				temp[tempPos++] = array[left++];
			}
			else {
				temp[tempPos++] = array[middle++];
			}
			
		}
		
		
		for(i = left; i <=leftEnd; i++) {
			temp[tempPos++] = array[i];
		}
		
		for(i = middle; i <=right; i++) {
			temp[tempPos++] = array[i];
		}
		
		for(i = right; i>=left; i--) {
			array[i] = temp[i];
		}
		
	}

	public static void main(String[] args) {
		int[] array = new int[] { 3, 2, 1, 10, 15, 16};
		mergeSort(array);
		Arrays.stream(array).forEach(element -> System.out.println(element));
	}

	

}
