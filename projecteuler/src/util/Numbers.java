package util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Numbers {
    private static ArrayList<Integer> cachedPrimes = new ArrayList<>();

    /**
     * Gets factors of an integer.
     * @param num Integer that the method will find the factors of.
     * @return an ArrayList of all the factors as Integers.
     */
    public static ArrayList<Integer> getFactors(int num){
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= Math.floor(num/2.0); i++){
            if (num%i == 0){
                factors.add(i);
            }
        }
        return factors;
    }

    /**
     * Calculates the factorial of an long (e.g. 5! (5 factorial) = 5 * 4 * 3 * 2 * 1)
     * @param num Long to calculate factorial of
     * @return BigInteger of result, since result can get very large.
     */
    public static BigInteger factorial(long num){
        BigInteger total = BigInteger.ONE;
        long temp = num;
        while (temp != 1){
            total = total.multiply(new BigInteger(String.valueOf(temp)));
            temp--;
        }
        return total;
    }

    /**
     * Calculates the factorial of an integer (e.g. 5! (5 factorial) = 5 * 4 * 3 * 2 * 1)
     * @param num Integer to calculate factorial of
     * @return Long of result.
     */
    public static long factorial(int num){
        if (num == 1 || num == 0) return 1;
        return factorial(num - 1) * num;
    }

    /**
     * Solves a quadratic equation of the form ax^2 + bx + c = 0;
     * @param a The coefficient of x^2.
     * @param b The coefficient of x.
     * @param c The number at the end.
     * @return The solutions of the equation given by the quadratic formula in a double array.
     */
    public static double[] solveQuadratic(double a, double b, double c){
        double discriminant = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        return new double[] {(-b + discriminant) / (2 * a),
                (-b - discriminant) / (2 * a)};
    }

    /**
     * Checks if a number is a pentagonal number.
     * @param num Number to check if pentagonal.
     * @return A boolean representing true if the number is pentagonal.
     */
    public static boolean isPentagonal(int num){
        return (Numbers.solveQuadratic(3/2.0, -1/2.0, -num)[0]%1 == 0);
    }

    /**
     * Checks if a number is a hexagonal number.
     * @param num Number to check if hexagonal.
     * @return A boolean representing true if the number is hexagonal.
     */
    private static boolean isHexagonal(int num){
        return (Numbers.solveQuadratic(2, -1, -num)[0]%1 == 0);
    }

    /**
     * Tests if a given integer is prime.
     * @param num Number to be tested for primeness.
     * @return A boolean representing true if the number is prime.
     */
    public static boolean isPrime(int num){
        if (num <= 1){
            return false;
        }
        if (num == 2){
            return true;
        }
        for (int factor = 2; factor <= Math.ceil(Math.sqrt(num)); factor++){
            if (num%factor == 0){
                return false;
            }
        }
        return true;
    }
    public static boolean isPrime(long num){
        if (num <= 1){
            return false;
        }
        if (num == 2){
            return true;
        }
        for (int factor = 2; factor <= Math.ceil(Math.sqrt(num)); factor++){
            if (num%factor == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Gets prime factors of a number. (e.g. 6 -> 2, 3)
     * @param num Number to get prime factors of.
     * @return ArrayList of integers containing all the prime factors.
     */
    public static ArrayList<Integer> getPrimeFactors(int num){
        if (cachedPrimes.size() < num/Math.log1p(num) + 5) generateCachedPrimes(num * 2);
        ArrayList<Integer> primeFactors = new ArrayList<>();
        int temp = num;
        int divisor = 0;
        while (temp != 1){
            if (temp%cachedPrimes.get(divisor) == 0){
                temp /= cachedPrimes.get(divisor);
                primeFactors.add(cachedPrimes.get(divisor));
            } else {
                divisor++;
            }
        }
       return primeFactors;
    }

    /**
     * Euler's totient function. Returns numbers of integers below n that are relatively prime to n (gcd(n, m) will be 1).
     * @param num Number to compute totient of.
     * @return Number of integers below n that are relatively prime to n.
     */
    public static int totient(int num){
        double totient = num;
        for (double primeFactor : new HashSet<>(getPrimeFactors(num))){
            totient *= (1 - 1/primeFactor);
        }
        return (int) Math.round(totient);
    }

    /**
     * Generates a list of primes from 1 to a limit.
     * @param limit Limit of where to stop searching for primes.
     * @return An ArrayList of Longs of the primes.
     */
    public static ArrayList<Integer> generatePrimes(int limit){
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 0; i <= limit; i++){
            if (isPrime(i)){
                primes.add(i);
            }
        }
        return primes;


    }

    public static ArrayList<ArrayList<Integer>> generateUniqueFactorizations(int limit){
        ArrayList<ArrayList<Integer>> factorizations = new ArrayList<>();
        for (int i = 0; i < limit; i++){
            factorizations.add(new ArrayList<>());
        }
        ArrayList<Integer> primes = Numbers.generatePrimes(limit);
        System.out.println("Generated primes");
        for (int i = 2; i < limit; i++){
            if (primes.contains(i)){
                for (int indx = 0; indx < limit; indx += i){
                    factorizations.get(indx).add(i);
                }
            }
        }
        return factorizations;
    }

    public static void generateCachedPrimes(int limit){
        cachedPrimes = generatePrimes(limit);
    }

    /**
     * Checks if a number is Pandigital. (e.g. 51342 is 1-5 pandigital, since it has all digits from 1-5.)
     * @param num Integer to be checked for pandigitality.
     * @param lowestDigit Lowest digit to check, commonly 0 or 1.
     * @param highestDigit Highest digit to check.
     * @return A boolean representing true if the number is pandigital.
     */
    public static boolean isPandigital(int num, int lowestDigit, int highestDigit){
        for (int digit = lowestDigit; digit <= highestDigit; digit++){
            if (!String.valueOf(num).contains(String.valueOf(digit))){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a string is a palindrome, reading the same forward and backward. (e.g. kayak, racecar, 121)
     * @param str String to be tested.
     * @return A boolean representing true of the string is a palindrome.
     */
    public static boolean isPalindrome(String str){
        return (new StringBuilder(str).reverse().toString().equals(str));
    }

    /**
     * Returns all permutations of a string. (e.g. "123" returns "123", "132", "213", etc.)
     * @param str String to generate permutations from.
     * @return An ArrayList of all the permutations.
     */
    public static ArrayList<String> getPermutations(String str){
        if (str.length() == 1){
            ArrayList<String> permutations = new ArrayList<>();
            permutations.add(str);
            return permutations;
        }
        ArrayList<String> permutations = new ArrayList<>();
        for (int i = 0; i < str.length(); i++){
            for (String permutation : getPermutations(str.substring(0, i) + str.substring(i + 1))){
                permutations.add(str.charAt(i) + permutation);
            }
        }
        return permutations;
    }

    public static class Fraction implements Comparable<Fraction> {
        private int numerator;
        private int denominator;
        private double value;
        public Fraction(int numerator, int denominator){
            this.numerator = numerator;
            this.denominator = denominator;
            this.value = this.numerator / (double) this.denominator;
        }

        @Override
        public int compareTo(Fraction f) {
            return Double.compare(this.value, f.value);
        }

        public String toString(){
            return this.numerator + "/" + this.denominator;
        }
    }


}
