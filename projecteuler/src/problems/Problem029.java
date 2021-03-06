package problems;


import java.util.ArrayList;

//Distinct Powers.
public class Problem029 {
    private static ArrayList<Double> distinctPowers = new ArrayList<>();

    public static void main(String[] args){
        for (int a = 2; a <= 100; a++){
            for (int b = 2; b <= 100; b++){
                double newPower = Math.pow(a, b);
                if (!distinctPowers.contains(newPower)){
                    distinctPowers.add(newPower);
                }
            }
        }
        System.out.println("The number of unique powers generated by a^b with a and b from 2-100 is: " + distinctPowers.size());
    }
}
