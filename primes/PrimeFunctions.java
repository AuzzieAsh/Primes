/* File: PrimeFunctions.java, Last Edit: May 2015 */

package primes;

import java.util.ArrayList;

/**
 * This class will have all the functions for the primes to be calculated.
 *
 * @author Ashley Manson
 */
public class PrimeFunctions {

    private static long numberInserted = 0;
    private static ArrayList<Long> primeList = new ArrayList<>();

    /**
     * addPrime1 will add prime numbers to an ArrayList, from values 2 to
     * number. (This is algorithm 1 mentioned in the help message)
     *
     * @param number A long value that is the maximum number to be added to the
     * ArrayList.
     */
    public static void addPrime1(long number) {

        // Used to time the algorithm.
        long startTime = System.currentTimeMillis();

        final long FIRSTNUMBER = 2;
        // Sets required numbers to FIRSTNUMBER.
        long numToAdd = FIRSTNUMBER;
        long numToDivide = FIRSTNUMBER;

        if (number >= 2) {
            primeList.add(FIRSTNUMBER);
        }

        while (numToAdd <= number) {
            if (numToAdd % numToDivide == 0) {
                // Not a prime number.
                numToAdd++;
                numToDivide = FIRSTNUMBER;
            } else if (numToAdd % numToDivide != 0) {
                // Possible to be a prime number.
                if (numToDivide < primeList.size() + 1) {
                    // If numToDivide is still smaller than the list size.
                    numToDivide++;
                } else {
                    // Is a prime number, numToAdd is incremented, added to list
                    //and numToDivide set back to FIRSTNUMBER.
                    primeList.add(numToAdd++);
                    numToDivide = FIRSTNUMBER;
                    PrimeHandlers.printPrimesFoundSoFar(++numberInserted);
                }
            }
        }

        // Used to time the algorithm.
        long timeToCalc = System.currentTimeMillis() - startTime;

        PrimeHandlers.handleListOutput(primeList);
        PrimeHandlers.printInfo(timeToCalc, primeList.size(), number);

    }// addPrime1

    /**
     * AddPrime2 will add prime numbers to an ArrayList from 2 to number. Uses
     * the method isPrime to check if the number being added is a prime. (This
     * is of algorithm 2 mentioned in the help message)
     *
     * @param number A long value that is the maximum number to be added to the
     * ArrayList.
     */
    public static void addPrime2(long number) {

        // Used to time the algorithm.
        long startTime = System.currentTimeMillis();

        // The starting number to be calculated as a Prime.
        long numToAdd = 2;

        // This while loop will call the isPrime method to check if
        // numToAdd is a Prime or not.
        while (numToAdd <= number) {
            if (isPrime(numToAdd)) {
                primeList.add(numToAdd++);
                PrimeHandlers.printPrimesFoundSoFar(++numberInserted);
            } else {
                numToAdd++;
            }
        }
        // Used to time the algorithm.
        long timeToCalc = System.currentTimeMillis() - startTime;

        PrimeHandlers.handleListOutput(primeList);
        PrimeHandlers.printInfo(timeToCalc, primeList.size(), number);

    }// addPrime2

    /**
     * addPrime3 will add prime numbers to an ArrayList from 2 to number using
     * Eratosthenes of Cyrene algorithm using a boolean array. (This is
     * algorithm 3 mention in the help message)
     *
     * @param number A long value that is the maximum number to be added to the
     * ArrayList.
     */
    public static void addPrime3(long number) {

        System.err.println("Preparing boolean array, hold on...");

        // Used to time the algorithm.
        long startTime = System.currentTimeMillis();

        // Declaration of the boolean array.
        //number++; /* Can't remember why i did this */
        boolean[] primeTest = new boolean[(int) number];

        // Sets indexs 0 and 1 to false.
        primeTest[0] = false;
        primeTest[1] = false;

        // Sets all remainding indexs to true.
        for (int i = 2; i < number; i++) {
            primeTest[i] = true;
        }

        // Runs through the boolean array checking for true indexs and
        // adding Primes to the input ArrayList.
        for (long t = 2; t < number; t++) {
            // Looks for the first true index, if true will add to the
            // ArrayList list.
            if (primeTest[(int) t] == true) {
                primeList.add(t);
                PrimeHandlers.printPrimesFoundSoFar(++numberInserted);
                // Will set all indexs that multiply by t to false, they
                // are not Primes.
                for (int i = 0; i < number; i++) {
                    if (i * t < number) {
                        primeTest[i * (int) t] = false;
                    }
                    else {
                        i = (int) number;
                    }
                }
            }
        }

        // Used to time the algorithm.
        long timeToCalc = System.currentTimeMillis() - startTime;

        PrimeHandlers.handleListOutput(primeList);
        PrimeHandlers.printInfo(timeToCalc, primeList.size(), number);


    }// addPrime3

    /**
     * isPrime will take a long input and do an algorithm check to see if it is
     * a Prime. (This is a helper to addPrime2 and algorithm 4 mention in the
     * help message)
     *
     * @param num Is a long used to check if it is Prime or not.
     *
     * @return true or false to represent if number is a Prime or not.
     */
    public static boolean isPrime(long number) {

        // Checks if number is less than or equal to one, which are not prime
        // numbers.
        if (number <= 1) {
            return false;
        } // Checks if number is 2 or 3, which are prime numbers.
        else if (number == 2 || number == 3) {
            return true;
        } // Checks if number is divisible by 2 or 3.
        else if (number % 2 == 0 || number % 3 == 0) {
            return false;
        } // Final check for numbers that are not primes, but passed the above
        // tests, i.e. 25, 35, 49.
        else {
            // Sets two values, i to 5, and k to 2.
            int i = 5;
            int k = 2;
            // While loop will check if i multiplied by itself will be less
            // then or equal to the input num.
            while (i * i <= number) {
                // Checking if number divided by i has a remainder.
                if (number % i == 0) {
                    return false;
                }
                // i has k added to it and k will equal a new value
                // 6 - k. If while loop becomes false, then these do
                // not matter anymore.
                i += k;
                k = 6 - k;
            }
            return true;
        }

    }// isPrime
}
