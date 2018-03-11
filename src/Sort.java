public class Sort {

	private Sort() {
	}

	public static void mergeSort(Integer[] values, int low, int hight) {
		int middle = (low + hight) / 2;

		if (low < hight) {
			mergeSort(values, low, middle);
			mergeSort(values, middle + 1, hight);
			merge(values, low, middle, hight);

		}

	}

	private static void merge(Integer[] values, int low, int middle, int hight) {
		int[] temp = new int[(hight - low) + 1];

		int lowLeft = low;
		int hightLeft = middle;
		int lowRight = middle + 1;
		int hightRight = hight;
		
		for (int i = 0; i < temp.length; i++) {
			if (lowRight > hightRight) {
				temp[i] = values[lowLeft++];
				continue;
			} else if (lowLeft > hightLeft) {
				temp[i] = values[lowRight++];
				continue;
			}
			
			if (values[lowRight] < values[lowLeft]) {
				temp[i] = values[lowRight];
				lowRight++;
			} else {
				temp[i] = values[lowLeft];
				lowLeft++;
			}
		}
		
		for (int i = 0, j = 0; i < values.length; i++) {
			if (i >= low && i <= hight) {
				values[i] = temp[j++];
			}
		}
	}
}
