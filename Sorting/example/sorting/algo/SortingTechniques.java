package example.sorting.algo;

import java.util.Arrays;

/**
 * @author satya
 *
 */
public class SortingTechniques {

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

	public static void bubbleSortAlgorithm(int[] array) {

		for (int i = 1; i < array.length; i++) {
			for (int j = 1; j < array.length; j++) {
				if (array[j] <= array[j - 1]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
		
		Arrays.stream(array).forEach(System.out::println);
	}

	public static void selectionSortAlgorithm(int[] array) {
		for (int i = 0; i < array.length; i++) {

			int minIndx = i;

			for (int j = i; j < array.length; j++) {
				if (array[j] <= array[minIndx]) {
					minIndx = j;
				}
			}

			int temp = array[minIndx];
			array[minIndx] = array[i];
			array[i] = temp;
		}

		Arrays.stream(array).forEach(System.out::println);
	}

	public static void insertSortAlgorithm(int[] array) {

		for (int i = 1; i < array.length; i++) {
			
			int j;
			
			int temp = array[i];
			
			for(j = i; j >=1&& array[j-1] >= temp; j--) {
				array[j] = array[j-1];
			}
			
			array[j] = temp;

		}

		Arrays.stream(array).forEach(System.out::println);
	}

	public static void shellSortAlgorithm(int[] array) {

		for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
			for (int i = gap; i < array.length; i++) {
				int temp = array[i];
				int j ;
				for(j = i; j >=gap && array[j-gap] > temp; j=j-gap) {
					array[j] = array[j-gap];
				}
				array[j] = temp;
			}
		}

		Arrays.stream(array).forEach(System.out::println);

	}

	
	public static void mergeSortAlgorithm(int[] array) {
		
		int temp[] = new int[array.length];
		
		mergeSortAlgorithm(array, temp, 0, array.length-1);
	
		Arrays.stream(array).forEach(System.out::println);

	}
	
	
	private static void mergeSortAlgorithm(int[] array, int[] temp, int left, int right) {
		
		if(right > left ) {
			int middle = (right+left)/2;
			mergeSortAlgorithm(array, temp, left, middle);
			mergeSortAlgorithm(array, temp, middle+1, right);
			mergeAlgorithm(array, temp, left, middle+1, right);
		}
		
	}

	private static void mergeAlgorithm(int[] array, int[] temp, int left, int middle, int right) {
		
		int leftEnd = middle-1;
		
		int tempPos = left;
		
		int originalLeft = left;
		
		while(left <= leftEnd && middle <= right) {
			
			if(array[left] <= array[middle]) {
				temp[tempPos] = array[left];
				left++;
			}
			
			else {
				temp[tempPos] = array[middle];
				middle++;
			}
			tempPos++;
		}
		
		while(left <= leftEnd) {
			temp[tempPos] =  array[left];
			tempPos++;
			left++;
		}
		
		while(middle <=right) {
			temp[tempPos] = array[middle];
			tempPos++;
			middle++;
		}
		
		while(right >= originalLeft) {
			array[right] = temp[right];
			right--;
		}
		
	}

	public static void quickSortAlgorithm(int []array) {
		quickSortAlgorithm(array, 0, array.length-1);
		Arrays.stream(array).forEach(System.out::println);
	}
	
	private static void quickSortAlgorithm(int[] array, int start, int end) {
		
		if(end > start ) {
			int pivot = findPivotElementPosition(array, start, end);
			quickSortAlgorithm(array, start, pivot-1);
			quickSortAlgorithm(array, pivot+1, end);
		}
	}

	private static int findPivotElementPosition(int[] array, int start, int end) {
		int left = start, right = end;
		int pivotElement = array[left];
		
		while(left < right ) {
			
			while(array[left] <= pivotElement) {
				left++;
			}
			
			while(array[right] > pivotElement) {
				right--;
			}
			
			if(left < right) {
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;
			}
		}
		
		array[start] = array[right];
		array[right] = pivotElement;
		
		return right;
	}

	
	public static void countSortAlgorithm(int []array) {
		
		int[] count = new int[array.length];
		
		for(int i = 0; i < array.length; i++) {
			count[array[i]] ++;
		}
		
		for(int i = 0; i < array.length; i++) {
			count[i]+=count[i-1];
		}
		
		int []temp = new int[array.length];
		
		for(int i = array.length-1; i >=0; i--) {
			temp[count[array[i]]] = array[i];
			count[array[i]]--;
		}
		
	}
	
	public static void radixSortAlgorithm(int []array) {
	
		int maxNo = Arrays.stream(array).max().getAsInt();
		
		for(int factor = 1; maxNo/factor > 0; factor*=10) {
			radixSortAlgorithm(array,  factor);
		}
	}
	
	private static void radixSortAlgorithm(int[] array, int factor) {
		int count[] = new int[9];
		int temp[] = new int[array.length];
		
		for(int i = 0; i < array.length; i++ ) {
			count[(array[i]/factor)%10] ++;
		}
		
		for(int i = 0; i < array.length; i++) {
			count[i] += count[i-1];
		}
		
		for(int i = 0; i < array.length; i++) {
			temp[(array[i]/factor)%10] = array[i];
			count[(array[i]/factor)%10]--;
		}
		
		array = Arrays.copyOf(temp, temp.length);
	}
	
	
	public boolean doesArrayContainRepeatedElements(int[] array) {
		
		boolean isRepeated = false;
		
		mergeSortAlgorithm(array);
		
		for(int i = 0; i < array.length-1; i++) {
			if(array[i+1] == array[i]) {
				isRepeated = true;
				break;
			}
		}
		
		return isRepeated;
	}
	
	public int whoWinsElection(int[] array ) {
		
		
		mergeSortAlgorithm(array);
		
		int currentCandidate = array[0];
		
		int maxCandidate = array[0];
		
		int currentVotes = 1, maxVotes = 1;
		
		for(int i = 1; i < array.length; ++i) {
			
			if(array[i] == currentCandidate) {
				currentVotes++;
			}
			else {
				currentCandidate = array[i];
				currentVotes = 1;
			}
			
			if(currentVotes > maxVotes) {
				maxVotes = currentVotes;
				maxCandidate = array[i];
			}
		}
		
		return maxCandidate;
	}

	public void mergeTwoArraysinToOne(int a[], int b[], int m, int n) {
		
		int endA =  a.length-1;
		
		
		while(m >=0 && n >=0) {
			
			if(a[m] <= b[n]) {
				a[endA] = a[m];
				m--;
			}
			
			else {
				a[endA] = b[n];
				n--;
			}
			
			endA--;
		}
		
		if(m > 0) {
			while( m>=0 ) {
				a[endA] = a[m];
				m--;
				endA--;
			}
		}
		
		if(n > 0) {
			while(n >=0) {
				a[endA] = a[n];
				n--;
				endA--;
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		int[] array = new int[] {223, 3333, -123343, 1, 1, -2, 1001, -1001, 2, 1, 1, 1,1,  100, 101, 100, 200, 201, 0, 0, 0, -1002, -94, -943, -904,  -2, -22, 22, 22, 12};

		/*System.out.println("----BUBBLE----");
		
		bubbleSortAlgorithm(array);*/

	/*	System.out.println("----SELECTION----");

		selectionSortAlgorithm(array);*/

		/*System.out.println("---INSERT-----");

		insertSortAlgorithm(array);
*/
		/*System.out.println("----SHELL----");

		shellSortAlgorithm(array);
*/
	/*	System.out.println("---- MERGE---- ");

		mergeSortAlgorithm(array);*/
		
	/*	System.out.println("---- QUICK---- ");
		
		quickSortAlgorithm(array);*/
		
		int maxCandidate = new SortingTechniques().whoWinsElection(array);
		
		System.out.println("Max Candidate: " + maxCandidate);

	}

}
