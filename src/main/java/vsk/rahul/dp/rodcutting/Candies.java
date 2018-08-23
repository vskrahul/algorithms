/**
 * 
 */
package vsk.rahul.dp.rodcutting;

import java.util.Arrays;

/**
 * 
 * <p>Alice is a kindergarten teacher. She wants to give some candies to the children in her class. 
 * 	All the children sit in a line and each of them has a rating score according to his or her performance in the class. 
 * 	Alice wants to give at least 1 candy to each child. 
 * 	If two children sit next to each other, then the one with the higher rating must get more candies. 
 * 	Alice wants to minimize the total number of candies she must buy.
 * 
 * 
 * @author rahul.vishvakarma
 * @see <a href="https://www.hackerrank.com/challenges/candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming">
 *		Candies</a> 
 */
public class Candies {

	static int[] candies(int[] ranks) {
		
		int[] candies = new int[ranks.length];
		
		candies[0] = 1;
		
		for(int i = 1; i < ranks.length; i++) {
			
			if(ranks[i - 1] > ranks[i]) {
				candies[i] = 1;
				if(candies[i - 1] == 1) {
					patch(candies, ranks, i);
				}
			} else {
				if(ranks[i] > ranks[i - 1]) {
					candies[i] = candies[i - 1] + 1;
				} else {
					candies[i] = 1;
				}
			}
		}
		
		return candies;
	}
	
	static void patch(int[] candies, int[] ranks, int start) {
		
		int idx = start;
		
		while(idx > 0 && ranks[idx - 1] > ranks[idx] && candies[idx - 1] <= candies[idx]) {
			candies[idx - 1] = candies[idx] + 1;
			idx--;
		}
	}
	
	static long count(int[] ranks) {
		long sum = 0L;
		for(int i : ranks) {
			sum += i;
		}
		return sum;
	}
	
	static int[] cadiesNew(int[] ranks) {
		int[] candies = new int[ranks.length];
		
		candies[0] = 1;
		
		for(int i = 1; i < ranks.length; i++) {
			
			if(ranks[i] > ranks[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			} else {
				candies[i] = 1;
			}
			
		}
		
		for(int i = ranks.length - 1; i > 0; i--) {
			
			if(ranks[i - 1] > ranks[i] && candies[i - 1] <= candies[i]) {
				candies[i - 1] = candies[i] + 1;
			}
		}
		
		return candies;
	}
	
	public static void main(String[] args) {
		
		int[][] ranks = {
			{4, 6, 4, 5, 6, 2},
			{9, 4, 2, 6, 1, 7, 8, 2, 2, 1},
			{9, 2, 3, 6, 5, 4, 3, 2, 2, 2},
			{1, 2, 2},
			{2, 4, 2, 6, 1, 7, 8, 9, 2, 1},
			{2, 4, 3, 5, 2, 6, 4, 5},
			{9,8,7,7,6,6,5,4,3}
		};
		
		for(int[] rank : ranks) {
			int[] candies = candies(rank);
			System.out.println(Arrays.toString(rank) + " = " + Arrays.toString(candies));
			System.out.println(count(candies));
		}
		
	}
}