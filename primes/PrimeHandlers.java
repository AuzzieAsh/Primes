/* File: PrimesHandlers.java, Last Edit: May 2015 */

package primes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This class will handle the printing and formating of everything for this
 * program.
 *
 * @author Ashley Manson
 */
public class PrimeHandlers {

    private static long primesFound = 100000;

    /**
     * Prints the ArrayList in formated way.
     *
     * @param list The ArrayList to be printed.
     */
    public static void printList(ArrayList<Long> list) {

        final int MAX_COLS = 10;
        int colsPrinted = 1;
        int width = (String.valueOf(list.get(list.size() - 1)).length()) + 1;

        for (int i = 0; i < list.size(); i++) {
            if (colsPrinted == MAX_COLS) {
                colsPrinted = 1;
                System.out.printf("%" + width + "d\n", list.get(i));
            } else {
                colsPrinted++;
                System.out.printf("%" + width + "d", list.get(i));
            }
        }
    }// printList

    /**
     * Prints out timing information in a formated way for the addPrime methods.
     *
     * @param timeToCalc The time in milliseconds.
     * @param size The size of the ArrayList.
     * @param total The total asked for by the user.
     */
    public static void printInfo(long timeToCalc, long size, long total) {
        long usedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        System.err.println("\nTime to calculate: " + millisToReadable(timeToCalc)
                + "\nNumber of primes found between 0 and " + formatNumber(total)
                + " is: " + formatNumber(size)
                + "\nTotal memory used: " + bytesToReadable(usedMem, true)
                + "\n");
    }// printTiming

    /**
     * Returns a String readable format for time, after being given a time in
     * milliseconds.
     *
     * @param milliseconds A long time in milliseconds.
     * @return A String representing a time.
     */
    public static String millisToReadable(Long milliseconds) {
        String result = milliseconds + " Milliseconds";
        if (milliseconds > 0) {
            long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
            milliseconds -= TimeUnit.HOURS.toMillis(hours);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
            milliseconds -= TimeUnit.MINUTES.toMillis(minutes);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
            milliseconds -= TimeUnit.SECONDS.toMillis(seconds);
            if (hours > 0) {
                result = hours + " Hours, " + minutes + " Minutes, " + seconds
                        + " Seconds, " + milliseconds + " Milliseconds";
            } else if (minutes > 0) {
                result = minutes + " Minutes, " + seconds + " Seconds, "
                        + milliseconds + " Milliseconds";
            } else if (seconds > 0) {
                result = seconds + " Seconds, " + milliseconds + " Milliseconds";
            }
        }
        return result;
    }// timeToCalculate

    /**
     * Converts a long byte into a human readable format.
     * Code from: http://bit.ly/r1zfVb
     * 
     * @param bytes The bytes being converted.
     * @param si Whether it is a standard unit or not.
     * @return A string representation of the byte.
     */
    public static String bytesToReadable(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }// bytesToReadable

    /**
     * Prints out if the number given is prime or not.
     *
     * @param number the number being tested.
     */
    public static void printIsPrime(Long number) {
        boolean result = PrimeFunctions.isPrime(number);
        if (result) {
            System.out.println("\n" + number + " is a prime number.\n");
        } else {
            System.out.println("\n" + number + " is not a prime number.\n");
        }
    }// printIsPrime

    /**
     * Prints out a help message for the program. Last Date Edited should be
     * updated here.
     */
    public static void printHelp() {
        System.err.println("\nThis program does various calculations with prime numbers."
                + "\nYou must decide which algorithm to use between: 1, 2, 3 or 4."
                + "\nFor more information on each algorithm, rerun the "
                + "program with the arguments: help <Algorithm number>"
                + "\nTo run the program, rerun with the arguments: "
				+ "<Number of Algorithm> <Number to calculate>"
                + "\n");
    }// printHelp

    /**
     * Prints out a little message saying how many primes have been found during
     * the progress of the program.
     */
    public static void printPrimesFoundSoFar(Long number) {
        if (number % primesFound == 0) {
            System.err.println(PrimeHandlers.formatNumber(
                    number) + " primes found so far...");
        }
    }// printPrimesFoundSoFar

    /**
     * Prints specific help for the 4 programs arguments.
     *
     * @param help The program that the help message is being printed for.
     */
    public static void printSpecificHelp(String help) {
        switch (help) {
            case "1":
                System.err.println("\nAlogrithm 1 uses an inefficient way of "
                        + "calculating primes."
                        + "\nIt can take a very long time to calculate primes."
                        + "\n");
                break;
            case "2":
                System.err.println("\nAlogrithm 2 is a more efficient way of "
                        + "calculating primes."
                        + "\nIt is useful for medium sized calculations of primes."
                        + "\n");
                break;
            case "3":
                System.err.println("\nAlogrithm 3 uses a boolean array to "
                        + "calculate primes."
                        + "\nThis algorithm is the fastest to calculate primes."
                        + "\n");
                break;
            case "4":
                System.err.println("\nAlogrithm 4 test if your number is prime "
                        + "or not."
                        + "\n");
                break;
            default:
                System.err.println("\nNo such algorithm exits. Try again."
                        + "\n");
                break;
        }
    }// printSpecificHelp

    /**
     * Prints an error message for not enough input.
     */
    public static void printErrorNotEnoughInput() {
        System.err.println("\nERROR: Too few or too many arguments given.");
    }// printErrorNotEnoughInput

    /**
     * Prints an error message for number format error.
     */
    public static void printErrorNumberFormat() {
        System.err.println("\nERROR: Value given was not an integer.");
    }// printErrorNumberFormat

    /**
     * Prints an error message for out of memory error.
     */
    public static void printErrorOutOfMemory() {
        System.err.println("\nERROR: Ran out of memory during execution.");
    }// printErrorOutOfMemory

    /**
     * Prints an error for a big number given.
     *
     * @param number The big number given.
     */
    public static void printErrorBigNumberGiven() {
        System.err.println("\nERROR: Number was too big for an array.");
    }// printErrorBigNumberGiven

    /**
     * Returns a String representation of a long number.
     *
     * @param number The number to be converted.
     * @return A String representation of a long number.
     */
    public static String formatNumber(Long number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number);
    }// formatNumber

    /**
     * Will handle whether the user wants to print out the prime list or not.
     *
     * @param list The prime list.
     */
    public static void handleListOutput(ArrayList<Long> list) {
        Scanner scan = new Scanner(System.in);
        boolean pass = false;
        while (!pass) {
            System.err.print("\nDo you want the prime list to be printed? (Y/N)"
                    + "\nWhat do you say? : ");
            String response = scan.nextLine();
            switch (response.toLowerCase()) {
                case "y":
                    System.err.println("Printing the prime list now, this may "
                            + "take some time.");
                    printList(list);
                    pass = true;
                    break;
                case "n":
                    System.err.println("Will not print the prime list.");
                    pass = true;
                    break;
                default:
                    System.err.println("Require \"Y\" or \"N\".\n");
                    break;
            }
        }
    }// handleListOutput
}
