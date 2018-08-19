package NonAdjecentSubMax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NonAdjectMax {

	public static void main(String[] args) {

		Integer arr[] = {-2, -1, -3, -4, -5 };
		List<Integer> storeElement = new ArrayList<Integer>();

		for (int i = 0; i < arr.length-2; i++) {

			storeElement.add(findMaxSubsetElement(arr, i));
		}

		System.out.println(Collections.max(storeElement));

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
	
	static Integer findMaxSubarraySum(Integer arr[]) {
		
		int max = max(arr);
		
		for(int i = 0; i < arr.length; i++) {
			
			for(int j = i + 2; j < arr.length; j++) {
				max = Integer.max(max, arr[i] + arr[j]);
			}
			
		}
		
		return -1;
	}
	
	static Integer max(Integer arr[]) {
		int max = Integer.MIN_VALUE;
		for(int v : arr)
			max = Integer.max(max, v);
		return max;
	}

}
