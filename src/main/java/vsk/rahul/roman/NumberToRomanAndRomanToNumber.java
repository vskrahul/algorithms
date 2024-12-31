package vsk.rahul.roman;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class NumberToRomanAndRomanToNumber {
    static Map<Character, Integer> romanValues = new HashMap<>();

    //I, II, III, IV, V, VI, VII, VIII, IX -> 1-9
    static final String roman_digits_regex = "(V?I{0,3}|I[VX])";

   //X, XX, XXX, XL, L, LX, LXX, LXXX, XC -> 10-90
    static final String roman_tens_regex = "(L?X{0,3}|X[LC])";

    //C, CC, CCC, CD, D, DC, DCC, DCCC, CM // 100-900
    static final String roman_hundreds_regex = "(D?C{0,3}|c[DM])";
    //M, MM, MMM -> 1000-3000
    static final String roman_thousands_regex = "M{0,3}";

    static String roman_regex = "^" + roman_thousands_regex
                                    + roman_hundreds_regex
                                    + roman_tens_regex
                                    + roman_digits_regex
                                    + "$";

    static {
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);
    }
    static String numberToRoman(int number) {
        StringBuilder sb = new StringBuilder();
        if(number > 3999 || number < 1)
            return "not a valid number";
        while(number >= 1000) {
            //M
            sb.append("M");
            number -= 1000;
        }
        if(number >= 900) {
            sb.append("CM");
            number -= 900;
        }
        if(number >= 500) {
            sb.append("D");
            number -= 500;
        }
        if(number >= 400) {
            sb.append("CD");
            number -= 400;
        }
        while(number >= 100) {
            sb.append("C");
            number -= 100;
        }
        if(number >= 90) {
            sb.append("XC");
            number -= 90;
        }
        if(number >= 50) {
            sb.append("L");
            number -= 50;
        }
        if(number >= 40) {
            sb.append("XL");
            number -= 40;
        }
        while(number >= 10) {
            sb.append("X");
            number -= 10;
        }
        if(number == 9) {
            //IX
            sb.append("IX");
            number -= 9;
        }
        if(number >= 5) {
            sb.append("V");
            number -= 5;
        }
        if(number == 4) {
            //IV
            sb.append("IV");
            number -= 4;
        }
        while(number >= 1) {
            sb.append("I");
            number -= 1;
        }
        return sb.toString();
    }
    static int romanToInt(String roman) {
        if(!isValidRoman(roman)) {
            throw new RuntimeException("Invalid roman: " + roman);
        }
        char[] romanChars = roman.toCharArray();
        int prev = romanValues.get(romanChars[0]);
        int curr;
        int result = 0;
        for(int i = 1; i < romanChars.length; i++) {
            curr = romanValues.get(romanChars[i]);
            if(prev < curr) {
                result = result - prev;
            } else {
                result = result + prev;
            }
            prev = curr;
        }
        result += prev;
        return result;
    }

    static boolean isValidRoman(String roman) {
        Pattern pattern = Pattern.compile(roman_regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(roman);
        return matcher.find();
    }
    public static void main(String[] args) {
        Stream.iterate(1, i -> i + 1)
                .limit(3999)
                .forEach(i -> {
                    String roman = numberToRoman(i);
                    int result = romanToInt(roman);
                    System.out.println(i + ": " + roman + "; assertion: " + (i == result));
                });
    }
}
