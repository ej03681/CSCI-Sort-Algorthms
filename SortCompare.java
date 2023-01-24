package CSCIAlgorithms;

public class SortCompare {
	static int count = 0;
	static int count2 = 0;

	public static void main(String[] args) {

		System.out.print("   Array        Merge             Min/Max        Quick             Min/Max");
		System.out.println();
		System.out.print("Elements     Sort(ns)                          Sort(ns)        ");
		printValue(10000);
		count = 0;
		count2 = 0;
		printValue(20000);
		count = 0;
		count2 = 0;
		printValue(50000);
	}
	public static void printValue(int arraySize) {
		int strWidth = 8;
		int width = 13;
		
		int[] list = new int[arraySize];
		for (int i = 0; i < list.length; i++) {
			list[i] = (int) (Math.random() * 1000000);
		}
		
		int[] list2 = new int[arraySize];
		System.out.printf("\n%" + strWidth + "d", arraySize);
		list2 = new int[arraySize];
		System.arraycopy(list, 0, list2, 0, list.length);
		long startTime = System.nanoTime();
		mergeSort(list2);
		long endTime = System.nanoTime();
		long executionTime = endTime - startTime;
		System.out.printf("%" + width + "d", count);
		System.out.printf("%" + width + "d", list2[0]);
		System.out.print("/" + list2[arraySize - 1]);
		
		

		list2 = new int[arraySize];
		System.arraycopy(list, 0, list2, 0, list.length);
		startTime = System.nanoTime();
		quickSort(list2);
		endTime = System.nanoTime();
		executionTime = endTime - startTime;
		System.out.printf("%" + width + "d", count2);
		System.out.printf("%" + width + "d", list2[0]);
		System.out.print("/" + list2[arraySize - 1]);
	}
	
	public static void mergeSort(int[] list) {
		if (list.length > 1) {
			// Merge sort the first half
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);

			// Merge sort the second half
			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);

			// Merge firstHalf with secondHalf into list
			merge(firstHalf, secondHalf, list);
		}
	}

	public static void merge(int[] list1, int[] list2, int[] temp) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp

		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1] < list2[current2]) {
				temp[current3++] = list1[current1++];
				count++;
			}else
				temp[current3++] = list2[current2++];
				count++;
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];
			count++;

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
			count++;
	}
	
	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}

	private static void quickSort(int[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
	private static int partition(int[] list, int first, int last) {
		int pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && list[low] <= pivot)
				low++;

			// Search backward from right
			while (low <= high && list[high] > pivot)
				high--;

			// Swap two elements in the list
			if (high > low) {
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
				count2++;
			}
		}

		while (high > first && list[high] >= pivot)
			high--;

		// Swap pivot with list[high]
		if (pivot > list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}
	}
}
