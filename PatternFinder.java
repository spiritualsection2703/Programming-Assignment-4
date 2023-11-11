package patternfinder;

import java.util.Scanner;
import java.util.Random;

public class PatternFinder {
    private static boolean singletonMiner(String mine, int length) {
    for (int start = 0; start < mine.length() - length; start++) {
        int i;
        for (i = start + 1; i < start + length; i++) {
            if (mine.charAt(i) != mine.charAt(i - 1)) {
                break;
            }
        }
        if (i == start + length) {
            System.out.println("Found a Singleton String: " + mine.substring(start, start + length) + " at index " + start);
            return true;
        }
    }
    return false;
}

    private static void arithmeticStringOrder1(String mine, int length) {
    for (int i = 0; i <= mine.length() - length; i++) {
        String subString = mine.substring(i, i + length);
        boolean isOrder1 = true;
        for (int j = 1; j < length; j++) {
            if (subString.charAt(j) != subString.charAt(j - 1) + 1) {
                isOrder1 = false;
                break;
            }
        }
        if (isOrder1) {
            System.out.println("Found an Arithmetic String: " + subString + " at index " + i);
            break;
        }
    }
}

private static void arithmeticStringOrderMinus1(String mine, int length) {
    for (int i = 0; i <= mine.length() - length; i++) {
        String subString = mine.substring(i, i + length);
        boolean isOrderMinus1 = true;
        for (int j = 1; j < length; j++) {
            if (subString.charAt(j) != subString.charAt(j - 1) - 1) {
                isOrderMinus1 = false;
                break;
            }
        }
        if (isOrderMinus1) {
            System.out.println("Found an Arithmetic String 2: " + subString + " at index " + i);
            break;
        }
    }
}

private static void balancedTripartiteString(String mine, int length) {
    int partLength = length / 3;
    for (int i = 0; i <= mine.length() - length; i++) {
        String subString = mine.substring(i, i + length);
        String part1 = subString.substring(0, partLength);
        String part2 = subString.substring(partLength, 2 * partLength);
        String part3 = subString.substring(2 * partLength, length);
        if (part1.equals(part2) && part2.equals(part3)) {
            System.out.println("Found a Balanced Tripartite String: " + subString + " at index " + i);
            break;
        }
    }
}

private static void balancedBipartiteString(String mine, int length) {
    int halfLength = length / 2;
    for (int i = 0; i <= mine.length() - length; i++) {
        String subString = mine.substring(i, i + length);
        String firstHalf = subString.substring(0, halfLength);
        String secondHalf = subString.substring(halfLength, length);
        if (firstHalf.equals(secondHalf)) {
            System.out.println("Found a Balanced Bipartite String: " + subString + " at index " + i);
            break;
        }
    }
}

private static void palinDrome(String mine, int length) {
    for (int i = 0; i <= mine.length() - length; i++) {
        String subString = mine.substring(i, i + length);
        StringBuilder reversed = new StringBuilder(subString).reverse();
        if (subString.equals(reversed.toString())) {
            System.out.println("Found a Palindrome: " + subString + " at index " + i);
            break;
        }
    }
}

    
    private static String randomStringGenerator(int length) {
        Random random = new Random(System.nanoTime());
        char[] array = new char[length];
        for (int i = 0; i < length; i++)
            array[i] = (char) ('a' + random.nextInt(26));
        return new String(array);
    }


    public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    // Step 1: handling input...
    int patternMaxLength;
    int randomStringLength;

    while (true) {
        try {
            System.out.println("Enter the length of random string: ");
            randomStringLength = keyboard.nextInt();

            if (randomStringLength < 100000 || randomStringLength > 1000000000)
                throw new NumberFormatException();

            System.out.println("Enter the maximum length of special patterns: ");
            patternMaxLength = keyboard.nextInt();

            if (patternMaxLength < 3 || patternMaxLength > 15)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            continue;
        }
        break;
    }

    // Step 2: generating a random string...
    String randomString;
        randomString = randomStringGenerator(randomStringLength);

    // Step 3: finding the interesting patterns
    try {
    for (int length = patternMaxLength; length > 0; length--) {
        if (singletonMiner(randomString, length)) {
            // Skip the next iteration if a singleton string is found
            continue;
        }
        arithmeticStringOrder1(randomString, length);
        arithmeticStringOrderMinus1(randomString, length);
        balancedTripartiteString(randomString, length);
        balancedBipartiteString(randomString, length);
        palinDrome(randomString, length);
    }
} catch (Exception exp) {
    System.out.println(exp.getMessage());
    }
    }
}