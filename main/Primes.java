/* File: Primes.java, Last Edit: May 2015 */

package main;

import primes.PrimeFunctions;
import primes.PrimeHandlers;

/**
 * This class is used to take command line arguments, one which would be the
 * number of the algorithm, and the second to be the number of Primes to be
 * calculated. The Primes that are calculated are added to an ArrayList of Longs
 * and the Primes are then printed out one by one.
 *
 * (Note: A prime number is a number that is greater than 1 and has "exactly"
 * two divisible numbers, 1 and itself.)
 *
 * @author Ashley Manson
 */
public class Primes {

    /**
     * Takes two command line arguments that are then used on one of the three
     * addPrime methods, or an isPrime method.
     *
     * @param args Command line argument is used to select a method. Also used
     * to get a Long for the method to calculate Primes.
     */
    public static void main(String[] args) {
        
        try {
            
            if (args.length == 1 && (args[0].toLowerCase().equals("help")
                    || args[0].toLowerCase().equals("h"))) {
                PrimeHandlers.printHelp();
            } 
            else if (args.length == 2 && (args[0].toLowerCase().equals("help")
                    || args[0].toLowerCase().equals("h"))) {
                PrimeHandlers.printSpecificHelp(args[1]);
            } 
            else if (args.length != 2) {
                PrimeHandlers.printErrorNotEnoughInput();
            } 
            else if (args.length == 2) {
                Long number = Long.valueOf(args[1]).longValue();
                System.err.println("\nAlgorithm Number = " + args[0] + ", Max "
                        + "Number = " + PrimeHandlers.formatNumber(number));

                switch (args[0]) {
                    case "1":
                        PrimeFunctions.addPrime1(number);
                        break;
                    case "2":
                        PrimeFunctions.addPrime2(number);
                        break;
                    case "3":
                        PrimeFunctions.addPrime3(number);
                        break;
                    case "4":
                        PrimeHandlers.printIsPrime(number);
                        break;
                    default:
                        System.err.println("\nNo Such algorithm exist.\n");
                        break;
                }
            } 
            else {
                System.err.println("Don't know what to do with that");
            }
        } 
        catch (NumberFormatException ne) {
            PrimeHandlers.printErrorNumberFormat();
        } 
        catch (ArrayIndexOutOfBoundsException | NegativeArraySizeException ae) {
            PrimeHandlers.printErrorBigNumberGiven();
        }
        catch (OutOfMemoryError me) {
            PrimeHandlers.printErrorOutOfMemory();
        }
    }// main
}
