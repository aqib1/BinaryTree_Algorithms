
public class MainClass {

	public static int findNo(int[] arr, int k) {
		int pivot = findPivot(arr, 0, arr.length - 1);
		if (pivot == -1) {
			return binarySearch(arr, 0, arr.length - 1, k);
		}
		if (arr[pivot] == k) {
			return arr[pivot];
		}
		if (k < arr[0]) {
			return binarySearch(arr, pivot + 1, arr.length - 1, k);
		} else {
			return binarySearch(arr, 0, pivot - 1, k);
		}
	}

	private static int binarySearch(int[] arr, int start, int length, int k) {
		if (length < start)
			return -1;
		if (arr.length == 1 && arr[0] == k)
			return arr[0];
		int mid = (start + length) / 2;
		if (arr[mid] == k) {
			return arr[mid];
		} else if (arr[mid] > k) {
			return binarySearch(arr, start, mid - 1, k);
		} else {
			return binarySearch(arr, mid + 1, length, k);
		}
	}

	// 4,5,6,1,2,3
	private static int findPivot(int[] arr, int start, int end) {
		if (end < start)
			return -1;
		if (arr.length == 1)
			return arr[0];
		int mid = (start + end) / 2;
		if (mid == 0) {
			return arr[mid + 1] > arr[mid] ? mid + 1 : mid;
		} else {
			if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
				return mid;
			} else if (arr[mid] < arr[start]) {
				return findPivot(arr, start, mid - 1);
			} else if (arr[mid] > arr[start]) {
				return findPivot(arr, mid + 1, end);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(findNo(new int[] { 4, 5, 6, 1, 2, 3 }, 1));
	}
}
