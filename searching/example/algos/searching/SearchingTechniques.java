package example.algos.searching;

public class SearchingTechniques {

	public static int findLastOccurrenceOfNo(int[] array, int data) {

		int low = 0;

		int high = array.length - 1;

		while (low <= high) {

			int middle = (low + high) / 2;

			if (high == middle && array[high] == data) {
				return high;
			}

			if (array[middle] == data && array[middle + 1] != data) {
				return middle;
			}

			else {

				if (array[middle] < data) {
					low = middle + 1;
				}

				else if (array[middle] > data) {
					high = middle - 1;
				}
			}
		}

		return -1;
	}

	public static void dutchNationalFlag(int[] array) {

		int left = 0;
		int right = array.length - 1;

		while (left < right) {

			while (array[left] % 2 == 0 && left < right) {
				left++;
			}

			while (array[right] % 2 == 1 && left < right) {
				right--;
			}

			if (left < right) {
				swap(array, left, right);
				left++;
				right--;
			}
		}
	}

	public static void seperateZerosFromOnes(int[] array) {

		int left = 0;
		int right = array.length - 1;

		while (left < right) {

			while (array[left] == 0 && left < right) {
				left++;
			}

			while (array[right] == 1 && left < right) {
				right--;
			}

			if (left < right) {
				swap(array, left, right);
				left++;
				right--;
			}
		}
	}

	public static void separate0s1s2s(int[] array) {

		int low = 0;

		int high = array.length - 1;

		int middle = (low + high) / 2;

		while (middle <= high) {

			switch (array[middle]) {

			case 0:
				swap(array, low, middle);
				low++;
				middle++;
				break;

			case 1:
				middle++;
				break;

			case 2:
				swap(array, middle, high);
				middle++;
				high--;
				break;
			}
		}
	}

	
	
	public static int findNoofTrailingZerosinFactorialofN(int number ) {
		
		int count = 0;
		
		for(int factor = 5; number/factor >=1; factor*=5) {
			
			if(number%factor == 0) {
				count++;
			}
			
		}
		
		return count;
	}
	
	
	public static int findMaxIndexDifference(int []array ) {
		
		int maxDiff = Integer.MAX_VALUE;
		int diff = 0;
		
		int i = 0;
		int j = 0;
		
		int leftMins[] = new int[array.length];
		int rightMaxs[] = new int[array.length];
		
		leftMins[0] = array[0];
		
		for(i = 1; i < array.length; i++) {
			
			if(array[i] <= leftMins[i-1]) {
				leftMins[i] = array[i];
			}
			
			else {
				leftMins[i] = leftMins[i-1];
			}
		}
		
		rightMaxs[array.length-1] = array[array.length-1];
		
		for( j = array.length-2; j >= 0; j--) {
			
			if(array[j] >= rightMaxs[j+1]) {
				rightMaxs[j] = array[j];
			}
			
			else {
				rightMaxs[j] = rightMaxs[j+1];
			}
		}
		
		i = 0;
		j = array.length-1;
		
		
		while( i < array.length && j < array.length) {
			if(leftMins[i] < rightMaxs[j]) {
				if(maxDiff < (j-i)) {
					maxDiff = j-i;
				}
			}
		}
		
		return maxDiff;
	}
	
	private static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
