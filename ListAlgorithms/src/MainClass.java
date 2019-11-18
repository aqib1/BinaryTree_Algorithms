import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {

	public static void dutchFlagProblem(int[] arr) {
		int min = 0, max = arr.length - 1, mid = 0;
		while(mid <= max) {
			switch(arr[mid]) {
			case 0:
				arr[min] = arr[min] +  arr[mid];
				arr[mid] = arr[min] - arr[mid];
				arr[min] =  arr[min] - arr[mid];
				min++; mid++;
				break;
			case 1:
				mid++;
				break;
			case 2:
				arr[mid] = arr[mid] + arr[max];
				arr[max] = arr[mid] - arr[max];
				arr[mid] = arr[mid] - arr[max];
				break;
			default:	
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, 1, 22, 12 };
		// bubblesort(arr);
		mergeSort(arr, 0, arr.length);
		// System.out.println(Arrays.toString(mergeSort(arr, 0, arr.length)));
	}

	private static void mergeSort(int[] arr, int start, int end) {
		if (arr.length > 1) {
			int mid = (start + end) / 2;
			int[] left = Arrays.copyOfRange(arr, start, mid);
			int[] right = Arrays.copyOfRange(arr, mid, end);
			mergeSort(left, start, left.length);
			mergeSort(right, start, right.length);
			System.out.println(Arrays.toString(left));
			System.out.println(Arrays.toString(right));

			// return merge(left, right);
		}
		// return null;
	}

	private static Integer[] merge(int[] left, int[] right) {
		List<Integer> li = new ArrayList<>();
		int i = 0, j = 0;
		while (i != left.length || j != right.length) {
			if (left[i] < right[j]) {
				li.add(left[i]);
				++i;
			} else {
				li.add(right[j]);
				++j;
			}
		}

		System.out.println(li);
		return li.toArray(new Integer[li.size()]);
	}

	private static void bubblesort(int[] arr) {
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr.length; y++) {
				if (arr[x] < arr[y]) {
					int temp = arr[x];
					arr[x] = arr[y];
					arr[y] = temp;
				}
			}
		}

	}
}
