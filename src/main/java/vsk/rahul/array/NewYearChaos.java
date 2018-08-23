/**
 * 
 */
package vsk.rahul.array;

/**
 * @author rahul.vishvakarma
 *
 */
public class NewYearChaos {

	static void minimumBribes(int[] q) {

		int bribe = 0;
		for (int i = 0; i < q.length; i++) {
			if (i < q[i] - 1) {
				if (q[i] - 1 - i > 2) {
					System.out.println("Too chaotic");
					return;
				}
				bribe += q[i] - 1 - i;
			}

		}
		System.out.println(bribe);
	}
	
	public static void main(String[] args) {
		minimumBribes(new int[]{1, 2, 5, 3, 7, 8, 6, 4});
	}
}