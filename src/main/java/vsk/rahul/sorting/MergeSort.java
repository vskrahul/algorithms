package vsk.rahul.sorting;

import java.util.Arrays;

public class MergeSort {

    public void sort(int[] arr) {
        this.sort(arr, 0, arr.length - 1);
    }
    private void sort(int[] arr, int low, int high) {
        if(low < high) {
            int median = (low + high)/2;
            this.sort(arr, low, median);
            this.sort(arr, median + 1, high);

            this.merge(arr, low, median, high);
        }
    }

    private void merge(int[] arr, int low, int median, int high) {
        int N1 = median - low + 1;
        int N2 =  high - median;

        int[] LEFT = new int[N1];
        int[] RIGHT = new int[N2];

        //copy data
        for(int i = 0; i < N1; i++) {
            LEFT[i] = arr[low + i];
        }
        for(int i = 0; i < N2; i++) {
            RIGHT[i] = arr[median + 1 + i];
        }

        //merge LEFT and RIGHT array
        int i = 0;
        int j = 0;
        int k = low;
        while(i < N1 && j < N2) {
            if(LEFT[i] < RIGHT[j]) {
                arr[k++] = LEFT[i++];
            } else if(LEFT[i] > RIGHT[j]) {
                arr[k++] = RIGHT[j++];
            }
        }
        //merge remaining
        while(i < N1) {
            arr[k++] = LEFT[i++];
        }
        while(j < N2) {
            arr[k++] = RIGHT[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 0, 1, 8, 7, 6, 9, 4};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
