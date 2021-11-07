package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Sets {
    /**
     * Take the sum of a list of integers.
     * @param l List to take the sum of.
     * @return The sum of the list of integers.
     */
    public static int sum(List<Integer> l){
        return l.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Find the max value of a list of integers.
     * @param l List to find the max of.
     * @return The maximum integer in the list.
     */
    public static int max(List<Integer> l){
        if (l == null) return Integer.MAX_VALUE;
        return (l.stream().max(Integer::compare).isPresent() ? l.stream().max(Integer::compare).get() : Integer.MAX_VALUE);
    }

    /**
     * Find the min value of a list of integer.
     * @param l List to find the min of.
     * @return The minimum integer of the list.
     */
    public static int min(List<Integer> l){
        if (l == null) return Integer.MIN_VALUE;
        return (l.stream().min(Integer::compare).isPresent() ? l.stream().min(Integer::compare).get() : Integer.MIN_VALUE);
    }

    /**
     * Convert an array of Strings representing numbers into a List of Integers.
     * @param strings String array to be converted.
     * @return A List of integers from the String array.
     */
    public static List<Integer> stringArrayToIntList(String[] strings){
        return Arrays.stream(strings).mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

}