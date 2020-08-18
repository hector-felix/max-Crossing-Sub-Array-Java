/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem4Main {

    public static int[] FindMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        Integer leftSum = Integer.MIN_VALUE;
        int sum = 0, maxLeft = 0, maxRight = 0;
        int[] ans = new int[3];

        for (int i = mid; mid >= low; mid--) {
            sum = sum + A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        Integer rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int j = (mid + 1); j <= high; j++) {
            sum = sum + A[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
//        System.out.println("Max Left: "+maxLeft);
//        System.out.println("Max Right: "+maxRight);
        
        ans[0] = maxLeft;
        ans[1] = maxRight;
        ans[2] = (leftSum + rightSum);
        return ans;
    }

    public static int[] FindMaximumSubarray(int[] A, int low, int high) {
        int[] ans = new int[3];
        
        int leftLow, leftHigh, leftSum;
        int rightLow, rightHigh, rightSum;
        int crossLow, crossHigh, crossSum;
        if (high == low) { // base case, only one element
            ans[0] = low;
            ans[1] = high;
            ans[2] = A[low];
            return ans;
        } else {
            int mid = (low + high) / 2;
            
            int[] leftTemp = FindMaximumSubarray(A, low, mid);
            leftLow = leftTemp[0];
            leftHigh = leftTemp[1];
            leftSum = leftTemp[2];

            int[] rightTemp = FindMaximumSubarray(A, (mid + 1), high);
            rightLow = rightTemp[0];
            rightHigh = rightTemp[1];
            rightSum = rightTemp[2];

            int[] crossTemp = FindMaxCrossingSubarray(A, low, mid, high);
            crossLow = crossTemp[0];
            crossHigh = crossTemp[1];
            crossSum = crossTemp[2];

            if ((leftSum >= rightSum) && (leftSum >= crossSum)) {
//                System.out.println("hit left");
//                System.out.println(Arrays.toString(leftTemp));
                ans[0] = leftLow;
                ans[1] = leftHigh;
                ans[2] = leftSum;
                return ans;
            } else if ((rightSum >= leftSum) && (rightSum >= crossSum)) {
//                System.out.println("hit right");
//                System.out.println(Arrays.toString(rightTemp));
                ans[0] = rightLow;
                ans[1] = rightHigh;
                ans[2] = rightSum;
                return ans;
            } else if ((crossSum >= leftSum) && (crossSum >= rightSum)) {
//                System.out.println("hit cross");
//                System.out.println(Arrays.toString(crossTemp));
                ans[0] = crossLow;
                ans[1] = crossHigh;
                ans[2] = crossSum;
                return ans;
            }
            else 
                return null;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader console = new BufferedReader(isr);

        System.out.print("Enter number: ");
        int input = Integer.parseInt(console.readLine());

        int[] A = new int[input];

        for (int i = 0; i < input; i++) {
            int low = (input*-1) - 1;
            int high = input + 1;
            A[i] = (int) (Math.random() * (high - low) + low);
        }

        System.out.println("Random Array within " + input * -1 + " and " + input + ": ");
        System.out.print("{");
        for (int i = 0; i < input; i++) {
            if (i < input - 1) {
                System.out.print(A[i] + ", ");
            } else {
                System.out.print(A[i] + "");
            }
        }
        System.out.print("}");

        int inversions = 0;

        for (int i = 0; i < input; i++) {
            for (int j = i + 1; j < input; j++) {
                if (A[i] > A[j]) {
                    inversions++;
                }
            }
        }

        System.out.println("\nInversions: " + inversions);
        
        int[] maxSum = FindMaximumSubarray(A, 0, A.length-1);
//        int[] maxSum = FindMaximumSubarray(A, 1, A.length-1);
        System.out.println(Arrays.toString(maxSum));
        System.out.println("Max Subarray: ["+maxSum[0]+","+maxSum[1]+"]");
        System.out.println("Max Sum: "+ maxSum[2]);
    }
}
