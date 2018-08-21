/**
 * 
 */
package vsk.rahul.dp.abbreviation;

import org.apache.log4j.Logger;

/**
 * @author Syed Sajid Raza Rizvi
 *
 */
public class Abbreviation {

	private static Logger logger = Logger.getLogger(Abbreviation.class);
	
	private static int[] store = new int[26];

	/**
	 * If {@code a} matches with {@code match} String.
	 * 
	 * <ol>
	 * <li>Capitalize zero or more of {@code a}'s lower case letters.
	 * <li>Delete all of the remaining lower case letters in {@code a}.
	 * </ol>
	 * 
	 * @param a String to modify for match
	 * @param match String which should be matched
	 * @return If the string can match or not?
	 */
	static boolean check(String a, String match) {

		for (int i = 0, m = 0; i < a.length(); i++) {

			if (a.charAt(i) == match.charAt(m) || a.charAt(i) - 32 == match.charAt(m)) {
				logger.debug(a.charAt(i) + " == " + match.charAt(m));
				/*
				 * If a lower case match is found and next matching then keep the count. It will
				 * be useful for ignoring UPPER CASE char in a's after a match
				 * is found. Increment the counter in store.
				 */
				if (Character.isLowerCase(a.charAt(i)) 
								&& i + 1 < a.length() 
								&& a.charAt(i) == Character.toLowerCase(a.charAt(i + 1))) {
					
					store[a.charAt(i) - 97] += 1;
					logger.debug(String.format("store[%c] = %d", a.charAt(i), store[a.charAt(i) - 97]));
				}

				/*
				 * match string is completely matched but still we might have
				 * chars in a's.
				 */
				if (m == match.length() - 1) {
					logger.debug(String.format("%s is matched, [%s] left in String a.", match, a.substring(i + 1)));
					/*
					 * Check if a's have any UPPER CASE char left except which
					 * is in store. If yes... means it is not a match.
					 */
					if (hasCaps(a, i + 1))
						return false;
					/*
					 * If no... means it is a match.
					 */
					return true;
				}
				m++;
			} else {
				/*
				 * char is not matched. 
				 * If it is UPPER CASE char, check the store if we can remove/ignore this char.
				 */
				if (a.charAt(i) <= 91) {
					if(store[a.charAt(i) - 65] > 0) {
						store[a.charAt(i) - 65] -= 1;
						continue;
					}
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Check if any UPPER CASE char is in 'a' starting from give index 'start'.
	 * Ignore the UPPER CASE char which is in store.
	 * 
	 * @param a string
	 * @param start starting index
	 * @return {@code true} - if it has UPPER CASE char, else {@code false}.
	 */
	static boolean hasCaps(String a, int start) {

		char[] arr = a.toCharArray();

		for (int i = start; i < arr.length; i++) {
			/*
			 * This is UPPER CASE char
			 */
			if (arr[i] <= 91) {
				logger.debug(arr[i] + " is UPPER CASE char and count = " + store[arr[i] - 65]);
				if (store[arr[i] - 65] == 0)
					return true;
				store[arr[i] - 65] -= 1;
			}
		}
		return false;
	}
	
	static String abbreviation(String a, String b) {
		if(a.isEmpty() && b.isEmpty())
			return "YES";
		if(a.isEmpty() && !b.isEmpty())
			return "NO";
		if(!a.isEmpty() && b.isEmpty())
			return "NO";
		return check(a, b) ? "YES" : "NO";
	}

	public static void main(String[] args) {
		String[] a = {
				//"lyylyyllyyylllyylyyylyllylyllllllyyylyllyyyylllllylyylyyllylyylllyhyllllyylllyllylyllylyllllyylylylyyylyllyyylylllyylylllyyllyylylyyllyylyyylllyllylyyllyllllyylylyylllllllyllyyyyyylllyyylylylylyyyyyyyymylyyyylyyyylyyyylyyyylylylylylyllylyylllyllyylylyyllyyyylylllyyyyyllllllyllyylllylyylyllllyyllllylyyyyyllllylylllyyyylyylyyyllyylyyyylylyyyylyyyyylyylllyyllylyyllyllyyyyyylylllylyyyyyllyylyyyyylyyylyylyylylylyyllllyylllyylylllyllyylylyllylllyllyyyyyylyyyllyllyyllyllyylyllyllyyylyyyyylylllyyylllyyyllylyllylylyyllylllylyyyyylyyyylyyyylyyyyylylllllyylyylyyyylyylyyylyylllllllyyyyyyyylyyylylllllylyrlyylllyylylllllylyylyylyyllylyyyyllyyyllylllyllylllylyyyyylylylyyllyyyyylllyyyllllylyllyyyllllyyllyyylllylyylyyyllllyllylllylyllylllyyllllyllyyymyylylllyylllllllyyyyylyyyllyyyyyyylylylyylylyylylyyllyyyllylylyyyylyyyyyyyyyyylllylylllllylllyylllyyllllllyylllllyllyyllyylyyllllyylyylyyllllyyyllllyyylylylylyylyllylllyyylylylylyyylyllllllylyllllyylyllylllyllyylylllylllyllllylyyylylllyyylllyylllllllyllyyy"
				"LLZOSYAMQRMBTZXTQMQcKGLR"
				,"SRTRING"
				,"ababbaAbAB"
				,"baaBa"
				,"bbbaaabB"
				};
		
		String[] b = {
					//"LYYLYYLYYYLLLYYLYYYYLLYLLLLLLYYYLYLLYYYYLLLLYLYLYYLLYLYLLYYLLLYYLLLLYLYLLYLYLLLLYYLYLLYYLYLLYLLLLYLYLLYLYYLLYYLLYYLYYYLLLYLYLYYLLYLLYYYLYLLLLLLLYLLYYYYYLLLYYYLYLYLYLYYYYYYYLYYYLYYYYLYYLYYYYLYYLYLYLYLLYLYYLLLYLLYYLYLYLLYYYLYLLLYYYYLLLLLLYLLYYLLLYLYYLYLLLLYLLLYLYYYYYLLLLLYLLLYYYYLYYLYYLLYYLYYYYLYLYYYYLYYYYYLYYLLLYYLLYLYLLYLLYYYYYLYLLYLYYYYYLLYYLYYYYLYYYLYYLYYLYLYLYYLLLLYYLLLYYLYLLLYLLYLYLYLLYLLLYLLYYYYYYLYYYLLYLYYLLYLLYLYLLYLLYYYLYYYYLLLLYYYLLLYYYLLYLYLLYLYLYYLLYLLLYLYYYYYLYYYYLYYYYLYYYYYLYLLLLLYYLYYLYYYLYYYYYLYYLLLLLLLYYYYYYYYLYYLYLLLLYLYLYYLLYYLYLLLLLYLYYLYYLYLLYLYYYLYYYLYLLLYLLYLLYLYYYYYLLYYYLLYYYYYLLYYYLLLLYLYLLYYYLLLLYYLLYYYLLLYLYYLYYYLLLYLLYLYLYLLYLLYYLLLYLLYYYYYLYLLLYYLLLLLLLYYYYYLYYLLYYYYYYLYYLLYYLYLYYLLYYYLLYYLYYYYLYYYYYYYYYYYLLLYYLLLLLYLLLYYLLLYYLLLLLYYLLLLYLYYLLYYLYYLLLYYLYYLYYLLLLYYYLLLLYYYYLYLYLYYYLLYLLLYYYLYLYLYLYYLYLLLLLYLYLLLYYYLLYLYLLYYLYLLYLLLYLLYLYYYLYLLLYYLLYYLLLLLLYLYY"
					"LLZOSYAMBTZXMQKLR"
					,"STRING"
					,"AABABB"
					,"BAAA"
					,"BAA"};
		
		logger.debug(String.format("if [%s] is matchable with [%s]?", a, b));
		
		for(int i = 3; i < a.length; i++) {
			System.out.println(String.format("(%s == %s) ? %s", a[i], b[i], abbreviation(a[i],b[i])));
		}
	}
}