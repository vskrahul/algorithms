/**
 * 
 */
package vsk.rahul.dp.rodcutting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

/**
 * Brute Force of Steel Rod cutting
 * in a way to get maximum price.
 * 
 * @author Rahul Vishvakarma
 *
 */
public class Introduction {

	private static final Logger logger = Logger.getLogger(Introduction.class);
	
	/*
	 * Brute Force approach
	 */
	static Integer cutRod(Integer[] prices, int size) {
		
		if(size == 0)
			return 0;
		
		int price = Integer.MIN_VALUE;
		
		for(int i = 1; i <= size; i++) {
			price = Integer.max(price, prices[i] + cutRod(prices, size - i));
		}
		
		return price;
	}
	
	static int[] cutPrices = new int[28];
	
	static {
		cutPrices[0] = 0;
	}
	
	/*
	 * Dynamic Programming approach
	 */
	static Integer cutRodDp(Integer[] prices, int size) {
		
		if(cutPrices[size] != 0)
			return cutPrices[size];
		
		if(size == 0)
			return 0;
		
		int price = Integer.MIN_VALUE;
		
		for(int i = 1; i <= size; i++) {
			cutPrices[size - i] = cutRodDp(prices, size - i);
			price = Integer.max(price, prices[i] + cutPrices[size - i]);
		}
		
		return price;
	}
	
	static String currentTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
	}
	
	public static void main(String[] args) {
		
		Integer[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30,
							31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
							41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
							51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
							61,62,63,64,65,66,67,68,69,70
							};
		final int size = 25;
		Long start = System.currentTimeMillis();
		
		logger.info(String.format("Optimized value of steel rod of size = %d, \nstartTime = %s", size, currentTime()));
		
		System.out.println(cutRod(prices, size));
		
		Long end = System.currentTimeMillis();
		
		logger.info(String.format("Total execution time in mili seconds = %d", end - start));
		
	}
}
