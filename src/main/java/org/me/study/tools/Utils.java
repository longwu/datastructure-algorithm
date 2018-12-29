package org.me.study.tools;

public final class Utils {
    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1){
                System.out.print(arr[i]);
            }
            else{
                System.out.print(arr[i] + ",");
            }
        }
    }
}
