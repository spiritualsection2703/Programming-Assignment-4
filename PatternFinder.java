package patternfinder;

import java.util.Scanner;
import java.util.Random;

public class PatternFinder {
    private static String randomStringGenerator(int length) {
        Random random = new Random(System.nanoTime());
        char[] array = new char[length];
        for (int i = 0; i < length; i++)
            array[i] = (char) ('a' + random.nextInt(26));
        return new String(array);
    }

    private static void singletonMiner(String mine, int length) throws SingletonException {
        for (int start = 0; start < mine.length() - length; start++) {
            int i;
            for (i = start + 1; i < start + length; i++)
                if (mine.charAt(i) != mine.charAt(i - 1))
                    break;
            if (i == start + length)
                throw new SingletonException(mine.substring(start, start + length), start);
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        // Step 1: handling input...
        //Updated part where the user eneters the value via keyboard
        int patternMaxLength;
        int randomStringLength;

        while (true) {
            try {
                System.out.println("Enter the length of random string: ");
                //User uses kayboard
                randomStringLength = keyboard.nextInt();

                if (randomStringLength < 100000 || randomStringLength > 1000000000)
                    throw new NumberFormatException();

                System.out.println("Enter the maximum length of special patterns: ");
                patternMaxLength = keyboard.nextInt();

                if (patternMaxLength < 3 || patternMaxLength > 15)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again!");
                continue;
            }
            break;
        }

        // Step 2: generating a random string...
        String randomString = randomStringGenerator(randomStringLength);

        // Step 3: finding the interesting patterns
        try {
            for (int length = patternMaxLength; length > 0; length--)
                singletonMiner(randomString, length);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
    }
}
