package geeks4geeks.subarray_with_given_sum;

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {

    public static void main(String[] args) {
        //solve(new int[] { 1, 2, 3, 7, 5}, 12);
        //solve(new int[] { 1, 2, 3, 4,5,6,7,8,9,10}, 15 );

        Scanner in = new Scanner(System.in);
        int problems = in.nextInt();
        for (int i = 0; i < problems; i++) {
            int el = in.nextInt();
            int sum = in.nextInt();

            int[] array = new int[el];
            for (int j = 0; j < el; j++) {
                array[j] = in.nextInt();
            }
            solve(array, sum);
        }
    }

    private static void solve(int[] array, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        if (array.length == 0) {
            System.out.println("-1");
        }
        if (sum == 0) {
            System.out.println("0 0");
        }

        int currSum = 0;
        for (int j = 0; j < array.length; j++) {
            int el = array[j];
            currSum += el;
            map.put(currSum, j + 1);
        }

        currSum = 0;
        for (int j = 0; j < array.length; j++) {
            if (map.containsKey(currSum + sum)) {
                System.out.println(String.format("%s %s", j + 1, map.get(currSum + sum)));
                return;
            }
            int el = array[j];
            currSum += el;
        }
        System.out.println("-1");
    }
}