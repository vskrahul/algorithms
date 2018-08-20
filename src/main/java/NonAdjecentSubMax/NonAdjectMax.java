package NonAdjecentSubMax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NonAdjectMax {

	public static void main(String[] args) {
		Integer arr[] = {-2, 1, 3, -4, 5};
		System.out.println(findMaxSubarraySum(arr));
	}

	static Integer findMaxSubsetElement(Integer arr[], int i) {
		List<Integer> storeAllElemetOfSubset = new ArrayList<Integer>();

		for (int j = i + 2; j < arr.length; j = j + 2) {
			if(storeAllElemetOfSubset.size() == 0)
				storeAllElemetOfSubset.add(arr[i] + arr[j]);
			else 
				storeAllElemetOfSubset.add(storeAllElemetOfSubset.get(storeAllElemetOfSubset.size()-1) + arr[j]);
		}

		for (int k = i + 3; k < arr.length; k++) {
			storeAllElemetOfSubset.add(arr[i] + arr[k]);
		}

		return Collections.max(storeAllElemetOfSubset);

	}
	
	/**
	 * Sub-array which has maximum sum. 
	 * 
	 * @param arr array of integers with negative and positive and integers.
	 * @return maximum sum
	 */
	static Integer findMaxSubarraySum(Integer arr[]) {
		arr[0] =  Integer.max(0, arr[0]);
		arr[1] = Integer.max(arr[0], arr[1]);
		
		for(int i = 2; i < arr.length; i++) {
			arr[i] = Integer.max(arr[i - 1], arr[i - 2] + arr[i]);
		}
		return arr[arr.length - 1];
	}
}