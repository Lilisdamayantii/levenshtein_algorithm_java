package levenshteinjava1;

import java.util.Scanner;

/**
 *
 * @author Lilis
 */

import java.util.Scanner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
//import static javaapplication1.JavaApplication1.distance;

public class LevenshteinJava1 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("enter number of elements");
        String n = s.nextLine();
        System.out.println("Your Input: "+ n);
        String[] data = {"Sinabang", "Cisayong", "Birayang", "Senayang", "Pulau Punjung", "Yogyakarta", "Jakarta"};
        String jumlah[] = new String[data.length];
        String jumlah2[] = new String[data.length];
        double[] array = new double[data.length];
        double max[] = new double[data.length];

        for (int i = 0; i < data.length; i += 1) {
            double x = similarity(data[i], n);
            array[i] = x;
            max[i] = array[array.length - 1];
        }
        double temp = 0;
        String temp_data;

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (array[i] < array[j]) {
                    temp = array[i];
                    temp_data = data[i];
                    array[i] = array[j];
                    data[i] = data[j];
                    array[j] = temp;
                    data[j] = temp_data;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i] + " ");
//            System.out.println(data[i] + " ");
        }
        double jumlah1 = array[0];
        for (int i = 0; i < array.length; i++) {
            if (jumlah1 - array[i] < array[0] / 3) {
                System.out.println("result : (" + data[i] + ")");
            }
        }
    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }        
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }
}
