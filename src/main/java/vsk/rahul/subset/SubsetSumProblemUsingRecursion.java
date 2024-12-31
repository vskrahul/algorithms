package vsk.rahul.subset;

public class SubsetSumProblemUsingRecursion {

    public static boolean solution(int[] arr, int target) {
        Boolean[][] memoization = new Boolean[arr.length + 1][target + 1];

        boolean result = helper(0, arr, target, memoization);
        for(int i = 0; i <= arr.length; i++) {
            for(int j = 0; j <= target; j++) {
                System.out.printf("[%s]", memoization[i][j] != null && memoization[i][j] ? "x" : " ");
            }
            System.out.println();
        }
        return result;
    }

    private static boolean helper(int vidx, int[] arr, int target, Boolean[][] memoization) {

        if(target == 0) {
            memoization[vidx][target] = true;
            return true;
        }
        if(target < 0) {
            return false;
        }
        if(vidx >= arr.length) {
            return false;
        }
        if(memoization[vidx][target] != null) {
            return memoization[vidx][target];
        }
        boolean left = helper(vidx + 1, arr, target - arr[vidx], memoization);
        boolean right = helper(vidx + 1, arr, target, memoization);
        memoization[vidx][target] = left || right;
        return memoization[vidx][target];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 6, 10 };
        System.out.println(solution(arr, 11));
    }
}
